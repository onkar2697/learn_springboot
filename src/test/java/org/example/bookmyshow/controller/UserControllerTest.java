package org.example.bookmyshow.controller;

import org.example.bookmyshow.config.JpaAuditingConfig;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith(MockitoExtension.class)
@WebMvcTest(
        controllers = UserController.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JpaAuditingConfig.class
        )
)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @MockitoBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getUserById() throws Exception {
        User user = new User();
        user.setName("kim");
        user.setPassword("kim@123");
        user.setAge(27);
        user.setId(1L);

        when(userService.getUserById(1L)).thenReturn(user);
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("kim"));
    }

    @Test
    void deleteUserById() throws Exception {
//        mockMvc.perform(delete("/users/delete/1"))
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
        verify(userService).deleteUser(1L);
    }

    @Test
    void updateUser() throws Exception {

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("updated");
        updatedUser.setPassword("updated@123");
        updatedUser.setAge(28);
        updatedUser.setEmail("updated@123");

        when(userService.updateUser(eq(1L), any(User.class)))
                .thenReturn(updatedUser);

        mockMvc.perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "name": "updated",
                                            "email": "updated@123",
                                            "age": 28,
                                            "password": "updated@123"
                                        }
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("updated"))
                .andExpect(jsonPath("$.email").value("updated@123"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.password").value("updated@123"));

        verify(userService).updateUser(eq(1L), any(User.class));
    }

    @Test
    void updateUserNotFound() throws Exception{

        when(userService.updateUser(eq(999L),any(User.class)))
                .thenThrow(UserNotFoundException.class);
        mockMvc.perform(
                        put("/users/999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "name": "updated",
                                            "email": "updated@123",
                                            "age": 28,
                                            "password": "updated@123"
                                        }
                                        """)
                )
                .andExpect(status().isNotFound());  // here we are getting 404 not found error not 200 ok response
    }

    @Test
    void validationFailureTest() throws Exception {

        mockMvc.perform(
                put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                 "name": " ",
                                 "email": "updated@123",
                                 "age": 28,
                                 "password": "updated@123"
                                 }
                                """)
        )
                .andExpect(status().isBadRequest());
        verify(userService,never()).updateUser(anyLong(),any(User.class));
    }

    @Test
    void createUserTest() throws Exception{

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "abcd",
                        "email": "abcd@123",
                        "age": 28,
                        "password": "abcd@123"
                        }
                        """)
        )
                .andExpect(status().isOk());

    }

}