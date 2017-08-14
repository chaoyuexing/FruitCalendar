package com.example.hasee.fruitcalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.hasee.fruitcalendar.bean.BannerBean;
import com.example.hasee.fruitcalendar.bean.GoodsTypeBean;
import com.example.hasee.fruitcalendar.bean.MainItemBean;
import com.example.hasee.fruitcalendar.bean.RecommendBean;
import com.example.hasee.fruitcalendar.http.impl.HttpImpl;
import com.example.hasee.fruitcalendar.http.model.ResponseData;
import com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant;
import com.example.hasee.fruitcalendar.util.LoadImageUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

import static com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant.GOODS_TYPE_URL;
import static com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant.RECOMMEND_URL;

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
    private ArrayList<MainItemBean> itemDataList = new ArrayList<MainItemBean>();
    private ArrayList<GoodsTypeBean> tyepList = new ArrayList<GoodsTypeBean>();
    private GoodsTypeAdapter mGoodsTypeAdapter;
    private String icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHttpImpl = new HttpImpl(this);
        mContext = this;
        initBanner();
        initItemData();
        mGoodsTypeAdapter = new GoodsTypeAdapter(this, mMainRecyclerView);
        mMainRecyclerView.setAdapter(mGoodsTypeAdapter);
    }

    /**
     * banner 在这里
     */
    private void initBanner() {
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


    /**
     * banner 显示接口的实现
     * @param banner
     * @param model
     * @param view
     * @param position
     */
    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        LoadImageUtils.LoadImage(mContext, bannerUrlList.get(position), view);
    }

    public void initItemData() {
        mHttpImpl.goodsTypee(GOODS_TYPE_URL).subscribe(new Action1<ResponseData<ArrayList<GoodsTypeBean>>>() {
           @Override
           public void call(ResponseData<ArrayList<GoodsTypeBean>> data) {
               for (int i = 0; i < data.getData().size(); i++) {
                   tyepList.add(data.getData().get(i));
                   mHttpImpl.recommend(RECOMMEND_URL,data.getData().get(i).getId()+"").subscribe(new Action1<ResponseData<ArrayList<RecommendBean>>>() {
                       @Override
                       public void call(ResponseData<ArrayList<RecommendBean>> data) {
                           MainItemBean itemBean = new MainItemBean();
                           for (int i = 0; i <tyepList.size();i++) {
                               if (data.getData().get(0).getFgoodstype().equals(tyepList.get(i).getId()+"")) {
                                   icon = tyepList.get(i).getIcon();
                               }
                           }
                           itemBean.setContext(data.getData().get(0).getGoodsBean().getContext());
                           itemBean.setImg(data.getData().get(0).getGoodsBean().getImg());
                           itemBean.setMass(data.getData().get(0).getGoodsBean().getMass());
                           itemBean.setName(data.getData().get(0).getGoodsBean().getName());
                           itemBean.setPrice(data.getData().get(0).getGoodsBean().getPrice());
                           itemBean.setIcon(icon);
                           itemDataList.add(itemBean);
                           itemBean = null;
                       }
                   });
               }
               mGoodsTypeAdapter.setDatas(itemDataList);
           }
       });

    }
}
