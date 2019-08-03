package com.aa.vo;

import com.aa.annotation.ValidateEnumAnnotation;
import com.aa.enums.UserTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.util.StringUtils;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.ConvertGroup;

/**
 * @descriotion 用户信息
 * @author ghx
 * @date 2019/7/12
 */

@Data
public class UserVo {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private Integer sex;

    @NotNull(message = "年龄不能为空！")
    @Range(min = 18, max = 108, message = "年龄必须在18岁-108岁之间")
    private Integer age;

    private String address;
    private String phone;

    private String studentNum;

    private String type;
    private Classes classes;


}
