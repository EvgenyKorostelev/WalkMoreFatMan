package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateUserPayload;
import ru.korostelev.WalkMoreFatMan.entity.User;
import ru.korostelev.WalkMoreFatMan.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/WalkMoreFatMan-api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User registeredUser (@RequestBody @Valid NewUserPayload payload){
        return userService.addUser(payload);
    }

    @GetMapping
    public List<User> allUsers (){
        return userService.findAllUsers();
    }

    @GetMapping("/{userName:\\S+}")
    public User findUser(@PathVariable String userName){
        return userService.findUserByName(userName);
    }

    @PutMapping("/{userName:\\S+}")
    public User updateUser(@PathVariable String userName,
                           @RequestBody UpdateUserPayload payload){
        return userService.updateUser(userName, payload);
    }


    @DeleteMapping("/{userName:\\S+}")
    @Transactional
    public void deleteUser(@PathVariable String userName){
        userService.deleteUserByName(userName);
    }


}
