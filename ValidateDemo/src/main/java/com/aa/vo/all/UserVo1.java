package com.aa.vo.all;

import com.aa.annotation.ValidateEnumAnnotation;
import com.aa.enums.UserTypeEnum;
import com.aa.vo.Classes;
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
//顺序验证
//@GroupSequence({UserVo1.First.class, UserVo1.Second.class, UserVo1.class})
@Data
//脚本校验
@ScriptAssert(lang = "javascript",
        script = "com.aa.vo.all.UserVo1.checkParams(_this.type,_this.phone)",
        message = "角色为老师时必须传入电话号码！",
        groups = UserVo1.Third.class)
public class UserVo1 {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private Integer sex;
    @NotNull(message = "年龄不能为空")

    @NotNull(message = "年龄不能为空！")
    @Range(min = 18, max = 108, message = "年龄必须在18岁-108岁之间")
    private Integer age;
    /**
     * 分组校验
     */
    @NotBlank(message = "address不能为空", groups = First.class)
    private String address;
    private String phone;

    @NotNull(message = "classes不能为空")
    @Valid  //嵌套校验
    /**
     * ConvertGroup(from=Fourth.class, to=Classes.First.class) 的含义是进行级联的分组
     * 当校验类型是Fourth 时 ，匹配 classes 里面的分组 Classes.First.class
     */
    @ConvertGroup(from = Fourth.class, to = Classes.First.class)
    private Classes classes;


    /**
     * 顺序校验
     */
    @Length(min = 5, max = 20, message = "学号长度为5-20", groups = First.class)
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "学号必须由a-z组成", groups = Second.class)
    private String studentNum;
    /**
     *枚举校验
     */

    //@ValidateEnumAnnotation(target = UserTypeEnum.class, message = "type类型错误")
    //@ValidateEnumAnnotation(target = UserTypeEnum.class, message = "type类型错误",groups = {First.class})
    @NotNull(message = "type不能为空", groups = {First.class, Second.class, Third.class})
    @ValidateEnumAnnotation(target = UserTypeEnum.class, message = "type类型错误", groups = {First.class, Second.class, Third.class})
    private String type;


    public interface First {
    }

    ;

    public interface Second {
    }

    ;

    public interface Third {
    }

    ;

    public interface Fourth {
    }

    ;


    //TypeError: xxx is not a function 的错误    
    public static boolean checkParams(String type, String phone) {
        if (UserTypeEnum.Teacher.getValue().equals(type)) {
            if (StringUtils.isEmpty(phone)) {
                return false;
            }
            return true;
        }
        return true;
    }


}
