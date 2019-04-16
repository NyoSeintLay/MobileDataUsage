package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import android.util.Log;

import com.nyoseintlay.mobiledatausage.model.DataUsage;
import com.nyoseintlay.mobiledatausage.network.RetrofitInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                onFinishedListener.onFinished(getDataUsageList(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    public ArrayList<DataUsage> getDataUsageList(String jsonString) {
        ArrayList<DataUsage> dataUsageArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");

            for (int i = 0; i < records.length(); i++) {
                JSONObject explrObject = records.getJSONObject(i);
                dataUsageArrayList.add(new DataUsage(explrObject.getInt("_id"), explrObject.getString("quarter"), explrObject.getDouble("volume_of_mobile_data")));
            }

        }catch (JSONException e){
            Log.e("Exception","JSON Exception");
        }
        return dataUsageArrayList;
    }
}
