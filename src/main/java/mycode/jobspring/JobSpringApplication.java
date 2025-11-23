package mycode.jobspring;

import mycode.jobspring.masina.models.Masina;
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
    CommandLineRunner show(UserRepository userRepository) {
        return args -> {
//            System.out.println("======");
//            System.out.println("TOTI USERII");
//            List<User> userList = userRepository.findAll();
//            userList.stream().forEach((user)->{
//
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//            System.out.println("======");
//            System.out.println("USERII MAI MARI DE 25 DE ANI");
//            userRepository.findByVarstaGreaterThan(25).stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//            System.out.println("=====");
//            System.out.println("USERII CU PRENUMELE ION");
//            userRepository.findByPrenume("Ion").stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//
//
//            System.out.println("====");
//            System.out.println("USERII CU MASINI MARCA DACIA");
//            userRepository.findByMasini_Marca("Dacia").stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//
//            System.out.println("====");
//            System.out.println("USERII CU MASINI CU MAI PUTIN DE 50000 KM");
//            userRepository.findByMasini_NumarKilometriLessThan(50000).stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//
//
//
//            System.out.println("======");
//            System.out.println("USERII FARA MASINI");
//            userRepository.findUsersWithoutMasini().stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//            System.out.println("======");
//            System.out.println("USERII CU MASINI MARCA AUDI SI MAI MARI DE 30 DE ANI");
//            userRepository.findByMasini_MarcaAndVarstaGreaterThan("Audi",30).stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//
//            System.out.println("======");
//            System.out.println("USERII CU NUME SI PRENUME");
//            userRepository.findByNumeAndPrenume("Popescu","Ion").stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//            System.out.println("======");
//            System.out.println("USERII CU PRENUME CARE INCEP CU A");
//            userRepository.findByPrenumeStartingWith("A").stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//            System.out.println("======");
//            System.out.println("USERII CU MASINI CARE NU SUNT MODEL A4");
//            userRepository.findByMasini_ModelIsNot("A4").stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });
//
//
//            System.out.println("======");
//            System.out.println("USERII CU MASINI CU PESTE 100000 KM SORTATI DUPA VARSTA ASCENDENT");
//            userRepository.findByMasini_NumarKilometriGreaterThanOrderByVarstaAsc(100000).stream().forEach((user)->{
//                System.out.println((( "User: "+ user.getNume()+" Masini: "+user.getMasini())));
//            });

            User user=userRepository.findAll().get(0);

            Masina masina=new Masina("Audi","A4",100,user);

            user.addMasina(masina);


            userRepository.save(user);






        };

    }
}
