package mycode.jobspring.user.service;

import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserResponse;

public interface UserQueryService {
    UserListResponse findAllUsers();
    //todo:15 functionalitati
}
