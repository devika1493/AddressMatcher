package google.beans;


/**
 * Created by sethulakshmi on 23/1/17.
 */

public class DerivedAttributes {

	private String name;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String zipCode;
	private String state;
	private String stateCode;
	private String countryCode;
	private String website;
	private String phoneNo;
	private String zip5;
	private String zip4;
	private String storeId;
	private String storeName;
	private String combinedAddress;

	public DerivedAttributes()
	{

	}

	public DerivedAttributes( String name, String addressLine1, String addressLine2, String city )
	{
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
	}

	@Override
	public String toString()
	{
		return "name='" + name + '\'' + ", addressLine1='" + addressLine1 + '\'' + ", addressLine2='" + addressLine2
				+ '\'' + ", city='" + city + '\'' + ", zipCode='" + zipCode + '\'' + ", state='" + state + '\''
				+ ", state='" + stateCode + '\'' + ", country='" + countryCode + '\'' + ", website='" + website + '\''
				+ ", phoneNo='" + phoneNo + '\'' + ", storeId='" + storeId + '\'';
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo( String phoneNo )
	{
		this.phoneNo = phoneNo;
	}

	public String getName()
	{
		if( this.name == null )
			return "";
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getCity()
	{
		if( this.city == null )
			return "";
		return city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getZipCode()
	{
		if( this.zipCode == null )
			return "";
		return zipCode;
	}

	public void setZipCode( String zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getState()
	{
		return state;
	}

	public void setState( String state )
	{
		this.state = state;
	}

	public String getStateCode()
	{
		if( this.stateCode == null )
			return "";
		return stateCode;
	}

	public void setStateCode( String stateCode )
	{
		this.stateCode = stateCode;
	}

	public String getCountryCode()
	{
		if( this.countryCode == null )
			return "";
		return countryCode;
	}

	public void setCountryCode( String countryCode )
	{
		this.countryCode = countryCode;
	}

	public String getWebsite()
	{
		if( this.website == null )
			return "";
		return website;
	}

	public void setWebsite( String website )
	{
		this.website = website;
	}

	public String getAddressLine1()
	{
		if( this.addressLine1 == null )
			return "";
		return addressLine1;
	}

	public void setAddressLine1( String addressLine1 )
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		if( this.addressLine2 == null )
			return "";
		return addressLine2;
	}

	public void setAddressLine2( String addressLine2 )
	{
		this.addressLine2 = addressLine2;
	}

	public String getZip4()
	{
		if( zip4 == null )
			return "";
		return zip4;
	}

	public void setZip4( String zip4 )
	{
		this.zip4 = zip4;
	}

	public String getZip5()
	{
		if( zip5 == null )
			return "";
		return zip5;
	}

	public void setZip5( String zip5 )
	{
		this.zip5 = zip5;
	}

	public String getStoreId()
	{

		if( storeId == null )
			return "";
		return storeId;
	}

	public void setStoreId( String storeId )
	{
		this.storeId = storeId;
	}

	public String getStoreName()
	{
		if( storeName == null )
			return "";
		return storeName;
	}

	public void setStoreName( String storeName )
	{
		this.storeName = storeName;
	}

	public String getCombinedAddress()
	{
		return combinedAddress;
	}

	public void setCombinedAddress( String combinedAddress )
	{
		this.combinedAddress = combinedAddress;
	}
}
