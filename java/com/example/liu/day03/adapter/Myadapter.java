package com.example.liu.day03.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.liu.day03.API;
import com.example.liu.day03.R;
import com.example.liu.day03.bean.Bannerclass;
import com.example.liu.day03.bean.Beanclass;
import com.example.liu.day03.bean.Fengzhuang;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.LogRecord;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Beanclass.ResultBean.DataBean> list;

    private Xiaoadapter xiaoadapter;


    public Myadapter(Context context, List<Beanclass.ResultBean.DataBean> list, Xiaoadapter xiaoadapter) {
        this.context = context;
        this.list = list;
        this.xiaoadapter = xiaoadapter;

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                View inflate = LayoutInflater.from(context).inflate(R.layout.itemone, null);
                return new oneviewholder(inflate);
            case 1:
                View inflate1 = LayoutInflater.from(context).inflate(R.layout.itemtwo, null);
                return new twoviewholder(inflate1);
            case 2:
                View inflate2 = LayoutInflater.from(context).inflate(R.layout.itemthree, null);
                return new threeviewholder(inflate2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        Beanclass.ResultBean.DataBean d = list.get(i);
        if (viewHolder instanceof oneviewholder) {
            final oneviewholder oneviewholder = (Myadapter.oneviewholder) viewHolder;
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(API.s)
                    .build();
            retrofit.create(API.class).getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Bannerclass>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Bannerclass bannerclass) {
                            ArrayList<String> strings = new ArrayList<>();
                            List<Bannerclass.DataBean> data = bannerclass.getData();
                            for (Bannerclass.DataBean a : data) {
                                Log.e("tag解析Banner", "onError: " + a.getImagePath());
                                strings.add(a.getImagePath());
                            }
                            ((oneviewholder) viewHolder).banner.setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object path, ImageView imageView) {
                                    Glide.with(context).load((String) path).into(imageView);
                                }
                            });
                            ((oneviewholder) viewHolder).banner.setImages(strings);
                            ((oneviewholder) viewHolder).banner.start();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("tag解析Banner", "onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
        if (viewHolder instanceof twoviewholder) {


            twoviewholder twoviewholder = (Myadapter.twoviewholder) viewHolder;
            twoviewholder.rlvtwo.setAdapter(xiaoadapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            twoviewholder.rlvtwo.setLayoutManager(linearLayoutManager);
        }
        if (viewHolder instanceof threeviewholder) {
            threeviewholder threeviewholder = (Myadapter.threeviewholder) viewHolder;
            threeviewholder.tvthree.setText(d.getTitle());
            threeviewholder.tvfour.setText(d.getAuthor_name());
            if (!d.getThumbnail_pic_s().isEmpty()) {
                RoundedCorners roundedCorners = new RoundedCorners(10);
                RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(context).load(d.getThumbnail_pic_s()).apply(requestOptions).into(threeviewholder.ivthree);
                Glide.with(context).load(d.getThumbnail_pic_s02()).apply(requestOptions).into(threeviewholder.ivfour);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class oneviewholder extends RecyclerView.ViewHolder {

        Banner banner;

        public oneviewholder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class twoviewholder extends RecyclerView.ViewHolder {
        RecyclerView rlvtwo;

        public twoviewholder(@NonNull View itemView) {
            super(itemView);
            rlvtwo = itemView.findViewById(R.id.rlvtwo);
        }
    }

    class threeviewholder extends RecyclerView.ViewHolder {

        ImageView ivthree;
        ImageView ivfour;
        TextView tvthree;
        TextView tvfour;

        public threeviewholder(@NonNull View itemView) {
            super(itemView);
            ivthree = itemView.findViewById(R.id.ivthree);
            tvthree = itemView.findViewById(R.id.tvthree);
            ivfour = itemView.findViewById(R.id.ivfour);
            tvfour = itemView.findViewById(R.id.tvfour);
        }
    }
}
