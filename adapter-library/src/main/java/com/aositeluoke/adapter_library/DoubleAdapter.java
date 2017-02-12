package com.aositeluoke.adapter_library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import static com.aositeluoke.adapter_library.BaseAdapterHelper.get;


/**
 * 类描述:ExpandableListView适配器基类
 * 作者:xues
 * 时间:2017年02月07日
 */

public abstract class DoubleAdapter<T, C> extends BaseDoubleAdapter<T, C, BaseAdapterHelper> {


    public DoubleAdapter(Context context, int layoutResIdG, int layoutResIdC, ExpandableListView mExpandableListView) {
        super(context, layoutResIdG, layoutResIdC, mExpandableListView);
    }

    public DoubleAdapter(Context context, MultiItemTypeSupportG<T> multiItemSupportG, MultiItemTypeSupportC<C> multiItemSupportC, ExpandableListView mExpandableListView) {
        super(context, multiItemSupportG, multiItemSupportC,mExpandableListView);
    }

    @Override
    protected BaseAdapterHelper getAdapterHelper(int groupPosition, int childPosition, View convertView, ViewGroup parent) {
        if (mMultiItemSupportC != null) {
            return get(context, convertView, parent,
                    mMultiItemSupportC.getLayoutId(groupPosition, childPosition, ((C) getChild(groupPosition, childPosition))),groupPosition,childPosition);
        } else {
            return get(context, convertView, parent, layoutResIdC,groupPosition,childPosition);
        }
    }

    @Override
    protected BaseAdapterHelper getAdapterHelper(int groupPosition, View convertView, ViewGroup parent) {
        if (mMultiItemSupportG != null) {
            return get(context, convertView, parent,
                    mMultiItemSupportG.getLayoutId(groupPosition, getGroup(groupPosition)),groupPosition);
        } else {
            return get(context, convertView, parent, layoutResIdG,groupPosition);
        }
    }
}
