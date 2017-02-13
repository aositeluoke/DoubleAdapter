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
 * 类描述:多分组布局多子item布局
 * 作者:xues
 * 时间:2017年02月12日
 */

public class MultiActivity extends BaseExpandableListViewActivity {

    //分组布局类型
    private final static int GROUP_VIEW_TYPE0 = 0;
    private final static int GROUP_VIEW_TYPE1 = 1;
    private final static int GROUP_VIEW_TYPE2 = 2;


    //子Item布局类型
    private final static int CHILD_VIEW_TYPE0 = 0;
    private final static int CHILD_VIEW_TYPE1 = 1;
    private final static int CHILD_VIEW_TYPE2 = 2;

    //分组多布局支持
    MultiItemTypeSupportG<GroupDto> groupUI = new MultiItemTypeSupportG<GroupDto>() {
        @Override
        public int getLayoutId(int groupPosition, GroupDto groupDto) {
            //根据后台返回来的标识符返回对应的布局


            //测试使用
            switch ((groupPosition % 3)) {
                case 0:
                    return R.layout.item_group_one;
                case 1:
                    return R.layout.item_group_two;
                default:
                    return R.layout.item_group_three;
            }
        }

        @Override
        public int getViewTypeCount() {
            //布局总数
            return 3;
        }

        @Override
        public int getItemViewType(int groupPosition, GroupDto groupDto) {
            //返回标识符  返回的数值应为0递增到 (getViewTypeCount-1)
            switch ((groupPosition % 3)) {
                case 0:
                    return GROUP_VIEW_TYPE0;
                case 1:
                    return GROUP_VIEW_TYPE1;
                default:
                    return GROUP_VIEW_TYPE2;
            }

        }
    };
    //子Item多布局支持
    MultiItemTypeSupportC<String> childUI = new MultiItemTypeSupportC<String>() {
        @Override
        public int getLayoutId(int groupPosition, int childPosition, String s) {
            //根据后台返回来的标识符返回对应的布局
            //通过adapter获得标识符

            //测试使用
            switch ((childPosition % 3)) {
                case 0:
                    return R.layout.item_child_one;
                case 1:
                    return R.layout.item_child_two;
                default:
                    return R.layout.item_child_three;
            }
        }

        @Override
        public int getViewTypeCount() {
            //布局总数
            return 3;
        }

        @Override
        public int getItemViewType(int groupPosition, int childPosition, String s) {
            //返回标识符  返回的数值应为0递增到 (getViewTypeCount-1)
            switch ((childPosition % 3)) {
                case 0:
                    return CHILD_VIEW_TYPE0;
                case 1:
                    return CHILD_VIEW_TYPE1;
                default:
                    return CHILD_VIEW_TYPE2;
            }
        }
    };

    @Override
    protected void init() {
        setTitle("多分组布局，多子Item布局");
        //实例化适配器
        adapter = new DoubleAdapter<GroupDto, String>(this, groupUI, childUI, expandableListView) {
            @Override
            public int getChildrenCount(int groupPosition) {
                return getGroup(groupPosition).getChilds().size();
            }

            @Override
            public String getChild(int groupPosition, int childPosition) {
                return getGroup(groupPosition).getChilds().get(childPosition);
            }


            @Override
            protected void convertG(BaseAdapterHelper helper, GroupDto item) {
                helper.setText(R.id.tv_shop_name, item.getGroupName());

                //根据布局类型设置对应的数值
                switch (getGroupType(helper.getGroupPosition())) {
                    case GROUP_VIEW_TYPE0:
                        helper.setText(R.id.tv_shop_name, "分组" + helper.getGroupPosition() + " GROUP_VIEW_TYPE0");
                        break;
                    case GROUP_VIEW_TYPE1:
                        helper.setText(R.id.tv_shop_name, "分组" + helper.getGroupPosition() + " GROUP_VIEW_TYPE1");
                        break;
                    case GROUP_VIEW_TYPE2:
                        helper.setText(R.id.tv_shop_name, "分组" + helper.getGroupPosition() + " GROUP_VIEW_TYPE2");
                        break;
                }
            }

            @Override
            protected void convertC(BaseAdapterHelper helper, String item) {
                ImageView imageView = helper.getView(R.id.iv_product);
                ImageLoaderUtil.loadProduct(item, imageView);


                //根据布局类型设置对应的数值
                switch (getChildType(helper.getGroupPosition(), helper.getChildPosition())) {
                    case CHILD_VIEW_TYPE0:
                        helper.setText(R.id.tv_sku, helper.getGroupPosition() + "," + helper.getChildPosition() + "CHILD_VIEW_TYPE0");
                        break;
                    case CHILD_VIEW_TYPE1:
                        helper.setText(R.id.tv_sku, helper.getGroupPosition() + "," + helper.getChildPosition() + "CHILD_VIEW_TYPE1");
                        break;
                    case CHILD_VIEW_TYPE2:
                        helper.setText(R.id.tv_sku, helper.getGroupPosition() + "," + helper.getChildPosition() + "CHILD_VIEW_TYPE2");
                        break;
//                }
                }


            }

        };
        expandableListView.setAdapter(adapter);
        getData();

    }
}
