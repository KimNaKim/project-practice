package com.camping.erp.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse.ListDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse.ListDTO::new)
                .toList();
    }

    @Transactional
    public void updateRole(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.toggleRole();
    }

    @Transactional
    public void join(UserRequest.JoinDTO request) {
        userRepository.findByUsername(request.getEmail()).ifPresent(user -> {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        });
        userRepository.save(request.toEntity());
    }

    public UserResponse.LoginDTO login(UserRequest.LoginDTO request) {
        // 직접 구현하세요.
        return null;
    }

    public UserResponse.DetailDTO findUser(Long id) {
        // 직접 구현하세요.
        return null;
    }
}
