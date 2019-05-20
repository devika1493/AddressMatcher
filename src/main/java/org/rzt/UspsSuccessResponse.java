package org.rzt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Zip5" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Zip4" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="DeliveryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="CarrierRoute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "address" } )
@XmlRootElement( name = "UspsSuccessResponse" )
public class UspsSuccessResponse implements SearchResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -147818290600708321L;
	@XmlElement( name = "Address", required = true )
	protected UspsSuccessResponse.Address address;

	/**
	 * Gets the value of the address property.
	 * 
	 * @return possible object is {@link UspsSuccessResponse.Address }
	 * 
	 */
	public UspsSuccessResponse.Address getAddress()
	{
		return address;
	}

	/**
	 * Sets the value of the address property.
	 * 
	 * @param value
	 *            allowed object is {@link UspsSuccessResponse.Address }
	 * 
	 */
	public void setAddress( UspsSuccessResponse.Address value )
	{
		this.address = value;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 *
	 * <p>
	 * The following schema fragment specifies the expected content contained within this class.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="Zip5" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *         &lt;element name="Zip4" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *         &lt;element name="DeliveryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *         &lt;element name="CarrierRoute" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}byte" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType( XmlAccessType.FIELD )
	@XmlType( name = "", propOrder = { "firmName", "address1", "address2", "city", "state", "zip5", "zip4",
			"deliveryPoint", "carrierRoute" } )
	public static class Address {

		@XmlElement( name = "FirmName" )
		protected String firmName;
		@XmlElement( name = "Address1", required = true )
		protected String address1;
		@XmlElement( name = "Address2", required = true )
		protected String address2;
		@XmlElement( name = "City", required = true )
		protected String city;
		@XmlElement( name = "State", required = true )
		protected String state;
		@XmlElement( name = "Zip5" )
		protected String zip5;
		@XmlElement( name = "Zip4" )
		protected String zip4;
		@XmlElement( name = "DeliveryPoint" )
		protected byte deliveryPoint;
		@XmlElement( name = "CarrierRoute", required = true )
		protected String carrierRoute;
		@XmlAttribute( name = "ID" )
		protected Byte id;

		public String getFirmName()
		{
			return firmName;
		}

		public void setFirmName( String firmName )
		{
			this.firmName = firmName;
		}

		/**
		 * Gets the value of the address2 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getAddress1()
		{
			return address1 + "";
		}

		/**
		 * Sets the value of the address2 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setAddress1( String value )
		{
			this.address1 = value;
		}

		/**
		 * Gets the value of the address2 property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getAddress2()
		{
			return address2 + "";
		}

		/**
		 * Sets the value of the address2 property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setAddress2( String value )
		{
			this.address2 = value;
		}

		/**
		 * Gets the value of the city property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getCity()
		{
			return city;
		}

		/**
		 * Sets the value of the city property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setCity( String value )
		{
			this.city = value;
		}

		/**
		 * Gets the value of the state property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getState()
		{
			return state;
		}

		/**
		 * Sets the value of the state property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setState( String value )
		{
			this.state = value;
		}

		/**
		 * Gets the value of the zip5 property.
		 *
		 */
		public String getZip5()
		{
			return zip5;
		}

		/**
		 * Sets the value of the zip5 property.
		 *
		 */
		public void setZip5( String value )
		{
			this.zip5 = value;
		}

		/**
		 * Gets the value of the zip4 property.
		 *
		 */
		public String getZip4()
		{
			return zip4;
		}

		/**
		 * Sets the value of the zip4 property.
		 *
		 */
		public void setZip4( String value )
		{
			this.zip4 = value;
		}

		/**
		 * Gets the value of the deliveryPoint property.
		 *
		 */
		public byte getDeliveryPoint()
		{
			return deliveryPoint;
		}

		/**
		 * Sets the value of the deliveryPoint property.
		 *
		 */
		public void setDeliveryPoint( byte value )
		{
			this.deliveryPoint = value;
		}

		/**
		 * Gets the value of the carrierRoute property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getCarrierRoute()
		{
			return carrierRoute;
		}

		/**
		 * Sets the value of the carrierRoute property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setCarrierRoute( String value )
		{
			this.carrierRoute = value;
		}

		/**
		 * Gets the value of the id property.
		 *
		 * @return possible object is {@link Byte }
		 *
		 */
		public Byte getID()
		{
			return id;
		}

		/**
		 * Sets the value of the id property.
		 *
		 * @param value
		 *            allowed object is {@link Byte }
		 *
		 */
		public void setID( Byte value )
		{
			this.id = value;
		}

	}
}
