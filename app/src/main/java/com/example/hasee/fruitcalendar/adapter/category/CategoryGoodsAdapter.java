package com.example.hasee.fruitcalendar.adapter.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.hasee.fruitcalendar.R;
import com.example.hasee.fruitcalendar.bean.GoodsBean;
import com.example.hasee.fruitcalendar.util.LoadImageUtils;

import xyz.yhsj.adapter.BaseRecyclerViewAdapter;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * Created by xing on 2017/8/15.
 */

public class CategoryGoodsAdapter extends BaseRecyclerViewAdapter<GoodsBean>{

    private ImageView ivFruit;



    public CategoryGoodsAdapter(Context context, RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_categroy_goods);
    }

    @Override
    protected void bindData(ViewHolderHelper helper, int i, GoodsBean bean) {
        helper.setText(R.id.category_item_tv_price,"￥"+bean.getPrice());
        helper.setText(R.id.category_item_tv_title,bean.getName());
        helper.setText(R.id.category_item_tv_introduce,bean.getContext());
        ivFruit = helper.getImageView(R.id.category_item_iv_fruit);
        LoadImageUtils.LoadImage(mContext,bean.getImg(),ivFruit);
    }
}
