package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import android.util.Log;

import com.nyoseintlay.mobiledatausage.model.DataUsageRaw;
import com.nyoseintlay.mobiledatausage.network.RetrofitInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataUsageIntractorImpl implements MainActivityInterface.GetDataUsageIntractor {

    @Override
    public void getDataUsageArrayList(final OnFinishedListener onFinishedListener) {

        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get data*/
        Call<String> call = service.getDataUsageList();

        /**Log the URL called*/
        Log.i("URL Called", call.request().url() + "");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                onFinishedListener.onFinished(getDataUsageHashMap(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    public HashMap<Integer,ArrayList<DataUsageRaw>> getDataUsageHashMap(String jsonString) {

        HashMap<Integer,ArrayList<DataUsageRaw>> dataUsageHashMap = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");

            for (int i = 0; i < records.length(); i++) {
                JSONObject explrObject = records.getJSONObject(i);
                DataUsageRaw dataUsageRaw = new DataUsageRaw(explrObject.getInt("_id"), explrObject.getString("quarter"), explrObject.getDouble("volume_of_mobile_data"));
                if(dataUsageHashMap.containsKey(Integer.parseInt(explrObject.getString("quarter").split("-")[0]))){
                    ArrayList<DataUsageRaw> dataUsageRawArrayList = dataUsageHashMap.get(Integer.parseInt(explrObject.getString("quarter").split("-")[0]));
                    dataUsageRawArrayList.add(dataUsageRaw);
                    dataUsageHashMap.put(Integer.parseInt(explrObject.getString("quarter").split("-")[0]),dataUsageRawArrayList);

                }
                else {
                    dataUsageHashMap.put(Integer.parseInt(explrObject.getString("quarter").split("-")[0]),new ArrayList<>(Arrays.asList(dataUsageRaw)));
                }}



        }catch (JSONException e){
            Log.e("Exception","JSON Exception");
        }
        return dataUsageHashMap;
    }
}
