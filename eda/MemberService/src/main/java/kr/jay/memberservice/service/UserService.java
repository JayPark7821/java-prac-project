package kr.jay.memberservice.service;

import kr.jay.memberservice.entity.UserEntity;
import kr.jay.memberservice.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(String loginId, String userName) {
        return userRepository.save(
            new UserEntity(loginId, userName)
        );
    }

    public UserEntity modifyUser(String loginId, String userName) {
        UserEntity user = userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setUserName(userName);
        return userRepository.save(user);
    }

    public UserEntity getUser(String loginId) {
        return userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
