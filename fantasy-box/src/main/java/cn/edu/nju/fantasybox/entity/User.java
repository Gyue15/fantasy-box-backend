package cn.edu.nju.fantasybox.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userName;
    private String passWord;
    private String email;
}
