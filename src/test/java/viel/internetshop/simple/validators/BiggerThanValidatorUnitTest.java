package viel.internetshop.simple.validators;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import veil.internetshop.simple.validators.BiggerThan;

import javax.validation.ConstraintViolation;
import javax.validation.UnexpectedTypeException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class BiggerThanValidatorUnitTest{

    private static final String COMPARABLE_STRING = "0";

    private static final String VALID_STRING = "1";
    private static final Short VALID_SHORT = 1;
    private static final Integer VALID_INTEGER = 1;
    private static final Integer INVALID_INTEGER = 0;
    private static final Long VALID_LONG = 1L;
    private static final Long INVALID_LONG = 0L;
    private static final Float VALID_FLOAT = Float.MIN_VALUE;
    private static final Float INVALID_FLOAT = 0.f;
    private static final Double VALID_DOUBLE = Double.MIN_NORMAL;
    private static final Double INVALID_DOUBLE = 0.d;
    private static final BigDecimal VALID_BIGDECIMAL = BigDecimal.valueOf(Double.MIN_NORMAL);
    private static final BigDecimal INVALID_BIGDECIMAL = BigDecimal.ZERO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void setUpClass(){
        validator = Validation.buildDefaultValidatorFactory().usingContext().getValidator();
    }

    private static Validator validator;

    @Test
    public void shouldNotReturnViolationsWhenNoErrorsFound(){
        ValidTestClass entity = new ValidTestClass(VALID_INTEGER, VALID_LONG, VALID_FLOAT, VALID_DOUBLE, VALID_BIGDECIMAL);
        Set<ConstraintViolation<ValidTestClass>> validations = validator.validate(entity);

        Assert.assertEquals(0, validations.size());
    }

    @Test
    public void shouldReturnSameViolationsCountAsErrors(){
        ValidTestClass entity = new ValidTestClass(INVALID_INTEGER, INVALID_LONG, INVALID_FLOAT, INVALID_DOUBLE, INVALID_BIGDECIMAL);
        Set<ConstraintViolation<ValidTestClass>> validations = validator.validate(entity);

        Assert.assertEquals(5, validations.size());
    }

    @Test
    public void shouldThrowUnexpectedTypeExceptionWhenValidatingNotNumberField(){
        InvalidFieldClass entity = new InvalidFieldClass(VALID_STRING);

        expectedException.expect(UnexpectedTypeException.class);

        validator.validate(entity);
    }

    @Test
    public void shouldReturnViolationWhenNumberFormatNotSupported(){
        NotSupportedFieldClass entity = new NotSupportedFieldClass(VALID_SHORT);
        Set<ConstraintViolation<NotSupportedFieldClass>> validations = validator.validate(entity);

        Assert.assertEquals(1, validations.size());
    }

    class ValidTestClass{

        @BiggerThan(COMPARABLE_STRING)
        private Integer integerField;

        @BiggerThan(COMPARABLE_STRING)
        private Long longField;

        @BiggerThan(COMPARABLE_STRING)
        private Float floatField;

        @BiggerThan(COMPARABLE_STRING)
        private Double doubleField;

        @BiggerThan(COMPARABLE_STRING)
        private BigDecimal bigDecimalField;

        public ValidTestClass(Integer integerField, Long longField, Float floatField, Double doubleField, BigDecimal bigDecimalField){
            this.integerField = integerField;
            this.longField = longField;
            this.floatField = floatField;
            this.doubleField = doubleField;
            this.bigDecimalField = bigDecimalField;
        }
    }

    class InvalidFieldClass{

        @BiggerThan(COMPARABLE_STRING)
        private String stringField;

        public InvalidFieldClass(String stringField){
            this.stringField = stringField;
        }
    }

    class NotSupportedFieldClass{

        @BiggerThan(COMPARABLE_STRING)
        private Short shortField;

        public NotSupportedFieldClass(Short shortField){
            this.shortField = shortField;
        }
    }
}
