package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateUserPayload;
import ru.korostelev.WalkMoreFatMan.entity.User;

import java.util.List;

public interface UserService {

    User addUser(NewUserPayload payload);

    List<User> findAllUsers();

    User findUserByName(String userName);

    User updateUser(String userName, UpdateUserPayload payload);

    void deleteUserByName(String userName);
}
