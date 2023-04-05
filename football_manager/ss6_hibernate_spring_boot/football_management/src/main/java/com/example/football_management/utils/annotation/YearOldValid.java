package com.example.football_management.utils.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface YearOldValid {
    String message() default "Age not true";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
