package org.rzt;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;


import google.beans.DerivedAttributes;
import google.beans.GoldRecordDetails;
import google.beans.JaroDistance;
import google.beans.MerchantCandidateRatings;
import google.beans.MerchantCandidates;
import google.beans.MerchantRecord;
import google.beans.MerchantUtils;
import google.beans.NullEmptyUtils;
import google.beans.Result;
import google.beans.Stopwords;
import google.beans.UspsAddressValidateRequest;
import google.beans.UspsAddressValidateRequest.Address;
import info.debatty.java.stringsimilarity.Cosine;
@RestController
public class GoogleAPI {
	public static String googleTextSearchUrl="https://maps.googleapis.com/maps/api/place/textsearch/json";
	public static String apiKey="AIzaSyDTDFzCrPVB2e7NipLlZIzSIKcwHyNpIcg";
	public static String googleplacesurl = "https://maps.googleapis.com/maps/api/place/details/json";
	static NullAwareBeanUtil nullAwareBeanUtil=new NullAwareBeanUtil();
	private static final Cosine termSimilarity = new Cosine();
    private static final JaroDistance charSimilarity = new JaroDistance();
    @CrossOrigin(origins = "*",allowedHeaders = "*")
	@RequestMapping(value="/rest/addresses", method = RequestMethod.POST)
	 public ResponseEntity<GoldRecordDetails> process(@RequestBody MerchantRecord merchantRecord)
	    {
	        try
	        {
	    		merchantRecord.setSrNo(1);
	    		MerchantUtils.cleanseZipCode(merchantRecord);
	        	    List<MerchantCandidateRatings> list=new ArrayList<>();
	            Set<MerchantCandidates> set = new HashSet<>();
	            
	            List<MerchantCandidates> candidatesTextSearch =processRecord(new SearchRecord(MerchantUtils.createMerchantSearchSrting(merchantRecord)));
	            		

	            if( !NullEmptyUtils.isNullorEmpty(candidatesTextSearch) )
	            {
	                set.addAll(candidatesTextSearch);
	            }
	            for( MerchantCandidates candidate : set )
	            {
	                try
	                {
	                    MerchantUtils.cleanseCandidateData(candidate.getDerivedAttributes());
	                    MerchantCandidateRatings merchantCandidateRatings = calculateDistance(merchantRecord, candidate);
	                    list.add(merchantCandidateRatings);
	                    
	                }
	                catch( Exception e )
	                {

	                   e.printStackTrace();
	                }
	            }
	            // Sorting the list by descending order
	            list.sort(( p1, p2 ) -> p1.getConfidenceScore().compareTo(p2.getConfidenceScore()));
	            Collections.reverse(list);
	            // Update the rank to the candidates for UI
	          
	            // Create gold record
	            GoldRecordDetails goldRecord = createGoldRecord(merchantRecord, list, false);
	            if(goldRecord==null) {
	            		goldRecord=new GoldRecordDetails();
	            		goldRecord.setRecordFound(false);
	            }
	        		goldRecord.setMatches(list);
	        		return new ResponseEntity<GoldRecordDetails>(goldRecord, HttpStatus.OK);

	        }
	        catch( Exception e )
	        {
	           e.printStackTrace();
	        }
	        return null;

	    }
	 private static GoldRecordDetails createGoldRecord( MerchantRecord merchantRecord, List<MerchantCandidateRatings> list,
	            boolean forUI ) throws Exception
	    {
	        GoldRecordDetails goldRecord = null;
	        int count = 0;

	        // initialize starting record
	        for( MerchantCandidateRatings rating : list )
	        {
	            if( goldRecord != null )
	                break;
	            if( rating.isQualifiesForRating() )
	            {
	                goldRecord = new GoldRecordDetails(rating, rating.getCandidate().getDerivedAttributes().getName(),
	                        rating.getCandidate().getDerivedAttributes().getAddressLine1(), rating.getCandidate()
	                                .getDerivedAttributes().getAddressLine2(), rating.getCandidate().getDerivedAttributes()
	                                .getZipCode(), rating.getCandidate().getDerivedAttributes().getCity(), rating
	                                .getCandidate().getDerivedAttributes().getStateCode(), rating.getCandidate()
	                                .getDerivedAttributes().getCountryCode(), rating.getCandidate().getDerivedAttributes()
	                                .getPhoneNo(), rating.getCandidate().getDerivedAttributes().getWebsite(),
	                        merchantRecord.getMccCode(),"", rating.getConfidenceScore());
	                

	                break;
	            }
	            if( count == 5 )
	                break;

	            count++;

	        }
	        
	        return goldRecord;

	    }
	  public static MerchantCandidateRatings calculateDistance( MerchantRecord merchantRecord, MerchantCandidates candidate ) throws Exception
	    {
	        if( candidate == null
	                || merchantRecord == null
	                || candidate.getDerivedAttributes() == null
	                || (NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getCombinedAddress()) && NullEmptyUtils
	                        .isNullorEmpty(candidate.getDerivedAttributes().getWebsite())) )
	            return null;

