package com.example.userserver.users;

import java.time.ZonedDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final PostService postService;

    public UserService(UserRepository userRepository, PostService postService) {
        this.userRepository = userRepository;
        this.postService = postService;
    }

    @Transactional
    public UserInfo createUser(UserRequest userRequest) {

        String hashedPassword = passwordEncoder.encode(userRequest.getPlainPassword());
        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new RuntimeException("Username duplicated");
        }

        User user = new User(userRequest.getUsername(), userRequest.getEmail(), hashedPassword);
        User savedUser = userRepository.save(user);

        return new UserInfo(savedUser);
    }

    public UserInfo getUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        return new UserInfo(user);
    }

    public UserInfo getUserByName(String name) {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            return null;
        }

        return new UserInfo(user);
    }

    public UserInfo signIn(UserRequest signInRequest) {
        User user = null;
        if (signInRequest.getUsername() != null) {
            user = userRepository.findByUsername(signInRequest.getUsername());
        }

        if (user == null) {
            return null;
        }

        boolean isPasswordMatch = passwordEncoder.matches(signInRequest.getPlainPassword(), user.getPassword());
        if (isPasswordMatch) {
            return new UserInfo(user);
        }

        return null;
    }

    public void updateLastPost(int userId, String postId, ZonedDateTime updatedDateTime) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setLastPostId(postId);
        user.setLastPostDatetime(updatedDateTime);
        userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(int id) {
        postService.deactivate(String.valueOf(id));
        try {
            userRepository.deleteById(id);
            throw new RuntimeException("User not found");
        } catch (Exception e) {
            try {
                boolean result = postService.activate(String.valueOf(id));

            } catch (Exception ex) {
                throw new RuntimeException("User not found");
            }
        }
        return true;
    }
}
