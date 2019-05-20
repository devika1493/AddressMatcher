package google.beans;

import java.util.Set;



/**
 * Created by alex on 23/1/17.
 * 
 * This domain class holds all merchant candidate details.
 */
public class MerchantCandidates {

    private String id;
    private String source;
    private Set<Integer> dcxIds;
    private DerivedAttributes derivedAttributes;
    private Boolean uspsStandardized;
    private Long createdDate;
    private Boolean smallSite;
    private Long updatedDate;
    private Boolean webscraped;
    private Float score;
    private String tagstr;
    private Set<String> tags;
  

    public Float getScore()
    {
        return score;
    }

    public void setScore( Float score )
    {
        this.score = score;
    }

   

    public String removeComma( String str )
    {
        if( str == null )
            return "";
        return str.replace(",", " ").trim();

    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource( String source )
    {
        this.source = source;
    }

    public Set<Integer> getDcxIds()
    {
        return dcxIds;
    }

    public void setDcxIds( Set<Integer> dcxIds )
    {
        this.dcxIds = dcxIds;
    }

   

    public DerivedAttributes getDerivedAttributes()
    {
        return derivedAttributes;
    }

    public void setDerivedAttributes( DerivedAttributes derivedAttributes )
    {
        this.derivedAttributes = derivedAttributes;
    }

    public Long getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate( Long createdDate )
    {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate()
    {
        return updatedDate;
    }

    public final void setUpdatedDate( Long updatedDate )
    {
        this.updatedDate = updatedDate;
    }

    public final String getTagstr()
    {
        return tagstr;
    }

    public final void setTagstr( String tagstr )
    {
        this.tagstr = tagstr;
    }

    public final Set<String> getTags()
    {
        return tags;
    }

    public final void setTags( Set<String> tags )
    {
        this.tags = tags;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;

        MerchantCandidates that = (MerchantCandidates) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    public final Boolean getUspsStandardized()
    {
        return uspsStandardized == null ? false : true;
    }

    public final void setUspsStandardized( Boolean uspsStandardized )
    {
        this.uspsStandardized = uspsStandardized;
    }

    public final Boolean getSmallSite()
    {
        return smallSite;
    }

    public final void setSmallSite( Boolean smallSite )
    {
        this.smallSite = smallSite;
    }

    public final Boolean getWebscraped()
    {
        return webscraped;
    }

    public final void setWebscraped( Boolean webscraped )
    {
        this.webscraped = webscraped;
    }

}
