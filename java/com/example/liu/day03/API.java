package com.example.liu.day03;

import com.example.liu.day03.bean.Bannerclass;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {
    String s="https://www.wanandroid.com/";
    @GET("banner/json")
    Observable<Bannerclass> getData();
}
