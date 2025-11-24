package mycode.jobspring.user.service;


import mycode.jobspring.mappers.JobSpringMapper;
import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService{
    private UserRepository userRepository;
    private JobSpringMapper mapper;

    public UserQueryServiceImpl(UserRepository userRepository,JobSpringMapper mapper){
        this.userRepository=userRepository;
        this.mapper=mapper;
    }


    @Override
    public UserListResponse findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = mapper.mapUserListToUserResponseList(userList);
        return new UserListResponse(userResponseList);
    }
}
