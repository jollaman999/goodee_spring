package util;

import logic.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// 유효성 검증 클래스
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        String group = "error.required";

        if (user.getUserId() == null || user.getUserId().length() == 0) {
            errors.rejectValue("userId", group);
            // error.required.userId
        }

        if (user.getPassword() == null || user.getPassword().length() == 0) {
            errors.rejectValue("password", group);
        }

        if (user.getUserName() == null || user.getUserName().length() == 0) {
            errors.rejectValue("userName", group);
        }

        if (user.getPhoneNo() == null || user.getPhoneNo().length() == 0) {
            errors.rejectValue("PhoneNo", group);
        }

        if (user.getPostcode() == null || user.getPostcode().length() == 0) {
            errors.rejectValue("Postcode", group);
        }

        if (user.getAddress() == null || user.getAddress().length() == 0) {
            errors.rejectValue("address", group);
        }

        if (user.getEmail() == null || user.getEmail().length() == 0) {
            errors.rejectValue("email", group);
        }

        if (errors.hasErrors()) {
            errors.reject("error.input.user");
        }
    }
}
