package mycode.jobspring;

import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JobSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSpringApplication.class, args);
    }
    @Bean
    CommandLineRunner show(UserRepository userRepository){
        return args -> {
            System.out.println("======");
            System.out.println("TOTI USERII");
            List<User> userList=userRepository.findAll();
            userList.stream().forEach((user)->{

                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
            });

        };
    }



}