	        MerchantCandidateRatings ratings = new MerchantCandidateRatings();
	        ratings.setId(merchantRecord.getSrNo() + "_" + candidate.getId());
	        try
	        {
	            String candidateAddress = candidate.getDerivedAttributes().getCombinedAddress();

	            String candidateMainAddress = candidate.getUspsStandardized() ? candidate.getDerivedAttributes()
	                    .getAddressLine2() : candidate.getDerivedAttributes().getAddressLine1();

	            double termNamescore = 0.0;
	            double jaroNameScore = 0.0;

	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getMerchantName())
	                    && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getName()) )
	            {

	                String merchantName = MerchantUtils.removeCommonWordsinName(merchantRecord.getMerchantName());

	                String candidateName = MerchantUtils.removeCommonWordsinName(MerchantUtils.cleanseName(candidate
	                        .getDerivedAttributes().getName(), candidate.getDerivedAttributes().getCity()));

	                termNamescore = termSimilarity.similarity(merchantName, candidateName);
	                jaroNameScore = charSimilarity.similarity(merchantName, candidateName);
	                ratings.setName(termNamescore);

	                String[] name = merchantName.split("\\s");
	                // set jaro score if first word matches
	                String[] canName = candidateName.split("\\s");
	                String regex = ".*\\b" + name[0].trim().toLowerCase() + "\\b.*";
	                if( ((jaroNameScore + termNamescore) / 2 > 0.40 && jaroNameScore > ratings.getName() && jaroNameScore > 0.70)
	                        || (candidateName.matches(regex) && jaroNameScore > ratings.getName())
	                        || (candidateName.contains(name[0]) && jaroNameScore > ratings.getName()
	                                && jaroNameScore > 0.70 && !Stopwords.isStopword(name[0]))
	                        || (merchantRecord.getMerchantName().trim().toLowerCase().contains(canName[0])
	                                && jaroNameScore > ratings.getName() && jaroNameScore > 0.70 && !Stopwords
	                                    .isStopword(canName[0])) || (jaroNameScore > 0.85 && termNamescore < jaroNameScore) )

	                {
	                    ratings.setName(jaroNameScore);
	                }
	                
	            }

	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getZip5())
	                    && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getZipCode()) )
	                ratings.setZipCode(merchantRecord.getZip5()
	                        .equalsIgnoreCase(candidate.getDerivedAttributes().getZip5()) ? 1.0 : 0.0);

	            boolean addressNumberMatch = false;
	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getCombinedAddress())
	                    && !NullEmptyUtils.isNullorEmpty(candidateAddress) )
	            {
	                ratings.setAddressLine1(termSimilarity.similarity(merchantRecord.getCombinedAddress().toLowerCase(),
	                        candidateAddress.toLowerCase()));
	                String merNum = merchantRecord.getCombinedAddress().split(" ")[0];
	                String canNum = candidateAddress.split(" ")[0];
	                if( StringUtils.isNumeric(merNum) && StringUtils.isNumeric(canNum) && merNum.equals(canNum) )
	                    addressNumberMatch = true;
	                if( !addressNumberMatch && ratings.getAddressLine1() > 0.70 && StringUtils.isNumeric(merNum)
	                        && StringUtils.isNumeric(canNum) )
	                    ratings.setAddressLine1(ratings.getAddressLine1() - 0.20);
	            }

	            double mainAddressMatchScore = 0.0;

	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getMainAddress())
	                    && !NullEmptyUtils.isNullorEmpty(candidateMainAddress) )
	            {
	                String merNum = merchantRecord.getMainAddress().split(" ")[0];
	                String canNum = candidateMainAddress.split(" ")[0];
	                if( StringUtils.isNumeric(merNum) && StringUtils.isNumeric(canNum) && merNum.equals(canNum) )
	                    addressNumberMatch = true;

	                mainAddressMatchScore = charSimilarity.similarity(merchantRecord.getMainAddress().toLowerCase(),
	                        candidateMainAddress.toLowerCase());
	                double termSimilarityScore = termSimilarity.similarity(merchantRecord.getMainAddress().toLowerCase(),
	                        candidateMainAddress.toLowerCase());
	                if( ((mainAddressMatchScore + termSimilarityScore) / 2) < 0.50 && !addressNumberMatch )
	                    mainAddressMatchScore = termSimilarityScore;
	                mainAddressMatchScore = termSimilarityScore > mainAddressMatchScore ? termSimilarityScore
	                        : mainAddressMatchScore;
	                if( mainAddressMatchScore > ratings.getAddressLine1() )
	                    ratings.setAddressLine1(mainAddressMatchScore);

	                if( addressNumberMatch && ratings.getAddressLine1() < 0.80 && ratings.getZipCode() == 1 )
	                    ratings.setAddressLine1(ratings.getAddressLine1() + 0.10 > 0.70 ? ratings.getAddressLine1() + 0.10
	                            : 0.70);

	                if( !addressNumberMatch && ratings.getAddressLine1() > 0.70 && StringUtils.isNumeric(merNum)
	                        && StringUtils.isNumeric(canNum) )
	                    ratings.setAddressLine1(ratings.getAddressLine1() - 0.20);

	            }
	            ratings.setAddressNumberMatch(addressNumberMatch);

	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getCity())
	                    && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getCity()) )
	                ratings.setCity(charSimilarity.similarity(merchantRecord.getCity(),
	                        MerchantUtils.cleanseCity(candidate.getDerivedAttributes().getCity())));

	            double confidenceScore = ratings.getName() * 5 + ratings.getAddressLine1() * 3 + ratings.getZipCode() * 1.5
	                    + ratings.getCity() * 0.5;

	            if( NullEmptyUtils.isNullorEmpty(merchantRecord.getCombinedAddress()) )
	            {
	                confidenceScore = ratings.getName() * 6 + ratings.getZipCode() * 2.75 + ratings.getCity() * 1.25;
	            }

	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getPhone())
	                    && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getPhoneNo()) )
	            {
	                ratings.setPhoneNo(charSimilarity.similarity(merchantRecord.getPhone().toLowerCase(), candidate
	                        .getDerivedAttributes().getPhoneNo().toLowerCase()));
	            }

	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getMerchantName())
	                    && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getWebsite())
	                    && !MerchantUtils.ignoreWebsites(candidate.getDerivedAttributes().getWebsite()) )
	                ratings.setWebsite(charSimilarity.similarity(merchantRecord.getMerchantName().toLowerCase(),
	                        MerchantUtils.fetchDomainName(candidate.getDerivedAttributes().getWebsite().toLowerCase())));

	            Integer goodWebsiteMatch = 0;
	            if( ratings.getPhoneNo() > 0.95 )
	                confidenceScore += 0.2;
	            if( ratings.getWebsite() > 0.80 )
	            {
	                confidenceScore += 0.2;
	                goodWebsiteMatch = 1;
	            }
	            if( ratings.getZipCode() == 1 )
	            {
	                if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getZip4())
	                        && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getZip4()) )
	                {
	                    if( merchantRecord.getZip4().equals(candidate.getDerivedAttributes().getZip4()) )
	                        confidenceScore += 0.1;
	                }
	            }

	            ratings.setConfidenceScore(Math.ceil((confidenceScore / 10) * 100) > 100 ? 100 : Math
	                    .ceil((confidenceScore / 10) * 100));

	            ratings.setQualifiesForRating(qualifiesForGoldRecord(ratings, merchantRecord));

	            ratings.setCandidate(candidate);
	            Integer haveStoreIdMatch = 0;
	            if( !NullEmptyUtils.isNullorEmpty(merchantRecord.getNameWithStoreId())
	                    && !NullEmptyUtils.isNullorEmpty(candidate.getDerivedAttributes().getStoreId()) )
	            {
	                if( jaroNameScore > 0.50
	                        && merchantRecord.getNameWithStoreId().contains(candidate.getDerivedAttributes().getStoreId()) )
	                {
	                    ratings.setQualifiesForRating(true);
	                    ratings.setConfidenceScore(jaroNameScore * 100);
	                    haveStoreIdMatch = 1;
	                    ratings.setStoreIdMatch(true);
	                }

	            }
	            String features = ratings.getName() + "," + ratings.getAddressLine1() + "," + ratings.getZipCode() + ","
	                    + ratings.getCity() + "," + (ratings.getPhoneNo() == 1 ? 1.0 : 0.0) + "," + goodWebsiteMatch + ","
	                    + haveStoreIdMatch;
	            ratings.setFeatures(features);
	            ratings.setScore(ratings.getConfidenceScore());
	            ratings.setQualifiedFromSimilarity(ratings.isQualifiesForRating());
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace();
	        }
	        return ratings;
	    }
	  public static boolean qualifiesForGoldRecord( MerchantCandidateRatings rating, MerchantRecord merchantRecord ) throws Exception
	    {
	        boolean zipMatch = rating.getZipCode() >= 0.90;
	        boolean cityOrZipMatch = (rating.getCity() >= 0.90 || rating.getZipCode() >= 0.90);
	        if( NullEmptyUtils.isNullorEmpty(merchantRecord.getCombinedAddress()) )
	        {
	            if( (rating.getName() >= 0.74 && cityOrZipMatch) || rating.getConfidenceScore() >= 80 )
	                return true;
	        }
	        else if( (rating.getName() >= 0.80 && (rating.getAddressLine1() >= 0.70 || zipMatch))

	                || (rating.getName() >= 0.75 && rating.getAddressLine1() >= 0.80 && cityOrZipMatch)

	                || (rating.getName() >= 0.70 && rating.getAddressLine1() >= 0.70 && zipMatch)

	                || (rating.getName() >= 0.60 && rating.getAddressLine1() >= 0.90 && zipMatch)

	                || (NullEmptyUtils.isNullorEmpty(merchantRecord.getMerchantName()) && rating.getAddressLine1() >= 0.90 && zipMatch)

	                || (rating.getName() >= 0.90 && zipMatch)

	                || (rating.getName() >= 0.70 && rating.getPhoneNo() == 1 && cityOrZipMatch)
	                || (rating.getConfidenceScore() >= 80 && cityOrZipMatch && rating.isAddressNumberMatch())

	        )
	            return true;
	        return false;
	    }
	private static List<MerchantCandidates> processRecord(SearchRecord searchRec) throws Exception
    {
		 List<MerchantCandidates> list=new ArrayList<>();
        try
        {
        
                long startTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                Set<SearchResponse> googleResponses = new HashSet<>();
                Set<SearchResponse> googleResponsesResult=makeGoogleTextSearch(searchRec.getSearchQuery(),searchRec);
               
                if( googleResponsesResult != null )
                {
                	int count=0;
                	
                    for( SearchResponse searchRes : googleResponsesResult )
                    {
                        GooglePlaceResponse googlePlaceRes = (GooglePlaceResponse) searchRes;
                        //standardizing address using USPS
                        MerchantCandidates m=new MerchantCandidates();
                        googlePlaceRes.getResult().setDerivedAttributes(
                                standardizeAgentAddress(
                                        setDerivedAttribute(googlePlaceRes).getResult().getDerivedAttributes(),m));
                       
                        
                        m.setId(""+(count++));
                        m.setDerivedAttributes(googlePlaceRes.getResult().getDerivedAttributes());
                        list.add(m);
                        googlePlaceRes.setSearchType("Google Search");
                        googleResponses.add(googlePlaceRes);
                    }
                }

                searchRec.setSearchResponses(googleResponses);
                searchRec.setAgentName("google");
        }
        
        catch( Exception e )
        {
          e.printStackTrace();
        }
        return list;
    }
	public static synchronized Object makeUspsHTTPRequest( UspsAddressValidateRequest requestData ) throws Exception
	{
		try
		{
			String inputXML = XmlUtils.convertJavaObjectToXmlString(requestData, UspsAddressValidateRequest.class);
			HashMap<String, String> urlParameters = new HashMap<>();
			urlParameters.put("xml", inputXML);
			urlParameters.put("API", "Verify");

			//making api request to usps
			HttpResponse response = HTTPUtils.doPostWithXmlParameters("http://production.shippingapis.com/ShippingAPI.dll", urlParameters);
			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity, "UTF-8");
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}
	public static DerivedAttributes standardizeAgentAddress( DerivedAttributes derivedAttributes, MerchantCandidates m ) throws Exception
	{
		
		try
		{
			UspsAddressValidateRequest addressValidatorRequest = new UspsAddressValidateRequest();
			addressValidatorRequest.setUSERID("398RAZOR6374");
			addressValidatorRequest.setIncludeOptionalElements("true");
			addressValidatorRequest.setReturnCarrierRoute("true");
			Address address = new Address();
			address.setID((byte) 0);
			address.setFirmName(derivedAttributes.getName());
			address.setAddress1(derivedAttributes.getAddressLine1());
			address.setAddress2(derivedAttributes.getAddressLine2());
			address.setCity(derivedAttributes.getCity());
			if( derivedAttributes.getStateCode() != null )
				address.setState(derivedAttributes.getStateCode());
			else
				address.setState(derivedAttributes.getState());
			address.setZip5(derivedAttributes.getZipCode());
			address.setZip4(derivedAttributes.getZipCode());

			addressValidatorRequest.setAddress(address);
			Object response = makeUspsHTTPRequest(addressValidatorRequest);
			if( response.toString().contains("Error") || response.toString().contains("Address Not Found") )
			{
				m.setUspsStandardized(false);

				System.out.println("USPS cannot standardize address ");
				return derivedAttributes;
			}
			
			UspsSuccessResponse uspsRes = (UspsSuccessResponse) XmlUtils
					.covertXmlStringToJavaObject(response.toString(), UspsSuccessResponse.class);

			if( uspsRes != null )
			{
				nullAwareBeanUtil.copyProperty(derivedAttributes, "addressLine1", uspsRes.getAddress().getAddress1());
				nullAwareBeanUtil.copyProperty(derivedAttributes, "addressLine2", uspsRes.getAddress().getAddress2());
				nullAwareBeanUtil.copyProperty(derivedAttributes, "city", uspsRes.getAddress().getCity());
				nullAwareBeanUtil.copyProperty(derivedAttributes, "state", uspsRes.getAddress().getState());
				derivedAttributes.setZipCode(uspsRes.getAddress().getZip5() + "-" + uspsRes.getAddress().getZip4());
				
			}
			m.setUspsStandardized(true);
			return derivedAttributes;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}
	 private static GooglePlaceResponse setDerivedAttribute( GooglePlaceResponse googlePlaceRes ) throws Exception
	    {

	        String rawAddr = googlePlaceRes.getResult().getAdrAddress();
	        rawAddr = rawAddr.replaceAll("<span class=", "").replaceAll("</span>", "~").replaceAll(",", "")
	                .replaceAll("\">", "=").replaceAll("\"", "").replaceAll("~ ", "~");

	        DerivedAttributes derivedAttr = new google.beans.DerivedAttributes();
	        derivedAttr.setName(googlePlaceRes.getResult().getName().toUpperCase());
	        // multiple try..catch because we are trying to derive attributes individually, 
	        //Some attribute may fail to extract. If it fails we are just logging and continue extracting other attributes,  
	        try
	        {
	            String city = rawAddr.substring(rawAddr.indexOf("locality="), rawAddr.indexOf("~region")).replace(
	                    "locality=", "");
	            derivedAttr.setCity(city);
	        }
	        catch( Exception e )
	        {
	        }
	        try
	        {
	            String zipCode = rawAddr.substring(rawAddr.indexOf("postal-code="), rawAddr.indexOf("~country")).replace(
	                    "postal-code=", "");
	            derivedAttr.setZipCode(zipCode);
	        }
	        catch( Exception e )
	        {
	        }
	        try
	        {
	            String stateCode = rawAddr.substring(rawAddr.indexOf("region="), rawAddr.indexOf("~postal-code")).replace(
	                    "region=", "");
	            derivedAttr.setStateCode(stateCode);
	        }
	        catch( Exception e )
	        {
	        }
	        try
	        {
	            String countryCode = rawAddr.substring(rawAddr.indexOf("country-name="), rawAddr.length() - 1).replace(
	                    "country-name=", "");
	            derivedAttr.setCountryCode(countryCode);
	        }
	        catch( Exception e )
	        {
	        }
	        try
	        {
	            String addressLine = rawAddr.substring(0, rawAddr.indexOf("street-address="))
	                    .replace("street-address=", "");
	            derivedAttr.setAddressLine1(addressLine);
	            addressLine = rawAddr.substring(rawAddr.indexOf("street-address="), rawAddr.indexOf("~locality")).replace(
	                    "street-address=", "");
	            derivedAttr.setAddressLine2(addressLine);
	        }
	        catch( Exception e )
	        {
      }
	        derivedAttr.setPhoneNo(googlePlaceRes.getResult().getFormattedPhoneNumber());
	        derivedAttr.setWebsite(googlePlaceRes.getResult().getWebsite());

	        googlePlaceRes.getResult().setDerivedAttributes(derivedAttr);

	        return googlePlaceRes;
	    }
	public static GooglePlaceResponse makeGooglePlaceIdSearch( String placeId, SearchRecord searchRec )
			throws Exception
	{
		try
		{
			String url = googleplacesurl + "?" + "placeid=" + placeId + "&key="
					+ apiKey;
			//making google api call
			return googleSearch(url, searchRec);
		}
		catch( Exception e )
		{
			return null;
		}
	}
	public static Set<SearchResponse> makeGoogleTextSearch( String searchQuery, SearchRecord searchRec )
			throws Exception
	{
		try
		{
			Set<SearchResponse> googleResponses = new HashSet<>();

			String url = googleTextSearchUrl + "?" + "query=" + URLEncoder.encode(searchQuery, "UTF-8")
					+ "&key=" + apiKey + "&quotaUser=user-1";
			//making google api call
			GooglePlaceResponse googlePlaceRes = googleSearch(url, searchRec);
			searchRec.setRequestCount(searchRec.getRequestCount() + 9);
			if( googlePlaceRes != null )
			{
				int noOfGoogleSearch = 0;
				for( Result result : googlePlaceRes.getResults() )
				{
					noOfGoogleSearch += 1;
					//making place id search to get web-site
					googlePlaceRes = makeGooglePlaceIdSearch(result.getPlaceId(), searchRec);
					if( googlePlaceRes != null )
					{
						googleResponses.add(googlePlaceRes);
					}
					//limiting no of requests as 2
					if( noOfGoogleSearch == 2 )
					{
						break;
					}
				}
			}
			return googleResponses;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	 public static GooglePlaceResponse googleSearch( String url, SearchRecord searchRec ) throws Exception
		{
			
			
				//making google api call
				Object response = Request.Post(url).execute().returnContent();
				searchRec.setRequestCount(searchRec.getRequestCount() + 1);
				//parse response
				GooglePlaceResponse googleRes = (GooglePlaceResponse) ObjectMapperUtils.convertObject(response.toString(),
		                GooglePlaceResponse.class);

				
				if( !isValidResponse(googleRes) )
				{
					return null;
				}
			
				
			return googleRes;
		}
	

		public static boolean isValidResponse( GooglePlaceResponse api ) throws Exception
		{
			if( !api.getStatus().equalsIgnoreCase("ok") )
			{
				return false;
			}
			return true;
		}
}
