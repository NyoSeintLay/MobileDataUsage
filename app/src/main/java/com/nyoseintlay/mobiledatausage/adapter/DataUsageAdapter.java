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

import java.util.ArrayList;

public class DataUsageAdapter extends RecyclerView.Adapter<DataUsageAdapter.DataUsageViewHolder>{

    private ArrayList<DataUsageByYear> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public DataUsageAdapter(ArrayList<DataUsageByYear> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;

    }


    @Override
    public DataUsageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.data_usage_view, parent, false);
        return new DataUsageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataUsageViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvYear.setText(dataList.get(position).getYear()+"");
        holder.tvVolume.setText(String.format("%.6f", dataList.get(position).getVolume_of_mobile_data_per_year()));
        if(dataList.get(position).getDecreasedVolume())holder.ibDecreasedVolume.setVisibility(View.VISIBLE);
        else holder.ibDecreasedVolume.setVisibility(View.INVISIBLE);


        holder.ibDecreasedVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position).getDataUsageByQuarterArrayList());
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
