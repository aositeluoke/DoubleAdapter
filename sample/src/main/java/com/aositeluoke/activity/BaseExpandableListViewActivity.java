package com.aositeluoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.aositeluoke.R;
import com.aositeluoke.adapter_library.DoubleAdapter;
import com.aositeluoke.dto.GroupDto;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类描述:
 * 作者:xues
 * 时间:2017年02月12日
 */

public abstract class BaseExpandableListViewActivity extends AppCompatActivity {
    protected ExpandableListView expandableListView;
    protected SwipyRefreshLayout refreshLayout;
    protected DoubleAdapter<GroupDto, String> adapter;
    protected List<String> mImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        //获取图片链接
        mImages = Arrays.asList(getResources().getStringArray(R.array.images));
        expandableListView = findViewByid(R.id.expandableListView);
        refreshLayout = ((SwipyRefreshLayout) findViewById(R.id.refreshLayout));
        refreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    adapter.clear();
                    getData();
                } else {
                    getData();
                }
            }
        });
        //去掉下拉箭头
        expandableListView.setGroupIndicator(null);
        //不能点击收缩：
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
        init();

    }

    protected abstract void init();


    protected <T extends View> T findViewByid(int id) {
        return (T) findViewById(id);
    }

    protected void getData() {
        final List<GroupDto> data = new ArrayList<>();
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = adapter.getGroupCount(); i < adapter.getGroupCount() + 10; i++) {
                    GroupDto groupDto = new GroupDto();
                    List<String> childData = new ArrayList<>();
                    groupDto.setGroupName("京东自营" + i);
                    groupDto.setChilds(childData);
                    data.add(groupDto);
                    for (int j = 0; j < 6; j++) {
                        childData.add(getImages());
                    }

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(data);
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        }, 1000);


    }


    protected String getImages() {
        int index = (int) (Math.random() * mImages.size());
        return mImages.get(index);
    }
}
