package com.example.sweater.service;

import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.security.SecurityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserSevice implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepo.findByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return userDetails;
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> loadUsersByUsernameLike(String username) throws UsernameNotFoundException {
        return userRepo.findUsersByUsernameLike(username);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void updateProfile(User user, String password) {
        user.setPassword(
                passwordEncoder.encode(SecurityValidator.XSSValidate(password)));

        System.out.println(user.getPassword());

        try {
            userRepo.save(user);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
