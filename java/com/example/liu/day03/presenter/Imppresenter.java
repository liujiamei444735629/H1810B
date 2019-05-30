package com.example.liu.day03.presenter;

import com.example.liu.day03.bean.Beanclass;
import com.example.liu.day03.bean.Erclass;
import com.example.liu.day03.bean.Fengzhuang;
import com.example.liu.day03.callback.Mycallback;
import com.example.liu.day03.modol.Modol;
import com.example.liu.day03.view.View;

public class Imppresenter implements Presenter,Mycallback {
    private Modol modol;
    private View view;

    public Imppresenter(Modol modol, View view) {
        this.modol = modol;
        this.view = view;
    }

    @Override
    public void getData() {
        if (modol!=null){
            modol.getData(this);
        }
    }

    @Override
    public void getDatatwo() {
        if (modol!=null){
            modol.getDatatwo(this);
        }
    }

    @Override
    public void getDatathree() {
        if (modol!=null){
            modol.getDatathree(this);
        }
    }

    @Override
    public void onsuccess(Fengzhuang fengzhuang) {
        if (view!=null){
            view.onsuccess(fengzhuang);
        }
    }

    @Override
    public void onfail(String s) {
        if (view!=null){
            view.onfail(s);
        }
    }

    @Override
    public void onsuccesstwo(Beanclass beanclass) {
        if (view!=null){
            view.onsuccesstwo(beanclass);
        }
    }

    @Override
    public void onfailtwo(String s) {
        if (view!=null){
            view.onfailtwo(s);
        }
    }

    @Override
    public void onsuccessthree(Erclass erclass) {
        if (view!=null){
            view.onsuccessthree(erclass);
        }
    }

    @Override
    public void onfailthree(String s) {
        if (view!=null){
            view.onfailthree(s);
        }
    }
}
