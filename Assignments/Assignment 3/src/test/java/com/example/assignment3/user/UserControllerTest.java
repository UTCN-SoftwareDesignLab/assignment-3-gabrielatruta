package com.example.assignment3.user;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.user.dto.UserDTO;
import com.example.assignment3.user.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.assignment3.TestCreationFactory.newUserDTO;
import static com.example.assignment3.URLMapping.ENTITY;
import static com.example.assignment3.URLMapping.USER;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allUsers() throws Exception {
        List<UserListDTO> userListDTOS = TestCreationFactory.listOf(UserListDTO.class);
        when(userService.allUsersForList()).thenReturn(userListDTOS);

        ResultActions result = mockMvc.perform(get(USER));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userListDTOS));

    }

    @Test
    void create() throws Exception {
        UserDTO user = newUserDTO();

        when(userService.create(user)).thenReturn(user);

        ResultActions result = performPostWithRequestBody(USER, user);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(user));
    }

    @Test
    void deleteById() throws Exception {
        UserDTO user = newUserDTO();
        long id = user.getId();

        doNothing().when(userService).deleteById(id);

        ResultActions result = performDeleteWithPathVariable(USER + ENTITY, id);
        result.andExpect(status().isOk());
        verify(userService,times(1)).deleteById(id);
    }

    @Test
    void edit() throws Exception {
        UserDTO user = newUserDTO();
        long id = user.getId();

        when(userService.edit(id, user)).thenReturn(user);

        ResultActions result = performPutWithRequestBodyAndPathVariable(USER + ENTITY, user, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(user));
    }
}