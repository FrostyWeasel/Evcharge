/* package ntua.softeng28.evcharge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ntua.softeng28.evcharge.user.User;
import ntua.softeng28.evcharge.user.UserController;
import ntua.softeng28.evcharge.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserControllerUnitTest.class);

    @Test
    public void getUsersUnitTest() throws Exception {
        User testUser1 = new User();

        testUser1.setCars(new HashSet<>());
        testUser1.setLoggedIn(false);
        testUser1.setId(Long.valueOf(0));
        testUser1.setPassword("password1");
        testUser1.setRole("ROLE_USER");
        testUser1.setUsername("testUser1");

        User testUser2 = new User();

        testUser2.setCars(new HashSet<>());
        testUser2.setLoggedIn(true);
        testUser2.setId(Long.valueOf(1));
        testUser2.setPassword("password2");
        testUser2.setRole("ROLE_ADMIN");
        testUser2.setUsername("testUser2");


        List<User> allUsers = Arrays.asList(testUser1, testUser2);

        Mockito.when(this.userRepository.findAll()).thenReturn(allUsers);

        RequestBuilder request = MockMvcRequestBuilders.get("/evcharge/api/users").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].loggedIn", Matchers.is(false)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].loggedIn", Matchers.is(true)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("testUser1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].username", Matchers.is("testUser2")));

    }
}
*/