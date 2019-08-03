package com.aa.enums;

import org.springframework.util.StringUtils;

/**
 * <p>Title: GroupBuyStatusEnum </p>
 * <p>Description: TODO </p>
 * Package: com.pickup.base.enums
 * Copyright: Copyright(c) 2018 zhizhimei All Rights Reserved
 * Company: zhizhimei
 * Product Line: Revenue Integrity
 * Date: 2019/1/24 0024 下午 11:21
 *
 * @author Abel
 * @version SHI_YI_2.0.0 </p>
 * Significant Modifications：
 * Date               Author           Content
 * ==========================================================
 * 2019/1/24 0024      Abel         创建文件,实现基本功能
 * <p>
 * ==========================================================
 */
public enum UserTypeEnum {
    //排序
    Student("Student", "学生"),
    Teacher("Teacher", "老师");
    private String value;
    private String desc;

    UserTypeEnum(String value, String name) {
        this.value = value;
        this.desc = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean validate(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        for (UserTypeEnum e : UserTypeEnum.values()) {
            if (e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
