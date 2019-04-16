package com.nyoseintlay.mobiledatausage.network;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://data.gov.sg/api/action/";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
