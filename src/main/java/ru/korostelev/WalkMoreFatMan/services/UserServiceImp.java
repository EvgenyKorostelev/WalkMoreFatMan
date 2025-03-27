package ru.korostelev.WalkMoreFatMan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewUserPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateUserPayload;
import ru.korostelev.WalkMoreFatMan.entity.User;
import ru.korostelev.WalkMoreFatMan.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(NewUserPayload payload) {
        return userRepository.save(
                new User(null, payload.name(), payload.gender(), payload.email(),
                        payload.age(), payload.weight(), payload.height(), payload.target()));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new NoSuchElementException("user.not_found"));
    }

    @Override
    public User updateUser(String userName, UpdateUserPayload payload) {
        Optional<User> user = userRepository.findByName(userName);
        return user.map(value -> userRepository.save(new User(user.get().getId(), payload.name(), payload.gender(),
                                payload.email(), payload.age(), payload.weight(), payload.height(), payload.target())))
                .orElse(null);

    }

    @Override
    public void deleteUserByName(String userName) {
        userRepository.deleteByName(userName);
    }
}
