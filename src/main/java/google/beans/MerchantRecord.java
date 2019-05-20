package google.beans;
public class MerchantRecord {

    
    private int srNo;
    private int merchantSk;
    private String merchantId;
    private String merchantName;
    private String nameWithStoreId;
    private String address1;
    private String address2;
    private String combinedAddress;
    private String city;
    private String stateShort;
    private String stateLong;
    private String countryShort;
    private String countryLong;
    private String zip;
    private String zip4;
    private String zip5;
    private String category;
    private String phone;
    private String mainAddress;
    private String rawName;
    private Integer mccCode;
    private String mccDescription;

    public String getMccDescription()
    {
        return mccDescription;
    }

    public void setMccDescription( String mccDescription )
    {
        this.mccDescription = mccDescription;
    }

    public int getMerchantSk()
    {
        return merchantSk;
    }

    public void setMerchantSk( int merchantSk )
    {
        this.merchantSk = merchantSk;
    }

    public Integer getMccCode()
    {
        return mccCode;
    }

    public void setMccCode( Integer mccCode )
    {
        this.mccCode = mccCode;
    }

    @Override
    public String toString()
    {
        return "srNo=" + srNo + ", merchantId='" + merchantId + '\'' + ", merchantName='" + merchantName + '\''
                + ", address1='" + address1 + '\'' + ", address2='" + address2 + '\'' + ", city='" + city + '\''
                + ", state='" + stateShort + '\'' + ", country='" + countryShort + '\'' + ", zipCode='" + zip + '\''
                + ", category='" + category + '\'' + ", phone='" + phone + '\'';
    }

    public String getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId( String merchantId )
    {
        this.merchantId = merchantId;
    }

    public String getMerchantName()
    {
        return merchantName;
    }

    public void setMerchantName( String merchantName )
    {
        this.merchantName = merchantName;
    }

    public String getAddress1()
    {
        if( address1 == null )
            return "";
        return address1;
    }

    public void setAddress1( String address1 )
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        if( address2 == null )
            return "";
        return address2;
    }

    public void setAddress2( String address2 )
    {
        this.address2 = address2;
    }

    public String getCity()
    {
        if( city == null )
            return "";
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getStateShort()
    {
        if( stateShort == null )
            return "";
        return stateShort;
    }

    public void setStateShort( String stateShort )
    {
        this.stateShort = stateShort;
    }

    public String getStateLong()
    {
        return stateLong;
    }

    public void setStateLong( String stateLong )
    {
        this.stateLong = stateLong;
    }

    public String getCountryShort()
    {
        return countryShort;
    }

    public void setCountryShort( String countryShort )
    {
        this.countryShort = countryShort;
    }

    public String getCountryLong()
    {
        return countryLong;
    }

    public void setCountryLong( String countryLong )
    {
        this.countryLong = countryLong;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
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

    public Integer getSrNo()
    {
        return srNo;
    }

    public void setSrNo( Integer srNo )
    {
        this.srNo = srNo;
    }

    public void setSrNo( int srNo )
    {
        this.srNo = srNo;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip( String zip )
    {
        this.zip = zip;
    }

    public String getCombinedAddress()
    {
        return getAddress1() +" "+getAddress2();
    }

    public void setCombinedAddress( String combinedAddress )
    {
        this.combinedAddress = combinedAddress;
    }

   

    public String getMainAddress()
    {
        return mainAddress;
    }

    public void setMainAddress( String mainAddress )
    {
        this.mainAddress = mainAddress;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;

        MerchantRecord that = (MerchantRecord) o;

        if( srNo != that.srNo )
            return false;
        if( merchantId != null ? !merchantId.equals(that.merchantId) : that.merchantId != null )
            return false;
        if( merchantName != null ? !merchantName.equals(that.merchantName) : that.merchantName != null )
            return false;
        if( address1 != null ? !address1.equals(that.address1) : that.address1 != null )
            return false;
        if( address2 != null ? !address2.equals(that.address2) : that.address2 != null )
            return false;
        if( combinedAddress != null ? !combinedAddress.equals(that.combinedAddress) : that.combinedAddress != null )
            return false;
        if( city != null ? !city.equals(that.city) : that.city != null )
            return false;
        if( stateShort != null ? !stateShort.equals(that.stateShort) : that.stateShort != null )
            return false;
        if( stateLong != null ? !stateLong.equals(that.stateLong) : that.stateLong != null )
            return false;
        if( countryShort != null ? !countryShort.equals(that.countryShort) : that.countryShort != null )
            return false;
        if( countryLong != null ? !countryLong.equals(that.countryLong) : that.countryLong != null )
            return false;
        if( zip != null ? !zip.equals(that.zip) : that.zip != null )
            return false;
        if( zip4 != null ? !zip4.equals(that.zip4) : that.zip4 != null )
            return false;
        if( zip5 != null ? !zip5.equals(that.zip5) : that.zip5 != null )
            return false;
        if( category != null ? !category.equals(that.category) : that.category != null )
            return false;
        if( phone != null ? !phone.equals(that.phone) : that.phone != null )
            return false;
     
        return mainAddress != null ? mainAddress.equals(that.mainAddress) : that.mainAddress == null;
    }

    @Override
    public int hashCode()
    {
        int result = srNo;
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (merchantName != null ? merchantName.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (combinedAddress != null ? combinedAddress.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (stateShort != null ? stateShort.hashCode() : 0);
        result = 31 * result + (stateLong != null ? stateLong.hashCode() : 0);
        result = 31 * result + (countryShort != null ? countryShort.hashCode() : 0);
        result = 31 * result + (countryLong != null ? countryLong.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (zip4 != null ? zip4.hashCode() : 0);
        result = 31 * result + (zip5 != null ? zip5.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mainAddress != null ? mainAddress.hashCode() : 0);
        return result;
    }

    public String getNameWithStoreId()
    {
        return nameWithStoreId;
    }

    public void setNameWithStoreId( String nameWithStoreId )
    {
        this.nameWithStoreId = nameWithStoreId;
    }

    public String getRawName()
    {
        return rawName;
    }

    public void setRawName( String rawName )
    {
        this.rawName = rawName;
    }

}