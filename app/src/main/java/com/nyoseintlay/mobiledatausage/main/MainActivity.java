package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nyoseintlay.mobiledatausage.adapter.DataUsageAdapter;
import com.nyoseintlay.mobiledatausage.R;
import com.nyoseintlay.mobiledatausage.helper.DatabaseHelper;
import com.nyoseintlay.mobiledatausage.model.DataUsageByQuarter;
import com.nyoseintlay.mobiledatausage.model.DataUsageByYear;
import com.nyoseintlay.mobiledatausage.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityInterface.MainView {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private MainActivityInterface.presenter presenter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbarAndRecyclerView();
        initProgressBar();
        initDatabaseHelper();

        presenter = new MainActivityPresenter(this, new GetDataUsageIntractorImpl());
        presenter.requestDataFromServer(Utils.isNetworkAvailable(this),databaseHelper);
    }

    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_data_usage_list);
       GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);

    }

    /**
     * Initializing progressbar
     * */
    private void initProgressBar()
    {
        progressBar = findViewById(R.id.progressBar);
    }

    /**
     * Initializing Database Helper
     */
    private void initDatabaseHelper(){
        databaseHelper = new DatabaseHelper(this);
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(ArrayList<DataUsageByQuarter> dataUsageByQuarterArrayList) {
            String data="\n";
            for(DataUsageByQuarter dataUsageByQuarter : dataUsageByQuarterArrayList){
                data += dataUsageByQuarter.getQuarter()+" - "+ dataUsageByQuarter.getVolume_of_mobile_data()+"\n";
            }
            Toast.makeText(MainActivity.this, data , Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public void showProgress() {
       if(progressBar!=null)progressBar.setVisibility(View.VISIBLE);
       else{
           initProgressBar();
           progressBar.setVisibility(View.VISIBLE);
       }
    }
    @Override
    public void removeProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<DataUsageByYear> dataUsage) {
        DataUsageAdapter adapter = new DataUsageAdapter(dataUsage, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(this,getString(R.string.dataFailure),Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.onRefreshImageButtonClick(Utils.isNetworkAvailable(getBaseContext()),databaseHelper);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
