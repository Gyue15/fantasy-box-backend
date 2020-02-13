package cn.edu.nju.fantasybox.model;

import lombok.Data;

@Data
public class UserModel {
    private long id;

    private String username;

    private String token;

    private String email;

    private String qrCodeUrl;

    private String avatarUrl;

}
