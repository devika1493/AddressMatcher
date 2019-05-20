package org.rzt;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A generic class to hold all agent's search response.
 *
 * //
 */
@JsonSubTypes( { @JsonSubTypes.Type( value = GooglePlaceResponse.class ), })
public interface SearchResponse extends Serializable {

}