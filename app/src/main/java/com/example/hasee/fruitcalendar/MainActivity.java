package com.example.hasee.fruitcalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.hasee.fruitcalendar.bean.BannerBean;
import com.example.hasee.fruitcalendar.http.impl.HttpImpl;
import com.example.hasee.fruitcalendar.http.model.ResponseData;
import com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant;
import com.example.hasee.fruitcalendar.util.LoadImageUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements XBanner.XBannerAdapter {

    @Bind(R.id.main_toobar)
    Toolbar mMainToobar;
    @Bind(R.id.mian_banner)
    XBanner mMianBanner;
    @Bind(R.id.main_recycler_view)
    RecyclerView mMainRecyclerView;
    private HttpImpl mHttpImpl;
    private Context mContext;
    private ArrayList<String> bannerUrlList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHttpImpl = new HttpImpl(this);
        mContext = this;
        mHttpImpl.banner(RequsetUrlConstant.BANNER_URL).subscribe(new Action1<ResponseData<ArrayList<BannerBean>>>() {
            @Override
            public void call(ResponseData<ArrayList<BannerBean>> data) {
                Log.d(getClass().getSimpleName(), data.getData().get(0).getBannerurl());
                for (int i = 0; i < data.getData().size(); i++) {
                    bannerUrlList.add(data.getData().get(i).getBannerurl());
                }
                mMianBanner.setData(bannerUrlList, null);
            }
        });
        mMianBanner.setmAdapter(this);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        LoadImageUtils.LoadImage(mContext, RequsetUrlConstant.BASE_URL + bannerUrlList.get(position), view);
    }
}
