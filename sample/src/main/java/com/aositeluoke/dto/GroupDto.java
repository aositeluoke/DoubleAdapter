package com.aositeluoke.dto;

import java.util.List;

/**
 * 类描述:
 * 作者:xues
 * 时间:2017年02月10日
 */

public class GroupDto {
    private String groupName;
    private List<String> childs;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getChilds() {
        return childs;
    }

    public void setChilds(List<String> childs) {
        this.childs = childs;
    }
}
