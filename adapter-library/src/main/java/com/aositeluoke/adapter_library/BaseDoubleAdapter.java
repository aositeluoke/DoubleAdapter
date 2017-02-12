/**
 * Copyright 2013 Joan Zapata
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aositeluoke.adapter_library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction class of a BaseAdapter in which you only need to provide the convert() implementation.<br/>
 * Using the provided BaseAdapterHelper, your code is minimalist.
 *
 * @param <G> The type of the items in the list.
 */
public abstract class BaseDoubleAdapter<G, C, H extends BaseAdapterHelper> extends BaseExpandableListAdapter {

    protected static final String TAG = BaseDoubleAdapter.class.getSimpleName();
    protected final Context context;
    protected ExpandableListView mExpandableListView;
    /**
     * 分组布局
     */
    protected int layoutResIdG;
    /**
     * 子item布局
     */
    protected int layoutResIdC;

    protected final List<G> data;


    /**
     * 分组多布局
     */
    protected MultiItemTypeSupportG<G> mMultiItemSupportG;
    /**
     * 子item多布局
     */
    protected MultiItemTypeSupportC<C> mMultiItemSupportC;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with some initialization data.
     *
     * @param context      The context.
     * @param layoutResIdG The layout resource id of each item.
     */
    public BaseDoubleAdapter(Context context, int layoutResIdG, int layoutResIdC, ExpandableListView mExpandableListView) {
        this.data = new ArrayList<G>();
        this.context = context;
        this.layoutResIdG = layoutResIdG;
        this.layoutResIdC = layoutResIdC;
        this.mExpandableListView = mExpandableListView;
    }


    /**
     * 多类型构造器
     *
     * @param context
     * @param multiItemSupportG
     * @param multiItemSupportC
     */
    public BaseDoubleAdapter(Context context,
                             MultiItemTypeSupportG<G> multiItemSupportG, MultiItemTypeSupportC<C> multiItemSupportC, ExpandableListView mExpandableListView) {
        this.mMultiItemSupportG = multiItemSupportG;
        this.mMultiItemSupportC = multiItemSupportC;
        this.mExpandableListView = mExpandableListView;
        this.data = new ArrayList<G>();
        this.context = context;
    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convertG(H helper, G item);

    protected abstract void convertC(H helper, C item);


    protected abstract H getAdapterHelper(int position, View convertView, ViewGroup parent);

    protected abstract H getAdapterHelper(int groupPosition, int position, View convertView, ViewGroup parent);


    //==========================================GroupItem==========================================
    @Override
    public int getGroupCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getGroupType(int groupPosition) {
        return mMultiItemSupportG.getItemViewType(groupPosition, getGroup(groupPosition));
    }

    @Override
    public int getGroupTypeCount() {
        if (mMultiItemSupportG != null)
            return mMultiItemSupportG.getViewTypeCount();
        return 1;
    }

    @Override
    public G getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final H helper = getAdapterHelper(groupPosition, convertView, parent);
        G item = getGroup(groupPosition);
        helper.setAssociatedObject(item);
        convertG(helper, item);
        return helper.getView();
    }


    //================================子Item======================================
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return data.get(groupPosition).getGoodsList().size();
//    }
//


    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return mMultiItemSupportC.getItemViewType(groupPosition, childPosition, ((C) getChild(groupPosition, childPosition)));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public int getChildTypeCount() {
        if (mMultiItemSupportC != null)
            return mMultiItemSupportC.getViewTypeCount();
        return 1;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final H helper = getAdapterHelper(groupPosition, childPosition, convertView, parent);
        C item = ((C) getChild(groupPosition, childPosition));
        helper.setAssociatedObject(item);
        convertC(helper, item);
        return helper.getView();
    }


    //=================公共方法============================
    @Override
    public boolean hasStableIds() {
        return false;
    }


    //子item可点击
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    //默认全部展开
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (mExpandableListView != null)
            for (int i = 0; i < data.size(); i++) {
                mExpandableListView.expandGroup(i);
            }
    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return super.getCombinedChildId(groupId, childId);
    }


    public void add(G elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<G> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(G oldElem, G newElem) {
        set(data.indexOf(oldElem), newElem);
    }

    public void set(int index, G elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(G elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<G> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(G elem) {
        return data.contains(elem);
    }

    /**
     * Clear data list
     */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }


    public List<G> getData() {
        return data;
    }


}
