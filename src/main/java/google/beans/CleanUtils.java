package google.beans;
public class CleanUtils {

	public static String cleanAndLowerCase( String s )
	{
		if( s == null )
			return null;
		return s.replaceAll("[^A-Za-z0-9 ]", " ").replaceAll("\\s{2,}", " ").toLowerCase().replaceAll("null", "")
				.trim();
	}

	public static String cleanCityForMap( String s )
	{
		if( s == null )
			return null;
		return cleanAndLowerCase(s).replaceAll(" ", "");
	}

	public static String cleanZipForMap( String s )
	{
		if( s == null )
			return null;
		String zip = cleanAndLowerCase(s).replaceAll(" ", "");

		if( zip.contains(AppConstants.LOCALITY_AS_ZIP_PART) )
		{
			zip = zip.substring(0, zip.indexOf(AppConstants.LOCALITY_AS_ZIP_PART));
		}
		return (zip.length() == 9) ? zip.substring(0, 5) : zip;

	}

	public static DerivedAttributes cleanseDerivedAttributes( DerivedAttributes derivedAttributes )
	{
		if( !NullEmptyUtils.isNullorEmptyOrNullString(derivedAttributes.getName()) )
		{
			String name = derivedAttributes.getName();
			name = name.replaceAll("&amp", " & ");
			name = name.replaceAll("[^\\w\\s\\'\\-\\&]", " ");
			name = name.replaceAll("_", " ");
			name = name.replaceAll("\\s+", " ");
			derivedAttributes.setName(name.trim());
		}
		if( !NullEmptyUtils.isNullorEmptyOrNullString(derivedAttributes.getAddressLine1())
				&& !NullEmptyUtils.isNullorEmptyOrNullString(derivedAttributes.getAddressLine2()) )
		{
			if( derivedAttributes.getAddressLine1().equalsIgnoreCase(derivedAttributes.getAddressLine2()) )
			{
				derivedAttributes.setAddressLine2(null);
			}
		}
		if( !NullEmptyUtils.isNullorEmptyOrNullString(derivedAttributes.getAddressLine1()) )
		{
			derivedAttributes.setAddressLine1(cleanseAddress(derivedAttributes.getAddressLine1()));
		}
		if( !NullEmptyUtils.isNullorEmptyOrNullString(derivedAttributes.getAddressLine2()) )
		{
			derivedAttributes.setAddressLine2(cleanseAddress(derivedAttributes.getAddressLine2()));
		}
		return derivedAttributes;
	}

	public static String cleanseAddress( String address )
	{
		address = address.replaceAll("&amp", " ");
		address = address.replaceAll("[^\\w\\s\\/\\#]", " ");
		address = address.replaceAll("_", " ");
		address = address.replaceAll("\\s+", " ");
		address = address.trim();
		return address;
	}
}
