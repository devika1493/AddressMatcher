package google.beans;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class MerchantUtils {

    public static final List<String> ignoreWords = Arrays
            .asList("avenue|lane|road|boulevard|drive|street|ave|dr|rd|blvd|ln|st|null|main|n|s|e|w|hwy|ste|suite|highway|pkwy|miles|the|and|restaurant|restaurants|store|stores|inc|cafe"
                    .split("\\|"));
    public static final List<String> ignoreDomains = Arrays.asList("google|yelp|foursquare".split("\\|"));
    public static final List<String> ignoreWordsInName = Arrays.asList("restaurant|restaurants|store|stores|inc|cafe"
            .split("\\|"));
    private static final String ignoreWalmartAddress = "702 SW 8th Street";
    private static final String ignorePaypalAddress = "2211 North First Stree";

    /**
     * Cleanses the zip code information
     *
     * @param record
     */
    public static void cleanseZipCode( MerchantRecord record )
    {
        try
        {
            String zipCode = record.getZip().trim();
            if( zipCode != null )
            {
                record.setZip(zipCode);
                // replacing non numeric & special with empty
                zipCode = zipCode.replaceAll("[^\\d.]", "");
                if( zipCode.length() == 5 )
                {
                    record.setZip5(zipCode);
                }
                else if( zipCode.length() == 9 )
                {
                    record.setZip4(zipCode.substring(5, 9));
                    record.setZip5(zipCode.substring(0, 5));
                }
                else if( zipCode.contains("-") )
                {
                    record.setZip5(zipCode.split("-")[0]);
                    record.setZip4(zipCode.split("-")[1]);
                }
                else if( zipCode.contains(" ") )
                {
                    record.setZip5(zipCode.split(" ")[0]);
                    record.setZip4(zipCode.split(" ")[1]);

                }
                else if( zipCode.length() > 5 && zipCode.length() < 9 )
                {
                    record.setZip5(zipCode.substring(0, 5));
                }
                else
                {
                    // LOGGER.warn("Improper zip code:" +
                    // JSONUtils.toJson(record));
                }
                if( record.getZip5().startsWith("0") )
                {
                    record.setZip5(record.getZip5().substring(1));
                }
                if( zipCode.startsWith("0") )
                {
                    record.setZip(zipCode.substring(1));
                }
                if( !record.getZip4().matches("[1-9]]") )
                    record.setZip4("");

                if( !NullEmptyUtils.isNullorEmpty(record.getZip5()) && !NullEmptyUtils.isNullorEmpty(record.getZip4()) )
                {
                    record.setZip(record.getZip5() + "-" + record.getZip4());
                }
                else if( !NullEmptyUtils.isNullorEmpty(record.getZip5()) )
                {
                    record.setZip(record.getZip5());
                }
                if( NullEmptyUtils.isNullorEmpty(record.getZip5()) )
                    record.setZip5(record.getZip());
            }
        }
        catch( Exception e )
        {
e.printStackTrace();        }
    }

    public static void cleanseCandidateZipCode( DerivedAttributes record )
    {
        try
        {
            String zipCode = record.getZipCode().trim();
            if( zipCode != null )
            {
                record.setZipCode(zipCode);
                // replacing non numeric & special with empty
                zipCode = zipCode.replaceAll("[^\\d.]", "");
                if( zipCode.length() == 5 )
                {
                    record.setZip5(zipCode);
                }
                else if( zipCode.length() == 9 )
                {
                    record.setZip4(zipCode.substring(5, 9));
                    record.setZip5(zipCode.substring(0, 5));
                }
                else if( zipCode.contains("-") )
                {
                    record.setZip5(zipCode.split("-")[0]);
                    record.setZip4(zipCode.split("-")[1]);
                }
                else if( zipCode.contains(" ") )
                {
                    record.setZip5(zipCode.split(" ")[0]);
                    record.setZip4(zipCode.split(" ")[1]);

                }
                else if( zipCode.length() > 5 && zipCode.length() < 9 )
                {
                    record.setZip5(zipCode.substring(0, 5));
                }
                else
                {
                    // LOGGER.warn("Improper zip code:" +
                    // JSONUtils.toJson(record));
                }
                if( record.getZip5().startsWith("0") )
                {
                    record.setZip5(record.getZip5().substring(1));
                }
                if( zipCode.startsWith("0") )
                {
                    record.setZipCode(zipCode.substring(1));
                }
                if( !record.getZip4().matches("[1-9]]") )
                    record.setZip4("");

                if( !NullEmptyUtils.isNullorEmpty(record.getZip5()) && !NullEmptyUtils.isNullorEmpty(record.getZip4()) )
                {
                    record.setZipCode(record.getZip5() + "-" + record.getZip4());
                }
                else if( !NullEmptyUtils.isNullorEmpty(record.getZip5()) )
                {
                    record.setZipCode(record.getZip5());
                }
                if( NullEmptyUtils.isNullorEmpty(record.getZip5()) )
                    record.setZip5(record.getZipCode());
            }
        }
        catch( Exception e )
        {
e.printStackTrace();        }
    }

    public static String cleanseCity( String city )
    {
        if( city == null )
            return "";
        String merchantCity = city.toLowerCase();
        try
        {
            merchantCity = merchantCity.replaceAll("&", " and ");
            merchantCity = merchantCity.replaceAll("-", " ");
            merchantCity = merchantCity.replaceAll("[^\\w\\s\\/\\*\\.\\']", "");
            merchantCity = merchantCity.replaceAll("[0-9]", "");
            merchantCity = merchantCity.replaceAll("\\bst\\b", "saint");

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return merchantCity.trim();

    }

    /**
     * Cleanses the merchant name
     *
     * @param record
     */
    public static void cleanseMerchantName( MerchantRecord record )
    {

        if( record == null || NullEmptyUtils.isNullorEmpty(record.getMerchantName()) )
            return;
        if( !record.getMerchantName().contains("*") && record.getMerchantName().matches(".*\\d+.*") )
        {
            record.setNameWithStoreId(record.getMerchantName());
        }

        try
        {
            record.setMerchantName(cleanseName(record.getMerchantName(), record.getCity()));
        }
        catch( Exception e )
        {
e.printStackTrace();        }
    }

    public static String cleanseName( String record, String city )
    {

        if( record == null || NullEmptyUtils.isNullorEmpty(record) )
            return "";

        try
        {
            String merchantName = record.toLowerCase();
            String replacedName = null;
            merchantName = merchantName.replaceAll("&", " and ");
            merchantName = merchantName.replaceAll("-", " ");
            merchantName = merchantName.replaceAll("[^\\w\\s\\/\\*\\.\\']", "");
            if( merchantName.matches(".*\\d+.*") )
            {
                replacedName = shouldReplaceNumbersInName(merchantName);

            }

            if( replacedName == null )
                merchantName = merchantName.replaceAll("[0-9]", " ");
            else
                merchantName = replacedName;

            merchantName = merchantName.replaceAll("\\s+", " ");
            if( !NullEmptyUtils.isNullorEmptyOrNullString(city) )
                merchantName = merchantName.replaceFirst(city.replaceAll("[^\\w\\s']", "").trim().toLowerCase(), "");
            merchantName = merchantName.trim();
            if( !NullEmptyUtils.isNullorEmpty(merchantName) && merchantName.contains("*") )
            {
                String[] name = merchantName.split("\\*");
                if( merchantName.split("\\*").length > 0 )
                {
                    String nameStr = "";
                    for( String n : name )
                    {
                        n = n.trim();
                        if( n.equalsIgnoreCase("of") || n.equalsIgnoreCase(AppConstants.CREDIT_MERCHANT_NAME_PART)
                                || n.equalsIgnoreCase(AppConstants.PAYPAL_MERCHANT_NAME_PART)
                                || n.equalsIgnoreCase(AppConstants.FACEBOOK_MERCHANT_NAME_PART) )
                            continue;
                        if( n.equalsIgnoreCase(AppConstants.MACY_MERCHANT_NAME_PART) )
                        {
                            nameStr = n;
                            break;
                        }
                        if( n.length() > nameStr.length() )
                            nameStr = n;

                    }
                    merchantName = nameStr;
                }
            }
            merchantName = merchantName.replaceAll("[\\*]", "");
            return merchantName.trim();
        }
        catch( Exception e )
        {

e.printStackTrace();            return record;
        }
    }

    private static String shouldReplaceNumbersInName( String merchantName )
    {
        if( merchantName.startsWith("76") )
        {
            String[] name = merchantName.split(" ");
            if( name.length == 2 && name[0].equals("76") && StringUtils.isNumeric(name[1].trim()) )
            {
                return name[0];
            }
        }
        return null;
    }

    public static boolean isInvalidPhoneNumber( String phone )
    {
        if( phone.matches("[^0-9]+") )
            return true;
        return false;
    }

    public static String cleanPhNo( String phno )
    {
        if( !NullEmptyUtils.isNullorEmpty(phno) )
        {
            phno = phno.replaceAll("[^\\d]", "");
            if( phno.length() < 10 )
                return "";
            else
                return phno.substring(phno.length() - 10);
        }
        return "";
    }

    public static boolean ignoreWebsites( String website )
    {
        try
        {
            if( NullEmptyUtils.isNullorEmpty(website) )
                return true;
            if( website.toLowerCase().contains(AppConstants.SITES_GOOGLE_MERCHANT_NAME_PART) )
                return false;
            String domainName = fetchDomainName(website);
            if( ignoreDomains.indexOf(domainName.toLowerCase()) != -1 )
                return true;
            return false;

        }
        catch( Exception e )
        {
            return true;
        }
    }

    public static String fetchDomainName( String website )
    {
        try
        {
            if( NullEmptyUtils.isNullorEmpty(website) )
                return website;
            website = website.toLowerCase().replaceAll("https", AppConstants.HTTP);
            if( !website.startsWith("http://") && website.contains(AppConstants.HTTP) )
            {
                website = website.replaceAll(AppConstants.HTTP, "").replaceAll(":", "");
                if( website.startsWith("/") )
                    website = website.substring(1);
            }
            if( !website.startsWith(AppConstants.HTTP) )
                website = "http://" + website;
            website = new java.net.URL(website).getHost();

            String[] addressParts = website.split("\\.");
            if( addressParts.length == 1 )
                return addressParts[0];
            else if( addressParts.length == 2 )
                return addressParts[0];
            else if( (addressParts.length == 3 || addressParts.length > 3) )
                return addressParts[1];
            return website;
        }
        catch( Exception e )
        {
            return website;
        }
    }

    public static String createMerchantSearchSrting( MerchantRecord record )
    {
        String tagStr = "";
        if( !NullEmptyUtils.isNullorEmptyOrNullString(record.getMerchantName()) )
        {
            tagStr += record.getMerchantName() + " ";
        }
        if( !NullEmptyUtils.isNullorEmptyOrNullString(record.getCombinedAddress()) )
        {
            tagStr += record.getCombinedAddress() + " ";

        }
        if(!NullEmptyUtils.isNullorEmptyOrNullString(record.getZip()) ) {
        	tagStr += record.getZip() +" ";
        }
        if(!NullEmptyUtils.isNullorEmptyOrNullString(record.getCity()) ) {
        	tagStr += record.getCity() +" ";
        }
        tagStr = CleanUtils.cleanAndLowerCase(tagStr);
        if( !StringUtils.isNumeric(tagStr) )
        {
            String[] words = tagStr.split(" ");
            tagStr = "";
            for( String word : words )
            {
                word = word.trim();

                tagStr += word + " ";
            }

        }
        return tagStr.trim();

    }

    /**
     * Replaces the special characters and return a cleansed lower case resultant string.
     *
     * @param input
     * @return
     */
    public static String stripSpecialCharactersAndDigits( String input )
    {
        return input.replaceAll("[^a-zA-Z]", "").trim().toLowerCase();
    }

    public static void cleanseMerchantAddress( MerchantRecord merchantRecord )
    {
        if( NullEmptyUtils.isNullorEmpty(merchantRecord.getCombinedAddress()) )
            return;
        if( ((merchantRecord.getCombinedAddress().equalsIgnoreCase(ignoreWalmartAddress) || merchantRecord
                .getMainAddress().equalsIgnoreCase(ignoreWalmartAddress)) && merchantRecord.getMerchantName()
                .toLowerCase().contains(AppConstants.WAL_MERCHANT_NAME_PART))
                || ((merchantRecord.getCombinedAddress().equalsIgnoreCase(ignorePaypalAddress) || merchantRecord
                        .getMainAddress().equalsIgnoreCase(ignorePaypalAddress))
                        && merchantRecord.getRawName().toLowerCase().contains(AppConstants.PAYPAL_MERCHANT_NAME_PART) && merchantRecord
                        .getRawName().toLowerCase().contains("*")) )
        {
            merchantRecord.setCombinedAddress("");
            merchantRecord.setMainAddress("");
        }
    }

    public static String removeCommonWordsinName( String name )
    {
        // remove common words in name
        if( NullEmptyUtils.isNullorEmptyString(name) )
            return "";
        name = name.toLowerCase();
        for( String replace : ignoreWordsInName )
        {
            String regex = "\\b" + replace + "\\b";
            name = name.replaceAll(regex, " ");
        }
        return name.replaceAll("\\s+", " ").trim();
    }

    private static String removeInvalidValueInAddress( String value )
    {
        if( value == null )
            return "";
        if( !NullEmptyUtils.isNullorEmpty(value)
                && (value.trim().equals("-") || value.trim().equals("--") || value.trim().equals("null")) )
        {
            return "";
        }

        return value.replaceAll("\\s+", " ").trim();
    }

    public static void cleanseCandidateData( DerivedAttributes derivedAttributes )
    {
        if( derivedAttributes != null )
        {
            derivedAttributes.setAddressLine1(removeInvalidValueInAddress(derivedAttributes.getAddressLine1()));
            derivedAttributes.setAddressLine2(removeInvalidValueInAddress(derivedAttributes.getAddressLine2()));
            derivedAttributes.setCombinedAddress(removeInvalidValueInAddress(derivedAttributes.getCombinedAddress()));
            MerchantUtils.cleanseCandidateZipCode(derivedAttributes);
            //            derivedAttributes.setPhoneNo(MerchantUtils.cleanPhNo(derivedAttributes.getPhoneNo()));

        }
    }
}