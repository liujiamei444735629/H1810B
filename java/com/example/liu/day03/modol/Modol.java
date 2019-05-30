package com.example.liu.day03.modol;

import com.example.liu.day03.callback.Mycallback;

public interface Modol {
    void getData(Mycallback mycallback);
    void getDatatwo(Mycallback mycallback);
    void  getDatathree(Mycallback mycallback);
}
