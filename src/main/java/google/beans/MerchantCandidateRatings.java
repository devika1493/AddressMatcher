package google.beans;
public class MerchantCandidateRatings {

	private String id;
	private Double name = 0.0;
	private Double addressLine1 = 0.0;
	private Double addressLine2 = 0.0;
	private Double city = 0.0;
	private Double zipCode = 0.0;
	private Double state = 0.0;
	private Double stateCode = 0.0;
	private Double countryCode = 0.0;
	private Double website = 0.0;
	private Double phoneNo = 0.0;
	private Double score = 0.0;
	private boolean qualifiesForRating = false;

	private MerchantCandidates candidate;
	private String features;
	private Double confidenceScore = 0.0;

	private Double inituativeNameScore = 0.0;
	private String nameMatchDescription = "";
	private Double inituativeAddressLine = 0.0;
	private String addressMatchDescription = "";
	private Double inituativeConfidenceScore = 0.0;
	private String inituativefeatures;

	private Double nnNameScore = 0.0;
	private Double nnAddressLine = 0.0;
	private boolean qualifiedFromSimilarity = false;
	private boolean addressNumberMatch=false;
	private boolean storeIdMatch=false;
    public Double getNnNameScore() {
		return nnNameScore;
	}

	public void setNnNameScore(Double nnNameScore) {
		this.nnNameScore = nnNameScore;
	}

	public Double getNnAddressLine() {
		return nnAddressLine;
	}

	public void setNnAddressLine(Double nnAddressLine) {
		this.nnAddressLine = nnAddressLine;
	}

	public String getNameMatchDescription() {
        return nameMatchDescription;
    }

    public void setNameMatchDescription(String nameMatchDescription) {
        this.nameMatchDescription = nameMatchDescription;
    }

    public String getAddressMatchDescription() {
        return addressMatchDescription;
    }

    public void setAddressMatchDescription(String addressMatchDescription) {
        this.addressMatchDescription = addressMatchDescription;
    }

    public Double getInituativeNameScore() {
		return inituativeNameScore;
	}

	public void setInituativeNameScore(Double inituativeNameScore) {
		this.inituativeNameScore = inituativeNameScore;
	}

	public Double getInituativeAddressLine() {
		return inituativeAddressLine;
	}

	public void setInituativeAddressLine(Double inituativeAddressLine) {
		this.inituativeAddressLine = inituativeAddressLine;
	}

	public Double getInituativeConfidenceScore() {
		return inituativeConfidenceScore;
	}

	public void setInituativeConfidenceScore(Double inituativeConfidenceScore) {
		this.inituativeConfidenceScore = inituativeConfidenceScore;
	}

	public boolean isQualifiesForRating()
	{
		return qualifiesForRating;
	}

	public void setQualifiesForRating( boolean qualifiesForRating )
	{
		this.qualifiesForRating = qualifiesForRating;
	}

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public Double getName()
	{
		return name;
	}

	public void setName( Double name )
	{
		this.name = name;
	}

	public Double getAddressLine1()
	{
		return addressLine1;
	}

	public void setAddressLine1( Double addressLine1 )
	{
		this.addressLine1 = addressLine1;
	}

	public Double getAddressLine2()
	{
		return addressLine2;
	}

	public void setAddressLine2( Double addressLine2 )
	{
		this.addressLine2 = addressLine2;
	}

	public Double getCity()
	{
		return city;
	}

	public void setCity( Double city )
	{
		this.city = city;
	}

	public Double getZipCode()
	{
		return zipCode;
	}

	public void setZipCode( Double zipCode )
	{
		this.zipCode = zipCode;
	}

	public Double getState()
	{
		return state;
	}

	public void setState( Double state )
	{
		this.state = state;
	}

	public Double getStateCode()
	{
		return stateCode;
	}

	public void setStateCode( Double stateCode )
	{
		this.stateCode = stateCode;
	}

	public Double getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode( Double countryCode )
	{
		this.countryCode = countryCode;
	}

	public Double getWebsite()
	{
		return website;
	}

	public void setWebsite( Double website )
	{
		this.website = website;
	}

	public Double getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo( Double phoneNo )
	{
		this.phoneNo = phoneNo;
	}

	public Double getScore()
	{
		return score;
	}

	public void setScore( Double score )
	{
		this.score = score;
	}

	public MerchantCandidates getCandidate()
	{
		return candidate;
	}

	public void setCandidate( MerchantCandidates candidate )
	{
		this.candidate = candidate;
	}

	public Double getConfidenceScore()
	{
		return confidenceScore;
	}

	public void setConfidenceScore( Double confidenceScore )
	{
		this.confidenceScore = confidenceScore;
	}

	public String getFeatures()
	{
		return features;
	}

	public void setFeatures( String features )
	{
		this.features = features;
	}

	public String getInituativefeatures() {
		return inituativefeatures;
	}

	public void setInituativefeatures(String inituativefeatures) {
		this.inituativefeatures = inituativefeatures;
	}

	public boolean isQualifiedFromSimilarity() {
		return qualifiedFromSimilarity;
	}

	public void setQualifiedFromSimilarity(boolean qualifiedFromSimilarity) {
		this.qualifiedFromSimilarity = qualifiedFromSimilarity;
	}

	public boolean isAddressNumberMatch() {
		return addressNumberMatch;
	}

	public void setAddressNumberMatch(boolean addressNumberMatch) {
		this.addressNumberMatch = addressNumberMatch;
	}

	public boolean isStoreIdMatch() {
		return storeIdMatch;
	}

	public void setStoreIdMatch(boolean storeIdMatch) {
		this.storeIdMatch = storeIdMatch;
	}

	

}