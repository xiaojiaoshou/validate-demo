package com.aa.vo;

import com.aa.annotation.ValidateEnumAnnotation;
import com.aa.enums.UserTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

/**
 * @descriotion 用户信息
 * @author ghx
 * @date 2019/7/12
 */

@Data
public class UserVoJoin {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private Integer sex;

    @NotNull(message = "年龄不能为空！")
    @Range(min = 18, max = 108, message = "年龄必须在18岁-108岁之间")
    private Integer age;
    @NotNull(message = "地址不能为空！")
    private String address;
    @NotNull(message = "电话号码不能为空！")
    private String phone;

    private String studentNum;

    private String type;
    @NotNull(message = "classes不能为空")
    @Valid  //嵌套校验
    /**
     * ConvertGroup(from=Fourth.class, to=Classes.First.class) 的含义是进行级联的分组
     * 当校验类型是Fourth 时 ，匹配 classes 里面的分组 Classes.First.class,
     */
    // @ConvertGroup(from = UserVo1.Fourth.class, to = Classes.First.class)
    private Classes classes;


    public interface First {
    }

    ;



}
