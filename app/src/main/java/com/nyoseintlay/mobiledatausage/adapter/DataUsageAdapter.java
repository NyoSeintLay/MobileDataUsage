package com.nyoseintlay.mobiledatausage.adapter;
/**
 * Created by NyoSeint Lay on 17/04/19.
 */

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nyoseintlay.mobiledatausage.R;
import com.nyoseintlay.mobiledatausage.main.RecyclerItemClickListener;
import com.nyoseintlay.mobiledatausage.model.DataUsageByYear;
import com.nyoseintlay.mobiledatausage.model.DataUsageRaw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DataUsageAdapter extends RecyclerView.Adapter<DataUsageAdapter.DataUsageViewHolder>{

    private HashMap<Integer,ArrayList<DataUsageRaw>> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;
    private ArrayList<DataUsageByYear> dataUsageByYearArrayList = new ArrayList<>();

    public DataUsageAdapter(HashMap<Integer,ArrayList<DataUsageRaw>> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        setUpDataUsageByYear();

    }

    private void setUpDataUsageByYear(){
        ArrayList<Integer> key = new ArrayList<>(this.dataList.keySet());
        Collections.sort(key);
        Double total_volume_per_year = 0.0;
        for(int i=0;i<dataList.size();i++) {
            Boolean decreasedVolume = false;
            for (int j = 0; j < dataList.get(key.get(i)).size(); j++) {
                total_volume_per_year += dataList.get(key.get(i)).get(j).getVolume_of_mobile_data();
                if(j!=0 && dataList.get(key.get(i)).get(j-1).getVolume_of_mobile_data()>dataList.get(key.get(i)).get(j).getVolume_of_mobile_data()) decreasedVolume=true;
            }
            dataUsageByYearArrayList.add(new DataUsageByYear(key.get(i).toString(),total_volume_per_year,decreasedVolume));
        }
    }


    @Override
    public DataUsageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.data_usage_view, parent, false);
        return new DataUsageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataUsageViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvYear.setText(dataUsageByYearArrayList.get(position).getYear());
        holder.tvVolume.setText(String.format("%.6f", dataUsageByYearArrayList.get(position).getVolume_of_mobile_data_per_year()));
        if(dataUsageByYearArrayList.get(position).getDecreasedVolume())holder.ibDecreasedVolume.setVisibility(View.VISIBLE);
        else holder.ibDecreasedVolume.setVisibility(View.INVISIBLE);


        holder.ibDecreasedVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(Integer.parseInt(dataUsageByYearArrayList.get(position).getYear())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataUsageViewHolder extends RecyclerView.ViewHolder {

        TextView tvYear, tvVolume;
        ImageButton ibDecreasedVolume;

        DataUsageViewHolder(View itemView) {
            super(itemView);
            tvYear =  itemView.findViewById(R.id.tvYear);
            tvVolume =  itemView.findViewById(R.id.tvVolume);
            ibDecreasedVolume =  itemView.findViewById(R.id.ibDecreasedVolume);

        }
    }
}
