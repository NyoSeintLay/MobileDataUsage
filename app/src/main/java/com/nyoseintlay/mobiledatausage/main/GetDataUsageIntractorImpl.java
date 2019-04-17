package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import android.util.Log;
import com.nyoseintlay.mobiledatausage.helper.DatabaseHelper;
import com.nyoseintlay.mobiledatausage.model.DataUsageByQuarter;
import com.nyoseintlay.mobiledatausage.model.DataUsageByYear;
import com.nyoseintlay.mobiledatausage.network.RetrofitInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataUsageIntractorImpl implements MainActivityInterface.GetDataUsageIntractor {

    @Override
    public void getDataUsageArrayList(final OnFinishedListener onFinishedListener, final boolean isNetworkAvaialble, final DatabaseHelper databaseHelper) {

        if(isNetworkAvaialble) {

            /** getDataUsage Online*/
            /** Create handle for the RetrofitInstance interface*/
            GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

            /** Call the method with parameter in the interface to get data*/
            Call<String> call = service.getDataUsageList();

            /**Log the URL called*/
            Log.i("URL Called", call.request().url() + "");

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    onFinishedListener.onFinished(getDataUsage(response.body(),databaseHelper));
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }else{
            /** getDataUsage Offline*/
            ArrayList<DataUsageByYear> dataUsageByYearArrayList = databaseHelper.getDataUsageByYear();
            if(dataUsageByYearArrayList.size()>0)onFinishedListener.onFinished(dataUsageByYearArrayList);
            else onFinishedListener.onFailure(new Throwable());
        }
    }

    public ArrayList<DataUsageByYear> getDataUsage(String jsonString,DatabaseHelper databaseHelper) {

        HashMap<Integer,ArrayList<DataUsageByQuarter>> dataUsageHashMap = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");

            for (int i = 0; i < records.length(); i++) {
                JSONObject explrObject = records.getJSONObject(i);
                DataUsageByQuarter dataUsageByQuarter = new DataUsageByQuarter(explrObject.getInt("_id"), explrObject.getString("quarter"), explrObject.getDouble("volume_of_mobile_data"));

                if(dataUsageHashMap.containsKey(Integer.parseInt(explrObject.getString("quarter").split("-")[0]))){
                    ArrayList<DataUsageByQuarter> dataUsageByQuarterArrayList = dataUsageHashMap.get(Integer.parseInt(explrObject.getString("quarter").split("-")[0]));
                    dataUsageByQuarterArrayList.add(dataUsageByQuarter);
                    dataUsageHashMap.put(Integer.parseInt(explrObject.getString("quarter").split("-")[0]), dataUsageByQuarterArrayList);
                }
                else {
                    dataUsageHashMap.put(Integer.parseInt(explrObject.getString("quarter").split("-")[0]),new ArrayList<>(Arrays.asList(dataUsageByQuarter)));
                }}

        }catch (JSONException e){
            Log.e("Exception","JSON Exception");
        }

        return getDataUsageByYear(dataUsageHashMap,databaseHelper);
    }

    private ArrayList<DataUsageByYear> getDataUsageByYear(HashMap<Integer,ArrayList<DataUsageByQuarter>> dataUsageHashMap,DatabaseHelper databaseHelper){
        ArrayList<DataUsageByYear> dataUsageByYearArrayList = new ArrayList<>();

        ArrayList<Integer> key = new ArrayList<>(dataUsageHashMap.keySet());
        Collections.sort(key);
        Double total_volume_per_year = 0.0;
        for(int i=0;i<dataUsageHashMap.size();i++) {
            Boolean decreasedVolume = false;
            for (int j = 0; j < dataUsageHashMap.get(key.get(i)).size(); j++) {
                total_volume_per_year += dataUsageHashMap.get(key.get(i)).get(j).getVolume_of_mobile_data();
                if(j!=0 && dataUsageHashMap.get(key.get(i)).get(j-1).getVolume_of_mobile_data()>dataUsageHashMap.get(key.get(i)).get(j).getVolume_of_mobile_data()) decreasedVolume=true;
            }
            DataUsageByYear dataUsageByYear = new DataUsageByYear(key.get(i),total_volume_per_year,decreasedVolume,dataUsageHashMap.get(key.get(i)));
            dataUsageByYearArrayList.add(dataUsageByYear);

            /** Save in Db*/
            databaseHelper.setDataUsageByYear(dataUsageByYear);
            for(DataUsageByQuarter data: dataUsageByYear.getDataUsageByQuarterArrayList()){
                databaseHelper.setDataUsageByQuarter(dataUsageByYear.getYear(),data);
            }
        }
        return dataUsageByYearArrayList;
    }

}
