package com.example.mils.demo.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
public class UserEntity {
    /**
     * authoritiesカラムの定数
     * 
     * "ROLE_ADMIN"：管理者権限。
     * "ROLE_USER"：一般ユーザー権限。
     */
    public static final String DEFAULT_AUTHORITIES = "ROLE_USER";
    public static final String ADMIN_AUTHORITIES = "ROLE_ADMIN";
    
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String password;
    private String authorities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}