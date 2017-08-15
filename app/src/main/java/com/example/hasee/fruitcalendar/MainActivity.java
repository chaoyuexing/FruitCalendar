package com.example.hasee.fruitcalendar;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.fruitcalendar.fragment.category.CategoryFragment;
import com.example.hasee.fruitcalendar.fragment.home.HomePageFragment;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.ko_tab_white));
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.addTab(mTabHost.newTabSpec("homepage").setIndicator(getTabItemView(R.drawable.tab_home_page_btn, "首页")),
                HomePageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("classify").setIndicator(getTabItemView(R.drawable.tab_classify_btn, "分类")),CategoryFragment.class, null);
        mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = (int) getResources().getDimension(R.dimen.tab_height);

    }


    private View getTabItemView(int id, String title) {
        View view = getLayoutInflater().inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(id);
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(title);
        return view;
    }


}
