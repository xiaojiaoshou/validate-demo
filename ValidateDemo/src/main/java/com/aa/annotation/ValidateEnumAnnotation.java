package com.aa.annotation;



import com.aa.validate.EnumValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.PARAMETER;


 /**
  * @description：
  * @author  ghx
  * @date 2019/7/12
  */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE ,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidatorImpl.class})
@Documented
public @interface ValidateEnumAnnotation {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?>[] target() default {};
}
