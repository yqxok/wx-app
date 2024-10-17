package pri.yqx.groups;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import java.util.regex.Pattern;

public class IsPhoneValidation implements ConstraintValidator<Isphone,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        Pattern compile = Pattern.compile("^1[345678]\\d{9}$");
        return compile.matcher(value).matches();
    }
}