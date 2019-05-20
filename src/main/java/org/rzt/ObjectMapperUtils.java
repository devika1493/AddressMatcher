package org.rzt;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	static
	{
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		mapper.setSerializationInclusion(Include.NON_NULL);

	}

	private ObjectMapperUtils()
	{
		super();
	}

	public static String writeAsString( Object object )
	{
		try
		{
			return mapper.writeValueAsString(object);
		}
		catch( JsonProcessingException e )
		{
		}
		return null;
	}

	public static Object convertObject( String obj, Class<?> type )
	{
		try
		{
			return mapper.readValue(obj, type);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

}
