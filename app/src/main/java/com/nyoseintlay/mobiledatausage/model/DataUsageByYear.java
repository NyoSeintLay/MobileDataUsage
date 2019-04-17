package com.nyoseintlay.mobiledatausage.model;
/**
 * Created by NyoSeint Lay on 17/04/19.
 */
public class DataUsageByYear {

    private String year;
    private Double volume_of_mobile_data_per_year;
    private Boolean isDecreasedVolume;

    public DataUsageByYear(String year, Double volume_of_mobile_data_per_year,Boolean isDecreasedVolume) {
        this.year = year;
        this.volume_of_mobile_data_per_year = volume_of_mobile_data_per_year;
        this.isDecreasedVolume = isDecreasedVolume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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
}
