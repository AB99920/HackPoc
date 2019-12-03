package com.pacman.bytes.demo.repo;

import com.pacman.bytes.demo.entity.User;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsernameIgnoreCase(String username);

}
