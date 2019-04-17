package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import com.nyoseintlay.mobiledatausage.helper.DatabaseHelper;
import com.nyoseintlay.mobiledatausage.model.DataUsageByYear;

import java.util.ArrayList;

public interface MainActivityInterface {
    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void requestDataFromServer(boolean isNetworkAvailable,DatabaseHelper databaseHelper);
        void onGraphMenuItemClick();
        void onDecreasedDataUsageImageButtonClick();
        void onRefreshImageButtonClick(boolean isNetworkAvaialble, DatabaseHelper databaseHelper);
    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetDataUsageIntractorImpl class
     **/
    interface MainView {

        void showProgress();

        void removeProgress();

        void setDataToRecyclerView(ArrayList<DataUsageByYear> dataUsage);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your databases, web services, or any other data source.
     **/
    interface GetDataUsageIntractor {

        interface OnFinishedListener {

            void onFinished(ArrayList<DataUsageByYear> dataUsage);
            void onFailure(Throwable t);
        }

        void getDataUsageArrayList(OnFinishedListener onFinishedListener,boolean isNetworkAvaialble, DatabaseHelper databaseHelper);



    }



}
