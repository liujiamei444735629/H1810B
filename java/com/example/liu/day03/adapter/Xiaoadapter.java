package com.example.liu.day03.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liu.day03.R;
import com.example.liu.day03.bean.Fengzhuang;

import java.util.List;

public class Xiaoadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Fengzhuang.ResultBean> list;

    public Xiaoadapter(Context context, List<Fengzhuang.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.itemtwotwo, null);
        return new Oneviewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Fengzhuang.ResultBean d = list.get(i);
        Oneviewholder  oneviewholder= (Oneviewholder) viewHolder;
        oneviewholder.tvoneone.setText(d.getTitle());
        if (!d.getPic().isEmpty()){
            Glide.with(context).load(d.getPic()).into(oneviewholder.ivoneone);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Oneviewholder extends RecyclerView.ViewHolder{

        ImageView ivoneone;
        TextView tvoneone;

        public Oneviewholder(@NonNull View itemView) {
            super(itemView);
            ivoneone = itemView.findViewById(R.id.ivoneone);
            tvoneone = itemView.findViewById(R.id.tvoneone);
        }
    }
}
