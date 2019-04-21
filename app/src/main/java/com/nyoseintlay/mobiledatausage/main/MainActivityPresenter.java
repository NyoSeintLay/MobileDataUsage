package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import com.nyoseintlay.mobiledatausage.helper.DatabaseHelper;
import com.nyoseintlay.mobiledatausage.model.DataUsageByYear;

import java.util.ArrayList;

public class MainActivityPresenter implements MainActivityInterface.presenter, MainActivityInterface.GetDataUsageIntractor.OnFinishedListener {

    private MainActivityInterface.MainView mainView;
    private MainActivityInterface.GetDataUsageIntractor getDataUsageIntractor ;


    public MainActivityPresenter(MainActivityInterface.MainView mainView, MainActivityInterface.GetDataUsageIntractor getDataUsageIntractor) {
        this.mainView = mainView;
        this.getDataUsageIntractor = getDataUsageIntractor;
    }

    @Override
    public void onDecreasedDataUsageImageButtonClick() {

    }

    @Override
    public void onGraphMenuItemClick() {

    }

    @Override
    public void onRefreshImageButtonClick(boolean isNetworkAvailable, DatabaseHelper databaseHelper) {

        if(mainView != null){
            mainView.showProgress();
        }
        getDataUsageIntractor.getDataUsageArrayList(this,isNetworkAvailable,databaseHelper);

    }

    @Override
    public void requestDataFromServer(boolean isNetworkAvailable, DatabaseHelper databaseHelper) {
        if(mainView != null){
            mainView.showProgress();
        }
        getDataUsageIntractor.getDataUsageArrayList(this,isNetworkAvailable,databaseHelper);
    }

    @Override
    public void onFinished(ArrayList<DataUsageByYear> dataUsage) {
        if(mainView != null){
            mainView.setDataToRecyclerView(dataUsage);
            mainView.removeProgress();
        }
    }
    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.removeProgress();
        }
    }
}
