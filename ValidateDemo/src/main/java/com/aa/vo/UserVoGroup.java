package com.aa.vo;

import com.aa.annotation.ValidateEnumAnnotation;
import com.aa.enums.UserTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @descriotion 用户信息
 * @author ghx
 * @date 2019/7/12
 */

@Data
public class UserVoGroup {
    @NotBlank(message = "用户名不能为空", groups = First.class)
    private String userName;
    private Integer sex;

    @NotNull(message = "年龄不能为空！", groups = Second.class)
    @Range(min = 18, max = 108, message = "年龄必须在18岁-108岁之间", groups = Second.class)
    private Integer age;
    @NotNull(message = "地址不能为空！", groups = {First.class, Second.class})
    private String address;
    @NotNull(message = "电话号码不能为空！")
    private String phone;

    private String studentNum;
    @ValidateEnumAnnotation(target = UserTypeEnum.class, message = "type类型错误", groups = {First.class})
    private String type;
    private Classes classes;


    public interface First {
    }

    ;

    public interface Second {
    }

    ;


}
