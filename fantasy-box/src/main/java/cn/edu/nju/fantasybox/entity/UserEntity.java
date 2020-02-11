package cn.edu.nju.fantasybox.entity;

import lombok.Data;

@Data
public class UserEntity {

    private long id;

    private String userName;

    private String passWord;

    private String email;

    private String qrCodeUrl;

    private String avatarUrl;
}
