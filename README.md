# 购物车和订单列表常用适配器（ExpandableListView适配器封装）
##使用教程
###1、在build.gradle文件中，添加仓库链接
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
###2、添加依赖
```
dependencies {
	        compile 'com.github.aositeluoke:DoubleAdapter:v1'
	}
```	
###3、实例化多布局支持对象
```
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
```
###4、初始化适配器
```
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
                   public int getChildrenCount(int groupPosition) {
                       return getGroup(groupPosition).getChilds().size();
                   }

                   @Override
                   public String getChild(int groupPosition, int childPosition) {
                       return getGroup(groupPosition).getChilds().get(childPosition);
                   }
               };
               expandableListView.setAdapter(adapter);
```
###5、实现效果
 ![](https://github.com/aositeluoke/DoubleAdapter/raw/master/gif/multi_group_and_child.gif)<br>
 ![](https://github.com/aositeluoke/DoubleAdapter/raw/master/gif/single_group_and_child.gif)
