package com.camping.erp.domain.user;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter @Setter
    public static class JoinDTO {
        private String name;
        private String email;
        private String phone;
        private String password;

        public User toEntity() {
            return User.builder()
                    .username(email)
                    .password(password)
                    .name(name)
                    .email(email)
                    .phone(phone)
                    .role(com.camping.erp.domain.user.enums.UserRole.USER)
                    .status(com.camping.erp.domain.user.enums.UserStatus.ACTIVE)
                    .build();
        }
    }

    @Getter @Setter
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
