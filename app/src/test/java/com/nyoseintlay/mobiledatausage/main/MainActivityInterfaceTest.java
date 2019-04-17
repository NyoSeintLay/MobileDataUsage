package com.nyoseintlay.mobiledatausage.main;

import org.junit.Before;
import org.junit.Test;


public class MainActivityInterfaceTest {

    MainActivityInterface mainActivityInterface;
    MainActivityInterface.presenter presenter;
    MainActivityInterface.MainView mainView;
    MainActivityInterface.GetDataUsageIntractor getDataUsageIntractor;

    @Before
    public void setUp() throws Exception {
        getDataUsageIntractor = new GetDataUsageIntractorImpl();
    }

    @Test
    public void testGetDataUsageIntractor(){

    }
}