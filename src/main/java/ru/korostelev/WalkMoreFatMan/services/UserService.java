package ru.korostelev.WalkMoreFatMan.services;

import ru.korostelev.WalkMoreFatMan.controller.payload.NewUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateUserPayload;
import ru.korostelev.WalkMoreFatMan.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> createUser(NewUserPayload payload);

    List<User> findAllUsers();

    Optional<User> findUserByName(String userName);

    Optional<User> updateUser(String userName, UpdateUserPayload payload);

    void deleteUserByName(String userName);
}
