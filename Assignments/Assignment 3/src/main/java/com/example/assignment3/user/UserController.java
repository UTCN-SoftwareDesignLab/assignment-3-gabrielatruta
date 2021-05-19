package com.example.assignment3.user;

import com.example.assignment3.user.dto.UserDTO;
import com.example.assignment3.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assignment3.URLMapping.USER;
import static com.example.assignment3.URLMapping.ENTITY;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() { return userService.allUsersForList();}

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }

    @PatchMapping(ENTITY)
    public UserDTO edit(@PathVariable Long id, @RequestBody UserDTO user) {
        return userService.edit(id, user);
    }

}
