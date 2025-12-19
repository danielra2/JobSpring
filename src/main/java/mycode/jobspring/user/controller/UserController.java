package mycode.jobspring.user.controller;


import jakarta.validation.Valid;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.service.UserCommandService;
import mycode.jobspring.user.service.UserQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserListResponse findAllUsers(){
        return userQueryService.findAllUsers();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserDto userDto){
        return userCommandService.createUser(userDto);
    }
    @PostMapping("/delete/{nume}/{prenume}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String nume,@PathVariable String prenume){
        userCommandService.deleteUserByNumeAndPrenume(nume,prenume);
    }
}
