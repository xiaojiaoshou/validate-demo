package com.aa.vo;

import com.aa.vo.all.UserVo1;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @descriotion 用户信息
 * @author ghx
 * @date 2019/7/12
 */
//顺序验证
@GroupSequence({UserVoSequence.First.class, UserVoSequence.Second.class, UserVoSequence.class})
@Data
public class UserVoSequence {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private Integer sex;


    private Integer age;

    private String address;

    private String phone;
    /**
     * 顺序校验
     */
    @Length(min = 5, max = 20, message = "学号长度为5-20", groups = UserVo1.First.class)
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "学号必须由a-z组成", groups = UserVo1.Second.class)
    private String studentNum;

    private String type;

    private Classes classes;

    public interface First {
    }

    ;

    public interface Second {
    }

    ;






}
