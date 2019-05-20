package org.rzt;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTPUtils {


	private static final String NO_SERVER_RESPONSE = "Unable to get a response from server";
	private static final String SERVER_RESPONSE = "Server responded with status code ";
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 *
	 */
	private HTTPUtils()
	{
		super();
	}

	public static HttpResponse doPostWithXmlParameters( String url, Map<String, String> xmlRequestParameters )
			throws Exception
	{
		HttpPost httpPost = new HttpPost(url);
		if( xmlRequestParameters.size() != 0 )
		{
			List<NameValuePair> urlParameters = new ArrayList<>();
			for( Map.Entry<String, String> parameter : xmlRequestParameters.entrySet() )
			{
				urlParameters.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
			}
			try
			{
				httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
			}
			catch( UnsupportedEncodingException e )
			{
				throw e;
			}
		}
		try
		{
			HttpResponse response = httpClient.execute(httpPost);
			validateHttpReponse(response);
			return response;
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	/**
	 * Validate http response
	 *
	 * @param response
	 * @return
	 */
	private static boolean validateHttpReponse( HttpResponse response ) throws Exception
	{
		StatusLine statusLine = response.getStatusLine();
		if( statusLine == null )
		{
			throw new Exception(NO_SERVER_RESPONSE);
		}

		int statusCode = statusLine.getStatusCode();
		if( statusCode < 200 || statusCode >= 300 )
		{
		}

		return true;
	}

	/**
	 * Get http post object with all parameters added to post request
	 *
	 * @param url
	 * @param requestParams
	 * @param headers
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static <T> HttpPost getHttpPostRequest( String url, T requestParams, Map<String, String> headers )
			throws UnsupportedEncodingException
	{
		HttpPost postRequest = new HttpPost(url);

		if( null != headers && !headers.isEmpty() )
		{
			for( Entry<String, String> e : headers.entrySet() )
			{
				postRequest.addHeader(e.getKey(), e.getValue());
			}
		}

		if( requestParams != null )
		{
			postRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            StringEntity params = null;
            try {
                params = new StringEntity(mapper.writeValueAsString(requestParams));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            postRequest.setEntity(params);
		}

		return postRequest;
	}

	/**
	 * Custom http post method using HttpClient, reads the response and returns response header
	 *
	 * @param <T>
	 * @param url
	 *            : URL
	 * @param requestParams
	 *            : POST request parameters
	 * @param headers
	 *            : Optional headers for request
	 * @return <U>
	 */
	public static <T> Map<String, String> doPost( String url, T requestParams, Map<String, String> headers )
			throws Exception
	{
		try
		{
			Map<String, String> responseHeader = new LinkedHashMap<>();

			HttpPost postRequest = getHttpPostRequest(url, requestParams, headers);

			HttpResponse response = httpClient.execute(postRequest);
			validateHttpReponse(response);

			if( response.getAllHeaders() != null )
			{
				for( Header header : response.getAllHeaders() )
				{
					responseHeader.put(header.getName(), header.getValue());
				}
			}

			return responseHeader;

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	/**
	 * Executes http post method using HttpClient, reads the response and converts response to json
	 * representation object specified
	 *
	 * @param url
	 *            : URL
	 * @param requestParams
	 *            : POST request parameters
	 * @param classType
	 *            : Return type for the response
	 * @param headers
	 *            : Optional headers for request
	 * @return <U>
	 */
	public static <T, U> HttpResponse doPost( String url, T requestParams, Class<U> classType,
			Map<String, String> headers ) throws Exception
	{
		try
		{
			HttpPost postRequest = getHttpPostRequest(url, requestParams, headers);

			HttpResponse response = httpClient.execute(postRequest);
			validateHttpReponse(response);
			return response;
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	/**
	 * Executes http put method using HttpClient, reads the response and converts response to json
	 * representation object specified
	 *
	 * @param url
	 * @param requestParams
	 * @param headers
	 * @return
	 */
	public static <T> Boolean doPut( String url, T requestParams, Map<String, String> headers ) throws Exception
	{
		try
		{
			HttpPut putRequest = new HttpPut(url);

			if( null != headers && !headers.isEmpty() )
			{
				for( Entry<String, String> e : headers.entrySet() )
				{
					putRequest.addHeader(e.getKey(), e.getValue());
				}
			}

			if( requestParams != null )
			{
				putRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
				StringEntity params = new StringEntity(mapper.convertValue(requestParams, String.class));

				putRequest.setEntity(params);
			}

			HttpResponse response = httpClient.execute(putRequest);
			return validateHttpReponse(response);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	/**
	 * Executes http get method using HttpClient, reads the response and converts response to json
	 * representation object specified
	 *
	 * @param url
	 *            : URL
	 * @param classType
	 *            : Return type for the response
	 * @param headers
	 *            : Optional headers for request
	 * @return <T>
	 */
	public static <T> T doGet( String url, Class<T> classType, Map<String, String> headers ) throws Exception
	{
		try
		{
			HttpGet request = new HttpGet(url);

			if( null != headers && !headers.isEmpty() )
			{
				for( Entry<String, String> e : headers.entrySet() )
				{
					request.addHeader(e.getKey(), e.getValue());
				}
			}

			HttpResponse response = httpClient.execute(request);
			validateHttpReponse(response);
			return convertReponseToObject(response, classType);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	/**
	 * Method that reads the response content into buffer and converts it to specified json
	 * representation object (classType)
	 */
	public static <T> T convertReponseToObject( HttpResponse response, Class<T> classType ) throws IOException
	{
		InputStream contentStream;
		String responseBody;

		HttpEntity responseEntity = response.getEntity();
		contentStream = responseEntity.getContent();

		Reader isReader = new InputStreamReader(contentStream, "UTF-8");
		int contentSize = (int) responseEntity.getContentLength();
		if( contentSize < 0 )
		{
			contentSize = 8 * 1024;
		}

		StringWriter strWriter = new StringWriter(contentSize);
		char[] buffer = new char[8 * 1024];
		int n;
		while( (n = isReader.read(buffer)) != -1 )
		{
			strWriter.write(buffer, 0, n);
		}

		responseBody = strWriter.toString();
		isReader.close();
		contentStream.close();
		return mapper.convertValue(responseBody, classType);
	}

	/**
	 * Executes http delete method using HttpClient
	 *
	 * @param queryUrl
	 * @param headers
	 * @return
	 */
	public static boolean doDelete( String queryUrl, Map<String, String> headers ) throws Exception
	{
		try
		{
			HttpDelete deleteRequest = new HttpDelete(queryUrl);

			if( null != headers && !headers.isEmpty() )
			{
				for( Entry<String, String> e : headers.entrySet() )
				{
					deleteRequest.addHeader(e.getKey(), e.getValue());
				}
			}

			HttpResponse response = httpClient.execute(deleteRequest);
			return validateHttpReponse(response);
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public static String getDomainName( String url ) throws URISyntaxException
	{
		URI uri = new URI(url);
		String domain = uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}
}
