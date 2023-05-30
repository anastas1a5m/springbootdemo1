package com.example.spring_boot;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User update(String username,   User userData) {
        User userToUpdate = userRepository.findByUsername(username);
        userToUpdate.setUsername(userData.getUsername());
        userToUpdate.setPassword(userData.getPassword());
        return userRepository.save(userToUpdate);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User save(User user) {
        return userRepository.save(user);
    }
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

}
