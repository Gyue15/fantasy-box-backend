package cn.edu.nju.fantasybox.entity;

import lombok.Data;

@Data
public class UserEntity {

    private long id;

    private String username;

    private String password;

    private String email;

    private String qrCodeUrl;

    private String avatarUrl;

    private String token;

    private Boolean activated;
}
