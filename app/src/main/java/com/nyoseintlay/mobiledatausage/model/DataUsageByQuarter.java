package com.nyoseintlay.mobiledatausage.model;

/**
 * Created by NyoSeint Lay on 16/04/19.
 */

public class DataUsageByQuarter {


    private Integer _id;
    private String quarter;
    private Double volume_of_mobile_data;

    public DataUsageByQuarter() {
    }

    public DataUsageByQuarter(Integer _id, String quarter, Double volume_of_mobile_data) {
        this._id = _id;
        this.quarter = quarter;
        this.volume_of_mobile_data = volume_of_mobile_data;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Double getVolume_of_mobile_data() {
        return volume_of_mobile_data;
    }

    public void setVolume_of_mobile_data(Double volume_of_mobile_data) {
        this.volume_of_mobile_data = volume_of_mobile_data;
    }

}
