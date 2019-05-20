package org.rzt;

import java.io.Serializable;
import java.util.Set;

public class SearchRecord implements Serializable {

	private static final long serialVersionUID = -501663342382998231L;
	private long executionTime;
	private String searchQueryId;
	private String searchQuery;
	private int merchantSrNo;
	private Set<SearchResponse> searchResponses;
	private int agentId;
	private String agentName;
	private int requestCount;
	private boolean forceReprocess;
	private boolean uspsStandardized;
	private String webScrapeName;
	private Set<Integer> webscraperDcxIds;

	public SearchRecord(String query) {
		this.searchQuery = query;
	}

	public boolean isUspsStandardized() {
		return uspsStandardized;
	}

	public void setUspsStandardized(boolean uspsStandardized) {
		this.uspsStandardized = uspsStandardized;
	}

	public boolean isForceReprocess() {
		return forceReprocess;
	}

	public void setForceReprocess(boolean fourceReprocess) {
		this.forceReprocess = fourceReprocess;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public Set<SearchResponse> getSearchResponses() {
		return searchResponses;
	}

	public void setSearchResponses(Set<SearchResponse> searchResponses) {
		this.searchResponses = searchResponses;
	}

	public String getSearchQueryId() {
		return searchQueryId;
	}

	public void setSearchQueryId(String searchQueryId) {
		this.searchQueryId = searchQueryId;
	}

	public int getMerchantSrNo() {
		return merchantSrNo;
	}

	public void setMerchantSrNo(int merchantSrNo) {
		this.merchantSrNo = merchantSrNo;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public int getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}

	public String getWebScrapeName() {
		return webScrapeName;
	}

	public void setWebScrapeName(String webScrapeName) {
		this.webScrapeName = webScrapeName;
	}

	public Set<Integer> getWebscraperDcxIds() {
		return webscraperDcxIds;
	}

	public void setWebscraperDcxIds(Set<Integer> webscraperDcxIds) {
		this.webscraperDcxIds = webscraperDcxIds;
	}

}
