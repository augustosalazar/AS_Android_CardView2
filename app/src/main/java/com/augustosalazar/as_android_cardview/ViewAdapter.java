package com.augustosalazar.as_android_cardview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>{

    private final Context context;
    private LayoutInflater inflater;
    private List<Information> data = Collections.emptyList();
    private RecyclerClickListner mRecyclerClickListner;

    public ViewAdapter(Context context,List<Information> data){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information information = data.get(position);
        holder.tv.setText(information.title);
        holder.bt.setTag(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setRecyclerClickListner(RecyclerClickListner recyclerClickListner){
        mRecyclerClickListner = recyclerClickListner;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv;
        private Button bt;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv = (TextView) itemView.findViewById(R.id.listText);
            bt = (Button) itemView.findViewById(R.id.button);

        }

        @Override
        public void onClick(View v) {
            if (mRecyclerClickListner != null) {
                mRecyclerClickListner.itemClick(v, getPosition());
            }
        }
    }

    public interface RecyclerClickListner
    {
        public void itemClick(View view, int position);
    }
}
