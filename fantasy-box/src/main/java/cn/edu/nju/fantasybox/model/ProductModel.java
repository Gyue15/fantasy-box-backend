package cn.edu.nju.fantasybox.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductModel {

    private long id;

    private String name;

    private String username;

    private String userAvatar;

    private String imgUrl;

    private String fileUrl;

    private String description;

    private Date releaseTime;

    private Date modifyTime;

    private List<String> tagList;

    private String qrCode;

}
