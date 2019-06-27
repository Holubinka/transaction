package com.holubinka.transaction.services;

import com.holubinka.transaction.model.User;

public interface UserServices {

    User save(User user);
    User getByEmail(String email);
}
