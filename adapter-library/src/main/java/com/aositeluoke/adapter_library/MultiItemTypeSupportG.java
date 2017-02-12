package com.aositeluoke.adapter_library;

/**
 * 分组多布局支持
 *
 * @param <T>
 */
public interface MultiItemTypeSupportG<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);
}