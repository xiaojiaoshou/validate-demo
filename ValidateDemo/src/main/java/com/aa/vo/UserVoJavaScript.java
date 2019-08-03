package com.aa.vo;

import com.aa.annotation.ValidateEnumAnnotation;
import com.aa.enums.UserTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @descriotion 用户信息
 * @author ghx
 * @date 2019/7/12
 */
@ScriptAssert(lang = "javascript",
        script = "com.aa.vo.all.UserVo1.checkParams(_this.type,_this.phone)",
        message = "角色为老师时必须传入电话号码！"
//        分组
//        ,groups = UserVoJavaScript.First.class
)
@Data
public class UserVoJavaScript {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private Integer sex;

    @NotNull(message = "年龄不能为空！")
    @Range(min = 18, max = 108, message = "年龄必须在18岁-108岁之间")
    private Integer age;

    private String address;
    private String phone;

    private String studentNum;
    @ValidateEnumAnnotation(target = UserTypeEnum.class, message = "type类型错误")
    private String type;
    private Classes classes;


    public interface First {
    };


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
