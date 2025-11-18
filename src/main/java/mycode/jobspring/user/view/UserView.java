package mycode.jobspring.user.view;

import mycode.jobspring.user.repository.UserRepository;
import mycode.jobspring.user.service.UserCommandService;
import mycode.jobspring.user.service.UserQueryService;

public class UserView {
    private UserQueryService userQueryService;
    private UserCommandService userCommandService;
    private UserRepository userRepository;

    public UserView(UserCommandService userCommandService,UserQueryService userQueryService,UserRepository userRepository){
        this.userCommandService=userCommandService;
        this.userQueryService=userQueryService;
        this.userRepository=userRepository;
    }
    public void menu(){
        System.out.println("1->Afiseaza userii");
    }

}
