package mycode.jobspring.masina.controller;

import jakarta.validation.Valid;
import mycode.jobspring.masina.dtos.MasinaDto;
import mycode.jobspring.masina.dtos.MasinaListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.masina.service.MasinaQuerryService;
import mycode.jobspring.user.service.UserCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job/masini")
public class MasinaController {

    private final MasinaQuerryService masinaQuerryService;
    private final UserCommandService userCommandService;

    public MasinaController(MasinaQuerryService masinaQuerryService, UserCommandService userCommandService) {
        this.masinaQuerryService = masinaQuerryService;
        this.userCommandService = userCommandService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MasinaListResponse findAllMasini() {
        return masinaQuerryService.findAllMasini(); //
    }

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addMasinaToUser(@PathVariable long userId, @Valid @RequestBody MasinaDto masinaDto) {
        return userCommandService.addMasinaToUser(userId, masinaDto); //
    }
}