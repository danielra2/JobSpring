package mycode.jobspring.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test

    void getAllUserReturnsResults() throws Exception {
//        userRepository.saveAll(List.of(user1,user2));
        Set<Masina> setMasini1 = new HashSet<>();

        Masina masina1=new Masina("Audi","A4",2000,200000,null);
        Masina masina2=new Masina("Bmw","A4",2000,200000,null);
        Masina masina3=new Masina("Opel","A4",2000,200000,null);
        Masina masina4=new Masina("Ford","A4",2000,200000,null);
        Masina masina5=new Masina("Peugeot","A4",2000,200000,null);

        setMasini1.add(masina1);
        setMasini1.add(masina2);
        setMasini1.add(masina3);
        setMasini1.add(masina4);
        setMasini1.add(masina5);

        User user1 = new User(1L, "Daniel", "Ion", 22, setMasini1);

        Set<Masina> setMasini2 = new HashSet<>();


        Masina masina6 = new Masina("Mercedes", "C-Class", 2021, 35000, null);
        Masina masina7 = new Masina("Toyota", "Corolla", 2022, 22000, null);
        Masina masina8 = new Masina("Volkswagen", "Golf", 2019, 15000, null);
        Masina masina9 = new Masina("Tesla", "Model 3", 2023, 42000, null);
        Masina masina10 = new Masina("Dacia", "Logan", 2020, 9500, null);

        setMasini2.add(masina6);
        setMasini2.add(masina7);
        setMasini2.add(masina8);
        setMasini2.add(masina9);
        setMasini2.add(masina10);

        User user2=new User(2L,"Popa","Viorel",88,setMasini2);



        MvcResult result=mockMvc.perform(get("/api/job")).andExpect(status().isOk()).andReturn();

        UserListResponse response=objectMapper.readValue(result.getResponse().getContentAsByteArray(),UserListResponse.class);
        assertThat(response.userList().size()).isEqualTo(2);



    }
}
