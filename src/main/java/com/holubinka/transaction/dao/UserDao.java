package com.holubinka.transaction.dao;

import com.holubinka.transaction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, Long> {
    User getByEmail(String email);

    @Override
    User save(User s);
}
