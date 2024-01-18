package org.vaadin.example;

import java.util.UUID;

public class Observation {

    /* ------------------- mscode ------------------- 

    private String mscode;
    
    public String getMscode() {
        return mscode;
    }
    public void setMscode(String mscode) {
        this.mscode = mscode;
    }
    */
    /* ------------------- Year ------------------- */

    private String year;

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    /* ------------------- estCOde ------------------- */
    
    private String estCode;
    
    public String getEstCode() {
        return estCode;
    }
    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    /* ------------------- estimate ------------------- */

    private Float estimate;
    
    public Float getEstimate() {
        return estimate;
    }
    public void setEstimate(Float estimate) {
        this.estimate = estimate;
    }

    /* ------------------- se ------------------- */

    private Float se;
    
    public Float getSe() {
        return se;
    }
    public void setSe(Float se) {
        this.se = se;
    }

    /* ------------------- lowerCIB ------------------- */

    private Float lowerCIB;
    
    public Float getLowerCIB() {
        return lowerCIB;
    }
    public void setLowerCIB(Float lowerCIB) {
        this.lowerCIB = lowerCIB;
    }

    /* ------------------- upperCIB ------------------- */

    private Float upperCIB;
    
    public Float getUpperCIB() {
        return upperCIB;
    }
    public void setUpperCIB(Float upperCIB) {
        this.upperCIB = upperCIB;
    }

    /* ------------------- flag ------------------- */

    private String flag;

    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /* ------------------- id ------------------- */

    private String _id;

    public Observation() {
        this._id = UUID.randomUUID().toString();
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    
}
