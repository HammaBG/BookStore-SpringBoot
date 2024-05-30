package com.gestion.projetbookstore.service;

import com.gestion.projetbookstore.entities.User;

import java.util.List;

public interface IServiceUser {
    public User createUser(User user);
    String encodePassword(String password);
    public User updateUser(User user);
    public void deleteUser(User user);
    public User findUserById(int id);
    public List<User> findAllUsers();
}
