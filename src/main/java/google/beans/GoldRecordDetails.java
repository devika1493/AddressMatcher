package google.beans;

import java.util.List;

/**
 * Created by ishan on 28/2/17.
 */
public class GoldRecordDetails {

	private String name;
	private String nameSource;
	private String address1;
	private String addressSource;
	private String address2Source;
	private String address2;
	private String zipCode;
	private String zipCodeSource;
	private String city;
	private String citySource;
	private String stateCode;
	private String stateCodeSource;
	private String countryCode;
	private String countryCodeSource;
	private String phone;
	private String phoneSource;
	private String website;
	private String websiteSource;
	private String topCandidate;
	private Double score;
	private String topWebscrapingCandidate;
	private Double topWebscrapingCandidateScore;
	private Integer completenessScore;
	private Integer accuracyScore;
	private Integer recencyScore;
	private Integer confidenceScore;
	private MerchantCandidateRatings rating;
	private Integer merchantType;
	private Integer mccCode;
	private String mccDescription;
	private String brandName;
	private Boolean psp;
	private Double latitude;
	private Double longitude;
	private String latLngSource;
	private Boolean isUspsStandardized;
	private List<MerchantCandidateRatings> matches;
	private boolean recordFound=true;
	public GoldRecordDetails() {

	}

	public GoldRecordDetails(MerchantCandidateRatings rating, String name, String address1, String address2,
			String zipCode, String city, String stateCode, String countryCode, String phone, String website,
			Integer mccCode, String mccDescription, Double score) {
		super();
		this.rating = rating;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(name))
			this.name = name;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(address1))
			this.address1 = address1;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(address2))
			this.address2 = address2;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(zipCode))
			this.zipCode = zipCode;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(city))
			this.city = city;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(stateCode))
			this.stateCode = stateCode;
		else if (!NullEmptyUtils.isNullorEmptyOrNullString(rating.getCandidate().getDerivedAttributes().getState())) {
			this.stateCode = rating.getCandidate().getDerivedAttributes().getState();
		}
		if (!NullEmptyUtils.isNullorEmptyOrNullString(countryCode))
			this.countryCode = countryCode;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(phone))
			this.phone = phone;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(website))
			this.website = website;
		this.mccCode = mccCode;
		if (!NullEmptyUtils.isNullorEmptyOrNullString(mccDescription))
			this.mccDescription = mccDescription;

		this.confidenceScore = (int) Math.floor(score);
	}

	public String getMccDescription() {
		return mccDescription;
	}

	public void setMccDescription(String mccDescription) {
		this.mccDescription = mccDescription;
	}

	public String getAddress2Source() {
		return address2Source;
	}

	public void setAddress2Source(String address2Source) {
		this.address2Source = address2Source;
	}

	public Integer getMccCode() {
		return mccCode;
	}

	public void setMccCode(Integer mccCode) {
		this.mccCode = mccCode;
	}

	public Boolean getPsp() {
		return psp;
	}

	public void setPsp(Boolean psp) {
		this.psp = psp;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public MerchantCandidateRatings getRating() {
		return rating;
	}

	public void setRating(MerchantCandidateRatings rating) {
		this.rating = rating;
	}

	public String getName() {
		return name==null?"":name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSource() {
		return nameSource;
	}

	public void setNameSource(String nameSource) {
		this.nameSource = nameSource;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddressSource() {
		return addressSource;
	}

	public void setAddressSource(String addressSource) {
		this.addressSource = addressSource;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipCodeSource() {
		return zipCodeSource;
	}

	public void setZipCodeSource(String zipCodeSource) {
		this.zipCodeSource = zipCodeSource;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCitySource() {
		return citySource;
	}

	public void setCitySource(String citySource) {
		this.citySource = citySource;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateCodeSource() {
		return stateCodeSource;
	}

	public void setStateCodeSource(String stateCodeSource) {
		this.stateCodeSource = stateCodeSource;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCountryCodeSource() {
		return countryCodeSource;
	}

	public void setCountryCodeSource(String countryCodeSource) {
		this.countryCodeSource = countryCodeSource;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneSource() {
		return phoneSource;
	}

	public void setPhoneSource(String phoneSource) {
		this.phoneSource = phoneSource;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsiteSource() {
		return websiteSource;
	}

	public void setWebsiteSource(String websiteSource) {
		this.websiteSource = websiteSource;
	}

	public String getTopCandidate() {
		return topCandidate;
	}

	public void setTopCandidate(String topCandidate) {
		this.topCandidate = topCandidate;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getTopWebscrapingCandidate() {
		return topWebscrapingCandidate;
	}

	public void setTopWebscrapingCandidate(String topWebscrapingCandidate) {
		this.topWebscrapingCandidate = topWebscrapingCandidate;
	}

	public Double getTopWebscrapingCandidateScore() {
		return topWebscrapingCandidateScore;
	}

	public void setTopWebscrapingCandidateScore(Double topWebscrapingCandidateScore) {
		this.topWebscrapingCandidateScore = topWebscrapingCandidateScore;
	}

	public Integer getCompletenessScore() {
		return completenessScore;
	}

	public void setCompletenessScore(Integer completenessScore) {
		this.completenessScore = completenessScore;
	}

	public Integer getAccuracyScore() {
		return accuracyScore;
	}

	public void setAccuracyScore(Integer accuracyScore) {
		this.accuracyScore = accuracyScore;
	}

	public Integer getRecencyScore() {
		return recencyScore;
	}

	public void setRecencyScore(Integer recencyScore) {
		this.recencyScore = recencyScore;
	}

	public Integer getConfidenceScore() {
		return confidenceScore;
	}

	public void setConfidenceScore(Integer confidenceScore) {
		this.confidenceScore = confidenceScore;
	}


	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public final Boolean getIsUspsStandardized() {
		return isUspsStandardized;
	}

	public final void setIsUspsStandardized(Boolean isUspsStandardized) {
		this.isUspsStandardized = isUspsStandardized;
	}

	@Override
	public String toString() {
		return "name='" + name + '\'' + ", address1='" + address1 + '\'' + ", address2='" + address2 + '\''
				+ ", zipCode='" + zipCode + '\'' + ", city='" + city + '\'' + ", state='" + stateCode + '\''
				+ ", country='" + countryCode + '\'' + ", phone='" + phone + '\'' + ", website='" + website + '\''
				+ ", latitude='" + latitude + '\'' + ", longitude='" + longitude + '\'';
	}

	public String getLatLngSource() {
		return latLngSource;
	}

	public void setLatLngSource(String latLngSource) {
		this.latLngSource = latLngSource;
	}

	public List<MerchantCandidateRatings> getMatches() {
		return matches;
	}

	public void setMatches(List<MerchantCandidateRatings> matches) {
		this.matches = matches;
	}

	public boolean isRecordFound() {
		return recordFound;
	}

	public void setRecordFound(boolean recordFound) {
		this.recordFound = recordFound;
	}

}
