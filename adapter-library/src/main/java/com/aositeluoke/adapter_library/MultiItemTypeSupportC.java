package com.aositeluoke.adapter_library;

/**
 * 子item多种布局支持
 *
 * @param <T>
 */
public interface MultiItemTypeSupportC<T> {
    int getLayoutId(int groupPosition, int childPosition, T t);

    int getViewTypeCount();

    int getItemViewType(int groupPosition, int childPosition, T t);
}