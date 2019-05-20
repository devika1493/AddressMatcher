package org.rzt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import google.beans.Result;

/**
 * @author muthamizhan
 * 
 *         Generic pojo which holds all google place search responses.
 *
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "html_attributions", "result", "results", "status" } )
public class GooglePlaceResponse implements SearchResponse {

	private static final long serialVersionUID = -7589357615689176534L;
	@JsonProperty( "html_attributions" )
	private List<Object> htmlAttributions = null;
	@JsonProperty( "result" )
	private Result result;
	@JsonProperty( "results" )
	private List<Result> results = null;
	@JsonProperty( "status" )
	private String status;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	private String searchType;

	public String getSearchType()
	{
		return searchType;
	}

	public void setSearchType( String searchType )
	{
		this.searchType = searchType;
	}

	@JsonProperty( "html_attributions" )
	public List<Object> getHtmlAttributions()
	{
		return htmlAttributions;
	}

	@JsonProperty( "html_attributions" )
	public void setHtmlAttributions( List<Object> htmlAttributions )
	{
		this.htmlAttributions = htmlAttributions;
	}

	@JsonProperty( "result" )
	public Result getResult()
	{
		return result;
	}

	@JsonProperty( "result" )
	public void setResult( Result result )
	{
		this.result = result;
	}

	@JsonProperty( "results" )
	public List<Result> getResults()
	{
		return results;
	}

	@JsonProperty( "results" )
	public void setResults( List<Result> results )
	{
		this.results = results;
	}

	@JsonProperty( "status" )
	public String getStatus()
	{
		return status;
	}

	@JsonProperty( "status" )
	public void setStatus( String status )
	{
		this.status = status;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty( String name, Object value )
	{
		this.additionalProperties.put(name, value);
	}

}