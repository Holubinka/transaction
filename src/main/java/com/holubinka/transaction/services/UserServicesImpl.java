package com.holubinka.transaction.services;

import com.holubinka.transaction.dao.UserDao;
import com.holubinka.transaction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
