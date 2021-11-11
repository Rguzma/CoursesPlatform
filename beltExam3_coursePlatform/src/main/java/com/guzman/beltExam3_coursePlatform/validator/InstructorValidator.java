package com.guzman.beltExam3_coursePlatform.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.guzman.beltExam3_coursePlatform.modules.*;


@Component
public class InstructorValidator implements Validator  {
	
	 @Override
	    public boolean supports(Class<?> clazz) {
	        return Instructor.class.equals(clazz);
	    }
	    
	    // 2
	    @Override
	    public void validate(Object target, Errors errors) {
	        Instructor user = (Instructor) target;
	        
	        if (!user.getConfirmpassword().equals(user.getPassword())) {
	            // 3
	            errors.rejectValue("passwordConfirmation", "Match");
	        }         
	    }

}
