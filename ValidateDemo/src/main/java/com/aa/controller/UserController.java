package com.aa.controller;

import com.aa.dto.Response;
import com.aa.excetion.CustomerException;
import com.aa.excetion.ParamsException;
import com.aa.vo.*;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * @descriotion bindResult测试
 * @author ghx
 * @date 2019/7/12
 */
//@Validated
@RequestMapping("/register")
@RestController
public class UserController {

    /**
     * @description 通过 BindingResul捕获校验异常结果
     * @author ghx
     * @param userVo
     * @param result
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/bingResult")
    public Response validateBingResult(@RequestBody @Validated UserVo userVo, BindingResult result) {

        if (result.hasErrors()) {
            String message = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage()).collect(Collectors.joining(",", "参数错误:", ""));
            return Response.setFaile(message);
        }
        return Response.setSuccess();

    }

    /**
     * @description 全局统一异常 返回验证结果
     * @author ghx
     * @param userVo
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/globalException")
    public Response validateGlobalException(@RequestBody @Validated UserVo userVo) {
        return Response.setSuccess();
    }


    /**
     *
     * @description 方法级校验, 全局异常捕获校验结果
     * @author ghx
     * @param userName
     * @param age
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/methodParams")
    public Response validateMethodParams(@NotBlank(message = "userName不能为空") String userName,
                                         @NotNull(message = "年龄不能为空")
                                         @Min(value = 21, message = "年龄太小") Integer age) {
        return Response.setSuccess();
    }


    /**
     *
     * @description 分组校验
     * @author ghx
     * @param userVoGroup
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/registerGroup")
    public Response registerGroup(@RequestBody @Validated(UserVoGroup.First.class) UserVoGroup userVoGroup) {
        return Response.setSuccess();
    }


    /**
     *
     * @description 枚举校验
     * @author ghx
     * @param userVoEnum
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/Enum")
    public Response validateEnum(@RequestBody @Validated UserVoEnum userVoEnum) {
        return Response.setSuccess();
    }


    /**
     *
     * @description JavaScript脚本校验
     * @author ghx
     * @param userVoJavaScript
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/javaScript")
    public Response validateJavaScript(@RequestBody @Validated UserVoJavaScript userVoJavaScript) {
        return Response.setSuccess();
    }


    /**
     *
     * @description 嵌套校验
     * @author ghx
     * @param userVoJoin
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/registerLoop")
    public Response registerLoop(@RequestBody @Validated UserVoJoin userVoJoin) {
        return Response.setSuccess();
    }


    /**
     *
     * @description 顺序校验
     * @author ghx
     * @param userVoSequence
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/registerSequence")
    public Response registerSequence(@RequestBody @Validated UserVoSequence userVoSequence) {
        return Response.setSuccess();
    }

    /**
     *
     * @description参数异常 不用validate 自己校验 抛出ParamsException
     * @author ghx
     * @param userVoSequence
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/registerParamExcetion")
    public Response parmsSequence(@RequestBody UserVoSequence userVoSequence) {

        if (userVoSequence == null || StringUtils.isEmpty(userVoSequence.getPhone())) {
            throw new ParamsException("phone不能为空");
        }

        //继续业务流程 .....

        return Response.setSuccess();

    }

    /**
     *
     * @description业务异常  一般用于service 抛出 CustomerException
     * @author ghx
     * @param userVoSequence
     * @exception
     * @return Response
     * @date 2019/7/13
     */
    @RequestMapping("/registerCustomerException")
    public Response customerCustomer(@RequestBody @Validated UserVoSequence userVoSequence) {

        throw new CustomerException("业务异常");
    }

}
