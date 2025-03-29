package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UserNamePayload;
import ru.korostelev.WalkMoreFatMan.entity.User;
import ru.korostelev.WalkMoreFatMan.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/WalkMoreFatMan-api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User registeredUser (@RequestBody @Valid NewUserPayload newUserPayload){
        return userService.addUser(newUserPayload );
    }

    @GetMapping
    public List<User> allUsers (){
        return userService.findAllUsers();
    }

    @GetMapping("/{userName:\\S+}")
    public User findUser(@PathVariable @Valid UserNamePayload userNamePayload){
        return userService.findUserByName(userNamePayload.name());
    }

    @PutMapping("/{userName:\\S+}")
    public User updateUser(@PathVariable @Valid UserNamePayload userNamePayload,
                           @RequestBody @Valid UpdateUserPayload payload){
        return userService.updateUser(userNamePayload.name(), payload);
    }


    @DeleteMapping("/{userName:\\S+}")
    @Transactional
    public void deleteUser(@PathVariable @Valid UserNamePayload userNamePayload){
        userService.deleteUserByName(userNamePayload.name());
    }


}
