package com.camping.erp.domain.user;

import lombok.Getter;
import lombok.Setter;

public class UserResponse {

    @Getter @Setter
    public static class ListDTO {
        private Long id;
        private String username;
        private String name;
        private String email;
        private String phone;
        private String role;
        private String status;

        public ListDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.role = user.getRole().name();
            this.status = user.getStatus().name();
        }
    }

    @Getter @Setter
    public static class LoginDTO {
        // 직접 구현하세요.
    }

    @Getter @Setter
    public static class DetailDTO {
        // 직접 구현하세요.
    }
}
