package com.example.liu.day03.view;

import com.example.liu.day03.bean.Beanclass;
import com.example.liu.day03.bean.Erclass;
import com.example.liu.day03.bean.Fengzhuang;

public interface View {
    void onsuccess(Fengzhuang fengzhuang);
    void onfail(String s);
    void onsuccesstwo(Beanclass beanclass);
    void onfailtwo(String s);
    void onsuccessthree(Erclass erclass);
    void onfailthree(String s);
}
