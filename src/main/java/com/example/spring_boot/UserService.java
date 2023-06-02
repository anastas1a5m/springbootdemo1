package com.example.spring_boot;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public User addFavoriteBook(String username, Book book) {
        User user = userRepository.findByUsername(username);
        user.addFavoriteBook(book);
        return userRepository.save(user);
    }

    public User addFavoriteAuthor(String username, Author author) {
        User user = userRepository.findByUsername(username);
        user.addFavoriteAuthor(author);
        return userRepository.save(user);
    }

    public User getUserInfo(String username) {
        return userRepository.findByUsername(username);
    }
}
