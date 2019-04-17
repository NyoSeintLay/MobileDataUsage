package com.nyoseintlay.mobiledatausage.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Observable;


public class MainActivityPresenterTest {

    @Mock
    MainActivityInterface.GetDataUsageIntractor getDataUsageIntractor;

    @Mock
    MainActivityInterface.MainView mainView;

    MainActivityPresenter presenter;

    @Mock
    MainActivityInterface.GetDataUsageIntractor.OnFinishedListener onFinishedListener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainActivityPresenter(mainView,getDataUsageIntractor);
        //presenter.attachView();
    }

    @Test
    public void onDestroy() {
    }

    @Test
    public void onRefreshButtonClick() {

    }

    @Test
    public void requestDataFromServer() {
    }

    @Test
    public void onFinished() {

    }

    @Test
    public void onFailure() {
    }
}