package com.aa.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @descriotion 班级信息
 * @author ghx
 * @date 2019/7/13
 */
@Data
public class Classes {
    /**
     *  @NotBlank(message = "班级不能为空",groups = First.class)分组和UserVoJoin里的
     *  @ConvertGroup(from=Fourth.class, to=Classes.First.class) 组合使用
     */
    //@NotBlank(message = "班级不能为空",groups = First.class)
    @NotBlank(message = "班级不能为空")
    private String classNum;
    @NotBlank(message = "学校名称不能为空")
    private String schoolName;

    public interface First {
    }

    ;
}
