package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nyoseintlay.mobiledatausage.adapter.DataUsageAdapter;
import com.nyoseintlay.mobiledatausage.R;
import com.nyoseintlay.mobiledatausage.model.DataUsageRaw;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MainActivityInterface.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private MainActivityInterface.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();


        presenter = new MainActivityPresenter(this, new GetDataUsageIntractorImpl());
        presenter.requestDataFromServer();
    }

    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_data_usage_list);
       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);

    }


    /**
     * Initializing progressbar programmatically
     * */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(ArrayList<DataUsageRaw> dataUsageRawArrayList) {
            String data="\n";
            for(DataUsageRaw dataUsageRaw:dataUsageRawArrayList){
                data += dataUsageRaw.getQuarter()+" - "+ dataUsageRaw.getVolume_of_mobile_data()+"\n";
            }
            Toast.makeText(MainActivity.this, data , Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(HashMap<Integer,ArrayList<DataUsageRaw>> dataUsageHashMap) {
        DataUsageAdapter adapter = new DataUsageAdapter(dataUsageHashMap, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
