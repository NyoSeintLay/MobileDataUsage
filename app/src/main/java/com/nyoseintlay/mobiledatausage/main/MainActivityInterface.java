package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import com.nyoseintlay.mobiledatausage.model.DataUsage;

import java.util.ArrayList;

public interface MainActivityInterface {
    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetDataUsageIntractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<DataUsage> dataUsageArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetDataUsageIntractor {

        interface OnFinishedListener {

            void onFinished(ArrayList<DataUsage> dataUsageList);
            void onFailure(Throwable t);
        }

        void getDataUsageArrayList(OnFinishedListener onFinishedListener);
    }



}
