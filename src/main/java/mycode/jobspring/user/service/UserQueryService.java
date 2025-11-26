package mycode.jobspring.user.service;

import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserNumePrenumeVarstaListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.dtos.UserSimpleListResponse;
import mycode.jobspring.user.exceptions.UserDoesntExistException;

public interface UserQueryService {
    UserListResponse findAllUsers();
    UserNumePrenumeVarstaListResponse findByVarstaGreaterThan(int varsta)throws UserDoesntExistException;
    UserNumePrenumeVarstaListResponse findByPrenume(String prenume)throws UserDoesntExistException;
    UserNumePrenumeVarstaListResponse findByMasiniNumarKilometriLessThan(int numarKilometri)throws UserDoesntExistException;
    UserSimpleListResponse findUsersWithoutMasini()throws UserDoesntExistException;
    UserListResponse findByMasiniMarcaAndVarstaGreaterThan(String marca,int varsta)throws UserDoesntExistException;
    UserNumePrenumeVarstaListResponse findByPrenumeStartingWith(String prefix)throws UserDoesntExistException;
    UserListResponse findByMasiniModelIsNot(String model)throws UserDoesntExistException;
    UserListResponse findByMasiniNumarKilometriGreaterThanOrderByVarstaAsc(int numarKilometri)throws UserDoesntExistException;
}
