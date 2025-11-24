package mycode.jobspring.user.exceptions;

import mycode.jobspring.constants.UserConstant;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super(UserConstant.USER_ALREADY_EXISTS_EXCEPTION);
    }
}
