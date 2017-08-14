package com.example.hasee.fruitcalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.hasee.fruitcalendar.bean.MainItemBean;
import com.example.hasee.fruitcalendar.util.LoadImageUtils;

import xyz.yhsj.adapter.BaseRecyclerViewAdapter;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * 主页的RecyclerViewAdapter
 * Created by xing on 2017/8/14.
 */

public class GoodsTypeAdapter extends BaseRecyclerViewAdapter<MainItemBean> {

    private ImageView ivCategory;
    private Context mContext;
    private ImageView ivFruit;


    public GoodsTypeAdapter(Context context, RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_main);
        this.mContext = context;
    }



    @Override
    protected void bindData(ViewHolderHelper helper, int i, MainItemBean bean) {
        helper.setText(R.id.main_item_tv_price,"￥"+bean.getPrice());
        helper.setText(R.id.main_item_tv_mass,bean.getMass());
        helper.setText(R.id.main_item_tv_title,bean.getName());
        helper.setText(R.id.main_item_tv_introduce,bean.getContext());
        ivCategory = helper.getImageView(R.id.main_item_iv_category);
        LoadImageUtils.LoadImage(mContext,bean.getIcon(),ivCategory);
        ivFruit = helper.getImageView(R.id.main_item_iv_fruit);
        LoadImageUtils.LoadImage(mContext,bean.getImg(),ivFruit);
    }
}
