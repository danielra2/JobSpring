package mycode.jobspring.user.service;


import mycode.jobspring.mappers.JobSpringMapper;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.exceptions.UserAlreadyExistsException;
import mycode.jobspring.user.exceptions.UserDoesntExistException;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private JobSpringMapper mapper;
    private UserCommandService userCommandService;

    @BeforeEach
    void setup(){
        userCommandService=new UserCommandServiceImpl(userRepository,mapper);
    }
    @Test
    void createUserThrowsWhenUserAlreadyExists(){
        UserDto userDto=new UserDto("Popescu","Dan",12,new HashSet<>());
        when(userRepository.findByNumeAndVarsta(userDto.nume(),userDto.varsta())).thenReturn(Optional.of(new User()));
        assertThatThrownBy(()->userCommandService.createUser(userDto)).isInstanceOf(UserAlreadyExistsException.class);
        verify(userRepository,never()).save(any());
    }
    @Test
    void createUserPersistsMappedEntity(){
        UserDto userDto=new UserDto("Popescu","Danut",12,new HashSet<>());
        User entity=new User();
        UserResponse response=new UserResponse(5L,"Popescu","Danut",12,new HashSet<>());
        when(userRepository.findByNumeAndVarsta(userDto.nume(),userDto.varsta())).thenReturn(Optional.empty());
        when(mapper.mapUserDtoToUser(userDto)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);
        when(mapper.mapUserToUserResponse(entity)).thenReturn(response);
        UserResponse response1=userCommandService.createUser(userDto);
        assertThat(response1).isEqualTo(response);
        verify(userRepository).save(entity);

    }

    @Test
    void updateUserPutThrowsWhenUserDoesnotExist(){
        long id=10;
        UserDto dto=new UserDto("Popescu","Dan",12,new HashSet<>());
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(()->userCommandService.updateUserPut(id,dto)).isInstanceOf(UserDoesntExistException.class);
        verify(userRepository).findById(id);

    }
    @Test
    void updateUserPutUpdatedEntityAndReturnsResponse(){
        //1. pregetim date
        long id=1L;
        UserDto userDto=new UserDto("Numenou","prenumenou",30,new HashSet<>());
        User existingUser=new User();
        existingUser.setId(id);
        existingUser.setNume("Pop");
        existingUser.setPrenume("Ionel");
        existingUser.setVarsta(24);

        UserResponse expectedResponse=new UserResponse(id,"Numenou","prenumenou",30,new HashSet<>());
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(mapper.mapUserToUserResponse(existingUser)).thenReturn(expectedResponse);
        UserResponse result=userCommandService.updateUserPut(id,userDto);
        assertThat(result).isEqualTo(expectedResponse);
        assertThat(existingUser.getNume()).isEqualTo(userDto.nume());
        assertThat(existingUser.getPrenume()).isEqualTo(userDto.prenume());
        assertThat(existingUser.getVarsta()).isEqualTo(userDto.varsta());
        verify(userRepository).findById(id);
    }

}
