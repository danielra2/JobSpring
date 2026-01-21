package mycode.jobspring.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.models.User;
import mycode.jobspring.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void createUserEndpointPersistsEntity() throws Exception {

        UserDto userDto=new UserDto("Popescu","Ion",30,new HashSet<>());

        MvcResult result= mockMvc.perform(post("/api/job/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(userDto))).andExpect(status().isCreated()).andReturn();
        UserResponse response=objectMapper.readValue(result.getResponse().getContentAsByteArray(),UserResponse.class);
        assertThat(response.nume()).isEqualTo("Popescu");
        assertThat(response.prenume()).isEqualTo("Ion");

        assertThat(userRepository.findByNumeAndPrenume("Popescu","Ion")).isPresent();
    }

    @Test
    void getAllUsersReturnsList() throws Exception {

        User user = new User();
        user.setNume("Daniel");
        user.setPrenume("Test");
        user.setVarsta(22);
        user.setMasini(new HashSet<>());
        userRepository.save(user);

        MvcResult result=mockMvc.perform(get("/api/job").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        UserListResponse response =objectMapper.readValue(result.getResponse().getContentAsByteArray(),UserListResponse.class);
        assertThat(response.userList()).hasSize(1);
        assertThat(response.userList().get(0).nume()).isEqualTo("Daniel");
    }
}