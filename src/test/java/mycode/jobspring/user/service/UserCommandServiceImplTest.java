package mycode.jobspring.user.service;


import mycode.jobspring.mappers.JobSpringMapper;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.exceptions.UserAlreadyExistsException;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

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
        UserDto userDto=new UserDto("Radoi","Daniel",22,new HashSet<>());
        when(userRepository.findByNumeAndPrenume(userDto.nume(),userDto.prenume())).thenReturn(Optional.of(new User()));
        assertThatThrownBy(()->userCommandService.createUser(userDto)).isInstanceOf(UserAlreadyExistsException.class);
       verify(userRepository,never()).save(any());
    }
}
