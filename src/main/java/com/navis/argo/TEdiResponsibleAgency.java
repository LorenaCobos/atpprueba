//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.01.27 a las 01:53:38 PM CST 
//


package com.navis.argo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tEdiResponsibleAgency.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tEdiResponsibleAgency">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UN"/>
 *     &lt;enumeration value="ANSI"/>
 *     &lt;enumeration value="ACS"/>
 *     &lt;enumeration value="Nonstandard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tEdiResponsibleAgency")
@XmlEnum
public enum TEdiResponsibleAgency {


    /**
     * United Nations
     * 
     */
    UN("UN"),

    /**
     * American National Standards Institute
     * 
     */
    ANSI("ANSI"),

    /**
     * Australia Chamber of Shipping
     * 
     */
    ACS("ACS"),

    /**
     * Nonstandard
     * 
     */
    @XmlEnumValue("Nonstandard")
    NONSTANDARD("Nonstandard");
    private final String value;

    TEdiResponsibleAgency(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TEdiResponsibleAgency fromValue(String v) {
        for (TEdiResponsibleAgency c: TEdiResponsibleAgency.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
