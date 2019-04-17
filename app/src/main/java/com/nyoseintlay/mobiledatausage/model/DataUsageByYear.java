package com.nyoseintlay.mobiledatausage.model;

import java.util.ArrayList;

/**
 * Created by NyoSeint Lay on 17/04/19.
 */
public class DataUsageByYear {

    private int year;
    private Double volume_of_mobile_data_per_year;
    private Boolean isDecreasedVolume;
    private ArrayList<DataUsageByQuarter> dataUsageByQuarterArrayList;

    public DataUsageByYear() {
    }
    public DataUsageByYear(int year, Double volume_of_mobile_data_per_year,Boolean isDecreasedVolume,ArrayList<DataUsageByQuarter> dataUsageByQuarterArrayList) {
        this.year = year;
        this.volume_of_mobile_data_per_year = volume_of_mobile_data_per_year;
        this.isDecreasedVolume = isDecreasedVolume;
        this.dataUsageByQuarterArrayList = dataUsageByQuarterArrayList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getVolume_of_mobile_data_per_year() {
        return volume_of_mobile_data_per_year;
    }

    public void setVolume_of_mobile_data_per_year(Double volume_of_mobile_data_per_year) {
        this.volume_of_mobile_data_per_year = volume_of_mobile_data_per_year;
    }

    public Boolean getDecreasedVolume() {
        return isDecreasedVolume;
    }

    public void setDecreasedVolume(Boolean decreasedVolume) {
        isDecreasedVolume = decreasedVolume;
    }

    public ArrayList<DataUsageByQuarter> getDataUsageByQuarterArrayList() {
        return dataUsageByQuarterArrayList;
    }

    public void setDataUsageByQuarterArrayList(ArrayList<DataUsageByQuarter> dataUsageByQuarterArrayList) {
        this.dataUsageByQuarterArrayList = dataUsageByQuarterArrayList;
    }
}
