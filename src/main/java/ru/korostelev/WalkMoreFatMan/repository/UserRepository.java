package ru.korostelev.WalkMoreFatMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korostelev.WalkMoreFatMan.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
