package mycode.jobspring.user.service;

import mycode.jobspring.masina.dtos.MasinaDto;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserPatchDto;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.exceptions.UserAlreadyExistsException;
import mycode.jobspring.user.exceptions.UserDoesntExistException;

public interface UserCommandService {
    UserResponse createUser(UserDto userDto) throws UserAlreadyExistsException;
    UserResponse deleteUserByNumeAndPrenume(String nume, String prenume) throws UserDoesntExistException;
    UserResponse addMasinaToUser(long userId, MasinaDto masinaDto) throws UserDoesntExistException;
    UserResponse updateUserPut(long id,UserDto userDto)throws UserDoesntExistException;
    UserResponse updateUserPatch(long id, UserPatchDto userPatchDto) throws UserDoesntExistException;

}
