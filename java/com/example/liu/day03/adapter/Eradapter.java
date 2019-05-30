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
import com.bumptech.glide.request.RequestOptions;
import com.example.liu.day03.R;
import com.example.liu.day03.bean.Erclass;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class Eradapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Erclass.ResultBean.DataBean> list;
    private onItemclickListener onItemclickListener;

    public void setOnItemclickListener(Eradapter.onItemclickListener onItemclickListener) {
        this.onItemclickListener = onItemclickListener;
    }

    public Eradapter(Context context, List<Erclass.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new oneviewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Erclass.ResultBean.DataBean d = list.get(i);
        oneviewholder oneviewholder= (Eradapter.oneviewholder) viewHolder;
        oneviewholder.tvone.setText(d.getTitle());
        if (!d.getThumbnail_pic_s().isEmpty()){
            RequestOptions requestOptions = RequestOptions.circleCropTransform().circleCrop();
            Glide.with(context).load(d.getThumbnail_pic_s()).apply(requestOptions).into(oneviewholder.ivone);
            Glide.with(context).load(d.getThumbnail_pic_s()).into(oneviewholder.ivoneone);

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemclickListener!=null){
                    onItemclickListener.OnItemclick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class oneviewholder extends RecyclerView.ViewHolder{

        ImageView ivone;
        ImageView ivoneone;
        TextView tvone;


        public oneviewholder(@NonNull View itemView) {
            super(itemView);
            ivone = itemView.findViewById(R.id.ivone);
            ivoneone = itemView.findViewById(R.id.ivoneone);
            tvone = itemView.findViewById(R.id.tvone);
        }
    }
    public interface onItemclickListener{
        void OnItemclick(int position);
    }
}
