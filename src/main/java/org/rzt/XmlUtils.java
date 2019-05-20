package org.rzt;


import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public final class XmlUtils {

	private XmlUtils()
	{
		super();
	}

	public static String convertJavaObjectToXmlString( Object pojo, Class<?> type ) throws JAXBException
	{

		JAXBContext contextObj = JAXBContext.newInstance(type);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		marshallerObj.marshal(pojo, sw);

		return sw.toString();
	}

	public static Object covertXmlStringToJavaObject( String xml, Class type )
	{
		Object result = null;
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StreamSource streamSource = new StreamSource(new StringReader(xml));
			JAXBElement<Class> je = unmarshaller.unmarshal(streamSource, type);
			result = je.getValue();
		}
		catch( JAXBException e )
		{
			e.printStackTrace();
		}
		return result;
	}
}
