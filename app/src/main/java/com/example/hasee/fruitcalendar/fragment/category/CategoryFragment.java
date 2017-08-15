package com.example.hasee.fruitcalendar.fragment.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.fruitcalendar.R;
import com.example.hasee.fruitcalendar.adapter.category.CategoryGoodsAdapter;
import com.example.hasee.fruitcalendar.adapter.category.CategoryTypeAdapter;
import com.example.hasee.fruitcalendar.bean.CategoryTypeBean;
import com.example.hasee.fruitcalendar.bean.GoodsBean;
import com.example.hasee.fruitcalendar.http.impl.HttpImpl;
import com.example.hasee.fruitcalendar.http.model.ResponseData;
import com.example.hasee.fruitcalendar.view.LoadMoreRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;
import xyz.yhsj.event.OnItemChildClickListener;

import static com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant.GOODS_LIST;
import static com.example.hasee.fruitcalendar.http.request.RequsetUrlConstant.GOOD_TYPE_URL;

/**
 * 分类fragment
 * Created by xing on 2017/8/15.
 */

public class CategoryFragment extends Fragment implements OnItemChildClickListener {


    @Bind(R.id.category_type_recyler_view)
    RecyclerView mCategoryTypeRecylerView;
    @Bind(R.id.category_list_recyler_view)
    LoadMoreRecyclerView mCategoryListRecylerView;

    private HttpImpl mHttpImpl;
    private Context mContext;
    private CategoryTypeAdapter mTypeAdapter;
    private CategoryGoodsAdapter mGoodsAdapter;
    private ArrayList<CategoryTypeBean> typeList = new ArrayList<CategoryTypeBean>();
    private ArrayList<GoodsBean> mGoodsBeanArrayList = new ArrayList<GoodsBean>();
    private int goodsListPageIndex = 1;
    private int type;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_category, container, false);
            ButterKnife.bind(this, view);
            mContext = getActivity();
            mHttpImpl = new HttpImpl(mContext);
            mTypeAdapter = new CategoryTypeAdapter(mContext, mCategoryTypeRecylerView);
            mCategoryTypeRecylerView.setAdapter(mTypeAdapter);
            mTypeAdapter.setOnItemChildClickListener(this);
            mGoodsAdapter = new CategoryGoodsAdapter(mContext, mCategoryListRecylerView);
            mCategoryListRecylerView.setAdapter(mGoodsAdapter);
            initTypeData();
            loadMoreData();
        } else {
            return view;
        }
        return view;
    }

    /**
     * 获取分类数据
     */
    private void initTypeData() {
        mHttpImpl.categoryType(GOOD_TYPE_URL).subscribe(new Action1<ResponseData<ArrayList<CategoryTypeBean>>>() {
            @Override
            public void call(ResponseData<ArrayList<CategoryTypeBean>> data) {
                int dataSize = data.getData().size();
                typeList.clear();
                for (int i = 0; i < dataSize; i++) {
                    typeList.add(data.getData().get(i));
                }
                mTypeAdapter.setDatas(typeList);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemChildClick(ViewGroup group, final View view, int i) {
        if (view.getId() == R.id.category_type_item_ly) {
            goodsListPageIndex = 1;
            if (mGoodsBeanArrayList != null) {
                mGoodsBeanArrayList.clear();
            } else {
                mGoodsBeanArrayList = new ArrayList<GoodsBean>();
            }
            type = typeList.get(i).getId();
            mHttpImpl.categoryGoods(GOODS_LIST, goodsListPageIndex + "", type+ "").subscribe(new Action1<ResponseData<ArrayList<GoodsBean>>>() {
                @Override
                public void call(ResponseData<ArrayList<GoodsBean>> data) {
                    mGoodsBeanArrayList = data.getData();
                    mGoodsAdapter.setDatas(mGoodsBeanArrayList);
                }
            });
        }
    }


    private void loadMoreData() {
        mCategoryListRecylerView.setLoadingData(new LoadMoreRecyclerView.LoadingData() {
            @Override
            public void onLoadMore() {
                goodsListPageIndex++;
                mHttpImpl.categoryGoods(GOODS_LIST, goodsListPageIndex + "", type + "").subscribe(new Action1<ResponseData<ArrayList<GoodsBean>>>() {
                    @Override
                    public void call(ResponseData<ArrayList<GoodsBean>> data) {
                        for (int i = 0; i < data.getData().size(); i++) {
                            mGoodsBeanArrayList.add(data.getData().get(i));
                        }
                        mGoodsAdapter.setDatas(mGoodsBeanArrayList);
                    }
                });
            }
        });
    }



}
