package com.aa.validate;

import com.aa.annotation.ValidateEnumAnnotation;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 作者: ghx   2019-7-12
 * 枚举的校验 ,约定规则:枚举中必须包含validate方法,
 * 参考PayStatus 枚举
 * 使用注解例如:@EnumValidAnnotation(message = "商品类型输入错误",target = PayStatus.class )
 *
 * 枚举类中必须书写如下静态方法validate 示例:
 *
 * public static boolean validate(String value) {
 *         for (MediaGoodsTypeEnum e : MediaGoodsTypeEnum.values()) {
 *             if (e.getValue().equals(value)) {
 *                 return true;
 *             }
 *         }
 *         return false;
 *     }
 *
 *
 */
@Slf4j
public class EnumValidatorImpl implements ConstraintValidator<ValidateEnumAnnotation, String> {

    Class<?>[] cls; //枚举类

    @Override
    public void initialize(ValidateEnumAnnotation constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        //枚举类验证
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("validate", String.class);
                        if (objs == null || objs.length == 0) {
                            log.error("枚举校验类错误,类名:{}" + objs.getClass().getName());
                            throw new RuntimeException("系统错误!");
                        }
                        boolean isboolean = (Boolean) method.invoke(objs[0], value);
                        return isboolean;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return true;
        }
        return false;
    }
}

