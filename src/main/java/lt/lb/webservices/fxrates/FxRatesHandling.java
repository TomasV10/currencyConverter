//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.22 at 02:43:27 PM EEST 
//


package lt.lb.webservices.fxrates;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


/**
 * List of foreign exchange rates or technical error report
 * 
 * <p>Java class for FxRatesHandling complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FxRatesHandling">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="FxRate" type="{http://www.lb.lt/WebServices/FxRates}FxRateHandling" maxOccurs="unbounded"/>
 *           &lt;element name="OprlErr" type="{http://www.lb.lt/WebServices/FxRates}OprlErrHandling"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FxRatesHandling", propOrder = {
    "fxRate",
    "oprlErr"
})
public class FxRatesHandling {

    @XmlElement(name = "FxRate")
    @JacksonXmlElementWrapper(useWrapping = false)
    protected List<FxRateHandling> fxRate;
    @XmlElement(name = "OprlErr")
    protected OprlErrHandling oprlErr;

    public FxRatesHandling() {
    }

    /**
     * Gets the value of the fxRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fxRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFxRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FxRateHandling }
     * 
     * 
     */

    @JsonProperty("FxRate")
    public List<FxRateHandling> getFxRate() {
        if (fxRate == null) {
            fxRate = new ArrayList<FxRateHandling>();
        }
        return this.fxRate;
    }

    /**
     * Gets the value of the oprlErr property.
     * 
     * @return
     *     possible object is
     *     {@link OprlErrHandling }
     *     
     */


    @JsonProperty("OprlErr")
    public OprlErrHandling getOprlErr() {
        return oprlErr;
    }

    /**
     * Sets the value of the oprlErr property.
     * 
     * @param value
     *     allowed object is
     *     {@link OprlErrHandling }
     *     
     */
    public void setOprlErr(OprlErrHandling value) {
        this.oprlErr = value;
    }
}
