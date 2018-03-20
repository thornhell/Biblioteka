package com.ms.library.repository;

import com.ms.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countAllByEnabled(int enabled);
}
