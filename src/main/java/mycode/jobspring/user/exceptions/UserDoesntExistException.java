package mycode.jobspring.user.exceptions;

import mycode.jobspring.constants.UserConstant;

public class UserDoesntExistException extends RuntimeException {
    public UserDoesntExistException() {
        super(UserConstant.USER_DOESNT_EXIST_EXCEPTION);
    }
}
