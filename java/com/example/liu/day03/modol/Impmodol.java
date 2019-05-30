package com.example.liu.day03.modol;

import android.util.Log;

import com.example.liu.day03.bean.Beanclass;
import com.example.liu.day03.bean.Erclass;
import com.example.liu.day03.bean.Fengzhuang;
import com.example.liu.day03.callback.Mycallback;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Impmodol implements Modol{
    @Override
    public void getData(final Mycallback mycallback) {
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url("http://api.juheapi.com/japi/toh?v=1.0&month=3&day=15&key=8a5f533bc3b94f536c679e93bd38930b")
                .build();
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mycallback.onfail(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Fengzhuang fengzhuang = new Gson().fromJson(string, Fengzhuang.class);
                mycallback.onsuccess(fengzhuang);
            }
        });
    }

    @Override
    public void getDatatwo(final Mycallback mycallback) {
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url("http://v.juhe.cn/toutiao/index?type=guoji&key=4c4826357bfa9a983038c2a61071f1a5")
                .build();
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mycallback.onfailtwo(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Beanclass fengzhuang = new Gson().fromJson(string, Beanclass.class);
                mycallback.onsuccesstwo(fengzhuang);
            }
        });
    }

    @Override
    public void getDatathree(final Mycallback mycallback) {
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url("http://v.juhe.cn/toutiao/index?type=guoji&key=4c4826357bfa9a983038c2a61071f1a5")
                .build();
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mycallback.onfailthree(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Erclass erclass = new Gson().fromJson(string, Erclass.class);
                mycallback.onsuccessthree(erclass);
            }
        });
    }
}
