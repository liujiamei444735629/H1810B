package com.example.liu.day03.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liu.day03.R;
import com.example.liu.day03.adapter.Myadapter;
import com.example.liu.day03.adapter.Xiaoadapter;
import com.example.liu.day03.bean.Beanclass;
import com.example.liu.day03.bean.Erclass;
import com.example.liu.day03.bean.Fengzhuang;
import com.example.liu.day03.modol.Impmodol;
import com.example.liu.day03.presenter.Imppresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaomenFragment extends Fragment implements com.example.liu.day03.view.View {


    private View view;
    private RecyclerView mRlv;
    private ArrayList<Fengzhuang.ResultBean> arr;
    private ArrayList<Beanclass.ResultBean.DataBean> ar;
    private Myadapter myadapter1;
    private Xiaoadapter xiaoadapter;

    public XiaomenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_xiaomen, container, false);
        initView(inflate);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRlv.setLayoutManager(linearLayoutManager);

        arr = new ArrayList<>();
        Imppresenter imppresenter = new Imppresenter(new Impmodol(), this);

        ar = new ArrayList<>();
        xiaoadapter = new Xiaoadapter(getContext(), arr);
        myadapter1 = new Myadapter(getContext(), ar,xiaoadapter);
        mRlv.setAdapter(myadapter1);

        imppresenter.getData();
        imppresenter.getDatatwo();
        return inflate;
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
    }

    @Override
    public void onsuccess(final Fengzhuang fengzhuang) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arr.addAll(fengzhuang.getResult());
                xiaoadapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onfail(String s) {
        Log.i("tag",s);
    }

    @Override
    public void onsuccesstwo(final Beanclass beanclass) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ar.addAll(beanclass.getResult().getData());
                myadapter1.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onfailtwo(String s) {
        Log.i("tag",s);
    }

    @Override
    public void onsuccessthree(Erclass erclass) {

    }

    @Override
    public void onfailthree(String s) {

    }
}
