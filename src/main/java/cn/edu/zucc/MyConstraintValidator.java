package cn.edu.zucc;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
	
	@Override
	public boolean isValid(String s, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(s.matches("^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X)$"))
			return true;
		else {
			return false;
		}
	}

}
