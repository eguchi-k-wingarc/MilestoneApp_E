package com.example.mils.demo.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void create(String email, String password, String authorities) {
        String hashedPassword = passwordEncoder.encode(password);
        userRepository.create(email, hashedPassword, authorities);
    }

    @Transactional
    public void update(long id,String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        userRepository.update(id, email, hashedPassword);
    }
}
