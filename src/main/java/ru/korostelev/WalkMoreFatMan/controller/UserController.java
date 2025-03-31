package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.korostelev.WalkMoreFatMan.controller.exceptions.AlreadyExistException;
import ru.korostelev.WalkMoreFatMan.controller.exceptions.NotRegisteredException;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UserNamePayload;
import ru.korostelev.WalkMoreFatMan.entity.User;
import ru.korostelev.WalkMoreFatMan.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/WalkMoreFatMan-api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registeredUser(@RequestBody @Valid NewUserPayload newUserPayload,
                                            BindingResult bindingResult,
                                            UriComponentsBuilder uriComponentsBuilder)
            throws BindException, AlreadyExistException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<User> user = userService.createUser(newUserPayload);
            if (user.isPresent()) {
                return ResponseEntity
                        .created(uriComponentsBuilder
                                .replacePath("/WalkMoreFatMan-api/users/{userName}")
                                .build(Map.of("userName", user.get().getName())))
                        .body(user);
            } else {
                throw new AlreadyExistException("walk_more_fat_man.user.create.errors.already_registered");
            }
        }
    }

    @GetMapping
    public List<User> allUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userName:\\S+}")
    public User findUser(@PathVariable @Valid UserNamePayload userNamePayload) {
        return userService.findUserByName(userNamePayload.name()).orElseThrow(
                () -> new NoSuchElementException("walk_more_fat_man.user.errors.not_found"));
    }

    @PutMapping("/{userName:\\S+}")
    public ResponseEntity<?> updateUser(@PathVariable @Valid UserNamePayload userNamePayload,
                                        @RequestBody @Valid UpdateUserPayload payload,
                                        BindingResult bindingResult,
                                        UriComponentsBuilder uriComponentsBuilder)
            throws BindException, NotRegisteredException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<User> user = userService.updateUser(userNamePayload.name(), payload);
            if (user.isPresent()) {
                return ResponseEntity
                        .created(uriComponentsBuilder
                                .replacePath("/WalkMoreFatMan-api/users/{userName}")
                                .build(Map.of("userName", user.get().getName())))
                        .body(user);
            } else {
                throw new NotRegisteredException("walk_more_fat_man.user.update.errors.not_registered");
            }
        }
    }

    @DeleteMapping("/{userName:\\S+}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable @Valid UserNamePayload userNamePayload) {
        userService.deleteUserByName(userNamePayload.name());
        return ResponseEntity.accepted().build();
    }

}
