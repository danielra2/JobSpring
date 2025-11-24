package mycode.jobspring;

import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import mycode.jobspring.user.view.UserView;
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
    CommandLineRunner show(UserView userView) {
        return args -> {
            userView.play();






        };

    }
}
