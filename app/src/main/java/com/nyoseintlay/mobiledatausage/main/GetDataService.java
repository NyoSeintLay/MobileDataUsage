package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

    @GET("datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&offset=14&limit=42")
    Call<String> getDataUsageList();


}
