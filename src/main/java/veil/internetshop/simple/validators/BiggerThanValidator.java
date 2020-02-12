package veil.internetshop.simple.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BiggerThanValidator implements ConstraintValidator<BiggerThan, Number>{

    private final Map<Class<? extends Number>, Function<Number, Boolean>> validators;

    public BiggerThanValidator(){
        validators = new HashMap<>();
        validators.put(Integer.class, this::validateInteger);
        validators.put(Long.class, this::validateLong);
        validators.put(Float.class, this::validateFloat);
        validators.put(Double.class, this::validateDouble);
        validators.put(BigDecimal.class, this::validateBigDecimal);
    }

    private String value;

    @Override
    public void initialize(BiggerThan constraintAnnotation){
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context){
        Class<? extends Number> numberClass = value.getClass();
        if(! validators.containsKey(numberClass)){
            context.buildConstraintViolationWithTemplate("Validator not supports class " + numberClass);
            return false;
        }
        return validators.get(numberClass).apply(value);
    }

    private boolean validateInteger(Number number){
        return ((Integer)number).compareTo(Integer.valueOf(value)) > 0;
    }

    private boolean validateLong(Number number){
        return ((Long)number).compareTo(Long.valueOf(value)) > 0;
    }

    private boolean validateFloat(Number number){
        return ((Float)number).compareTo(Float.valueOf(value)) > 0;
    }

    private boolean validateDouble(Number number){
        return ((Double)number).compareTo(Double.valueOf(value)) > 0;
    }

    private boolean validateBigDecimal(Number number){
        return ((BigDecimal)number).compareTo(new BigDecimal(value)) > 0;
    }
}
