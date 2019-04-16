package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import com.nyoseintlay.mobiledatausage.model.DataUsage;

import java.util.ArrayList;

public class MainActivityPresenter implements MainActivityInterface.presenter, MainActivityInterface.GetDataUsageIntractor.OnFinishedListener {

    private MainActivityInterface.MainView mainView;
    private MainActivityInterface.GetDataUsageIntractor getDataUsageIntractor ;

    public MainActivityPresenter(MainActivityInterface.MainView mainView, MainActivityInterface.GetDataUsageIntractor getDataUsageIntractor) {
        this.mainView = mainView;
        this.getDataUsageIntractor = getDataUsageIntractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshButtonClick() {
        if(mainView != null){
            mainView.showProgress();
        }
        getDataUsageIntractor.getDataUsageArrayList(this);
    }

    @Override
    public void requestDataFromServer() {
        getDataUsageIntractor.getDataUsageArrayList(this);
    }

    @Override
    public void onFinished(ArrayList<DataUsage> dataUsageArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(dataUsageArrayList);
            mainView.hideProgress();
        }
    }


    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
