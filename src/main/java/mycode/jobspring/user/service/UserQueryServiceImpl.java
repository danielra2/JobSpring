package mycode.jobspring.user.service;


import mycode.jobspring.mappers.JobSpringMapper;
import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserNumePrenumeVarstaListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.dtos.UserSimpleListResponse;
import mycode.jobspring.user.exceptions.UserDoesntExistException;
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

    @Override
    public UserNumePrenumeVarstaListResponse findByVarstaGreaterThan(int varsta)throws UserDoesntExistException{
        List<User> userList = userRepository.findByVarstaGreaterThan(varsta);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserNumePrenumeVarstaListResponse(mapper.mapUserListToNumePrenumeVarstaResponseList(userList));
    }

    @Override
    public UserNumePrenumeVarstaListResponse findByPrenume(String prenume)throws UserDoesntExistException{
        List<User> userList = userRepository.findByPrenume(prenume);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserNumePrenumeVarstaListResponse(mapper.mapUserListToNumePrenumeVarstaResponseList(userList));
    }

    @Override
    public UserNumePrenumeVarstaListResponse findByMasiniNumarKilometriLessThan(int numarKilometri)throws UserDoesntExistException{
        List<User> userList = userRepository.findByMasini_NumarKilometriLessThan(numarKilometri);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserNumePrenumeVarstaListResponse(mapper.mapUserListToNumePrenumeVarstaResponseList(userList));
    }

    @Override
    public UserSimpleListResponse findUsersWithoutMasini()throws UserDoesntExistException{
        List<User> userList = userRepository.findUsersWithoutMasini();
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserSimpleListResponse(mapper.mapUserListToSimpleResponseList(userList));
    }

    @Override
    public UserListResponse findByMasiniMarcaAndVarstaGreaterThan(String marca,int varsta)throws UserDoesntExistException{
        List<User> userList = userRepository.findByMasini_MarcaAndVarstaGreaterThan(marca,varsta);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserListResponse(mapper.mapUserListToUserResponseList(userList));
    }

    @Override
    public UserNumePrenumeVarstaListResponse findByPrenumeStartingWith(String prefix)throws UserDoesntExistException{
        List<User> userList = userRepository.findByPrenumeStartingWith(prefix);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserNumePrenumeVarstaListResponse(mapper.mapUserListToNumePrenumeVarstaResponseList(userList));
    }

    @Override
    public UserListResponse findByMasiniModelIsNot(String model)throws UserDoesntExistException{
        List<User> userList = userRepository.findByMasini_ModelIsNot(model);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserListResponse(mapper.mapUserListToUserResponseList(userList));
    }

    @Override
    public UserListResponse findByMasiniNumarKilometriGreaterThanOrderByVarstaAsc(int numarKilometri)throws UserDoesntExistException{
        List<User> userList = userRepository.findByMasini_NumarKilometriGreaterThanOrderByVarstaAsc(numarKilometri);
        if(userList.isEmpty()){throw new UserDoesntExistException();}
        return new UserListResponse(mapper.mapUserListToUserResponseList(userList));
    }
}
