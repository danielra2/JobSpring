package mycode.jobspring.user.controller;


import jakarta.validation.Valid;
import mycode.jobspring.user.dtos.*;
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
    @DeleteMapping("/delete/{nume}/{prenume}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String nume,@PathVariable String prenume){
        userCommandService.deleteUserByNumeAndPrenume(nume,prenume);
    }
    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUserPut(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        return userCommandService.updateUserPut(id, userDto); //
    }


    @PatchMapping("/patch/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUserPatch(@PathVariable long id, @RequestBody UserPatchDto userPatchDto) {
        return userCommandService.updateUserPatch(id, userPatchDto); //
    }

    //QUERRY

    @GetMapping("/without-masini")
    @ResponseStatus(HttpStatus.OK)
    public UserSimpleListResponse findUsersWithoutMasini() {
        return userQueryService.findUsersWithoutMasini(); //
    }

    @GetMapping("/filter/marca/{marca}/varsta-peste/{varsta}")
    @ResponseStatus(HttpStatus.OK)
    public UserListResponse findByMasiniMarcaAndVarstaGreaterThan(@PathVariable String marca, @PathVariable int varsta) {
        return userQueryService.findByMasiniMarcaAndVarstaGreaterThan(marca, varsta); //
    }

    @GetMapping("/search/prenume-prefix/{prefix}")
    @ResponseStatus(HttpStatus.OK)
    public UserNumePrenumeVarstaListResponse findByPrenumeStartingWith(@PathVariable String prefix) {
        return userQueryService.findByPrenumeStartingWith(prefix); //
    }

    @GetMapping("/filter/model-nu-e/{model}")
    @ResponseStatus(HttpStatus.OK)
    public UserListResponse findByMasiniModelIsNot(@PathVariable String model) {
        return userQueryService.findByMasiniModelIsNot(model); //
    }

    @GetMapping("/filter/km-peste/{numarKilometri}")
    @ResponseStatus(HttpStatus.OK)
    public UserListResponse findByMasiniNumarKilometriGreaterThanOrderByVarstaAsc(@PathVariable int numarKilometri) {
        return userQueryService.findByMasiniNumarKilometriGreaterThanOrderByVarstaAsc(numarKilometri); //
    }

    @GetMapping("/filter/varsta-peste/{varsta}")
    @ResponseStatus(HttpStatus.OK)
    public UserNumePrenumeVarstaListResponse findByVarstaGreaterThan(@PathVariable int varsta) {
        return userQueryService.findByVarstaGreaterThan(varsta);
    }


    @GetMapping("/filter/prenume/{prenume}")
    @ResponseStatus(HttpStatus.OK)
    public UserNumePrenumeVarstaListResponse findByPrenume(@PathVariable String prenume) {
        return userQueryService.findByPrenume(prenume);
    }


    @GetMapping("/filter/masini/km-sub/{numarKilometri}")
    @ResponseStatus(HttpStatus.OK)
    public UserNumePrenumeVarstaListResponse findByMasiniNumarKilometriLessThan(@PathVariable int numarKilometri) {
        return userQueryService.findByMasiniNumarKilometriLessThan(numarKilometri);
    }


}

