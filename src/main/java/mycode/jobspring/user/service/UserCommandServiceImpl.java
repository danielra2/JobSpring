package mycode.jobspring.user.service;

import jakarta.transaction.Transactional;
import mycode.jobspring.mappers.JobSpringMapper;
import mycode.jobspring.masina.dtos.MasinaDto;
import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserPatchDto;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.exceptions.UserAlreadyExistsException;
import mycode.jobspring.user.exceptions.UserDoesntExistException;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final JobSpringMapper mapper;

    public UserCommandServiceImpl(UserRepository userRepository, JobSpringMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public UserResponse createUser(UserDto userDto) throws UserAlreadyExistsException {
        Optional<User>userOptional=userRepository.findByNumeAndVarsta(userDto.nume(),userDto.varsta());
        if(userOptional.isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = mapper.mapUserDtoToUser(userDto);
        Set<Masina> masini = mapper.mapMasinaDtoSetToMasinaSet(userDto.masini());
        masini.forEach(user::addMasina);
        User savedUser = userRepository.save(user);
        return mapper.mapUserToUserResponse(savedUser);
    }

    @Transactional
    @Override
    public UserResponse deleteUserByNumeAndPrenume(String nume, String prenume) throws UserDoesntExistException {
        Optional<User> userList = userRepository.findByNumeAndPrenume(nume, prenume);

        if (!userList.isPresent()) {
            throw new UserDoesntExistException();
        }


        User userToDelete = userList.get();
        userRepository.delete(userToDelete);

        return mapper.mapUserToUserResponse(userToDelete);
    }

    @Override
    public UserResponse addMasinaToUser(long userId, MasinaDto masinaDto) throws UserDoesntExistException {
        User user =userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
        Masina newMasina=mapper.mapMasinaDtoToMasina(masinaDto);
        user.addMasina(newMasina);
        User updatedUser =userRepository.save(user);
        return mapper.mapUserToUserResponse(updatedUser);
    }

    @Transactional
    @Override
    public UserResponse updateUserPut(long id, UserDto userDto) throws UserDoesntExistException {
        User existingUser=userRepository.findById(id).orElseThrow(UserDoesntExistException::new);
        existingUser.setNume(userDto.nume());
        existingUser.setPrenume(userDto.prenume());
        existingUser.setVarsta(userDto.varsta());
        return mapper.mapUserToUserResponse(existingUser);

    }

    @Transactional
    @Override
    public UserResponse updateUserPatch(long id, UserPatchDto userPatchDto) throws UserDoesntExistException {User existingUser=userRepository.findById(id).orElseThrow(UserDoesntExistException::new);
        if(userPatchDto.nume()!=null){
            existingUser.setNume(userPatchDto.nume());
        }
        if(userPatchDto.prenume()!=null){
            existingUser.setPrenume(userPatchDto.prenume());
        }
        if(userPatchDto.varsta()!=null){
            existingUser.setVarsta(userPatchDto.varsta());
        }
        return mapper.mapUserToUserResponse(existingUser);
    }


}