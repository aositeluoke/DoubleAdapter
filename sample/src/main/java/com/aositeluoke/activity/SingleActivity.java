package com.aositeluoke.activity;

import android.widget.ImageView;

import com.aositeluoke.R;
import com.aositeluoke.adapter_library.BaseAdapterHelper;
import com.aositeluoke.adapter_library.DoubleAdapter;
import com.aositeluoke.adapter_library.MultiItemTypeSupportC;
import com.aositeluoke.adapter_library.MultiItemTypeSupportG;
import com.aositeluoke.dto.GroupDto;
import com.aositeluoke.library.image.ImageLoaderUtil;

/**
 * 类描述:分组布局只有一个，子Item布局只有一个的情况
 * 作者:xues
 * 时间:2017年02月12日
 */

public class SingleActivity extends BaseExpandableListViewActivity {


    //分组多布局支持
    MultiItemTypeSupportG<GroupDto> groupUI = new MultiItemTypeSupportG<GroupDto>() {
        @Override
        public int getLayoutId(int position, GroupDto groupDto) {
            //根据后台返回来的标识符返回对应的布局
            return R.layout.item_group_one;
        }

        @Override
        public int getViewTypeCount() {
            //布局总数
            return 1;
        }

        @Override
        public int getItemViewType(int postion, GroupDto groupDto) {
            //返回标识符  返回的数值应为0递增到 (getViewTypeCount-1)
            return 0;
        }
    };
    //子Item多布局支持
    MultiItemTypeSupportC<String> childUI = new MultiItemTypeSupportC<String>() {
        @Override
        public int getLayoutId(int groupPosition, int childPosition, String s) {
            //根据后台返回来的标识符返回对应的布局
            //通过adapter获得标识符
            return R.layout.item_child_one;
        }

        @Override
        public int getViewTypeCount() {
            //布局总数
            return 1;
        }

        @Override
        public int getItemViewType(int groupPosition, int childPosition, String s) {
            //返回标识符  返回的数值应为0递增到 (getViewTypeCount-1)
            return 0;
        }
    };

    @Override
    protected void init() {
        //实例化适配器
        adapter = new DoubleAdapter<GroupDto, String>(this, groupUI, childUI, expandableListView) {
            @Override
            protected void convertG(BaseAdapterHelper helper, GroupDto item) {
                helper.setText(R.id.tv_shop_name, item.getGroupName());
            }

            @Override
            protected void convertC(BaseAdapterHelper helper, String item) {
                ImageView imageView = helper.getView(R.id.iv_product);
                ImageLoaderUtil.loadProduct(item, imageView);
            }

            @Override
            public int getChildrenCount(int i) {

                return getGroup(i).getChilds().size();
            }

            @Override
            public Object getChild(int i, int i1) {
                return getGroup(i).getChilds().get(i1);
            }
        };
        expandableListView.setAdapter(adapter);
        getData();
    }


}
