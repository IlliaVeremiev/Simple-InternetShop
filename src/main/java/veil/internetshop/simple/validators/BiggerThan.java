package veil.internetshop.simple.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BiggerThanValidator.class)
public @interface BiggerThan{

    String message() default "Value must be bigger than";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value();
}
