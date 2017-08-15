package com.example.hasee.fruitcalendar.adapter.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.hasee.fruitcalendar.R;
import com.example.hasee.fruitcalendar.bean.CategoryTypeBean;

import xyz.yhsj.adapter.BaseRecyclerViewAdapter;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * Created by xing on 2017/8/15.
 */

public class CategoryTypeAdapter extends BaseRecyclerViewAdapter<CategoryTypeBean> {


    public CategoryTypeAdapter(Context context, RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_categroy_type);
    }

    @Override
    protected void bindData(ViewHolderHelper helper, int i, CategoryTypeBean bean) {
        helper.setText(R.id.category_item_tv_name,bean.getName());
    }

    @Override
    protected void bindItemChildEvent(ViewHolderHelper viewHolderHelper) {
        super.bindItemChildEvent(viewHolderHelper);
        viewHolderHelper.setItemChildClickListener(R.id.category_type_item_ly);
    }
}
