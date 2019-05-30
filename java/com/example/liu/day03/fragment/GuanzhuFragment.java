package com.example.liu.day03.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liu.day03.R;
import com.example.liu.day03.Xiangqing;
import com.example.liu.day03.adapter.Eradapter;
import com.example.liu.day03.adapter.Myadapter;
import com.example.liu.day03.bean.Beanclass;
import com.example.liu.day03.bean.Erclass;
import com.example.liu.day03.bean.Fengzhuang;
import com.example.liu.day03.modol.Impmodol;
import com.example.liu.day03.presenter.Imppresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuanzhuFragment extends Fragment implements com.example.liu.day03.view.View {


    private View view;
    private RecyclerView mRlv;
    private ArrayList<Erclass.ResultBean.DataBean> arr;
    private Eradapter eradapter;

    public GuanzhuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_guanzhu, container, false);
        initView(inflate);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        arr = new ArrayList<>();
        eradapter = new Eradapter(getContext(), arr);
        mRlv.setAdapter(eradapter);
        Imppresenter imppresenter = new Imppresenter(new Impmodol(), this);
        imppresenter.getDatathree();

        eradapter.setOnItemclickListener(new Eradapter.onItemclickListener() {
            @Override
            public void OnItemclick(int position) {
                Erclass.ResultBean.DataBean d = arr.get(position);
                Intent intent = new Intent(getContext(), Xiangqing.class);
                intent.putExtra("url",d.getUrl());
                startActivity(intent);
            }
        });

        return inflate;
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
    }

    @Override
    public void onsuccess(Fengzhuang fengzhuang) {

    }

    @Override
    public void onfail(String s) {

    }

    @Override
    public void onsuccesstwo(Beanclass beanclass) {

    }

    @Override
    public void onfailtwo(String s) {

    }

    @Override
    public void onsuccessthree(final Erclass erclass) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arr.addAll(erclass.getResult().getData());
                eradapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onfailthree(String s) {
        Log.i("tag",s);
    }
}
