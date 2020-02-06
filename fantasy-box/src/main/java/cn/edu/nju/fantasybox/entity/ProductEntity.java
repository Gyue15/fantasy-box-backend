package cn.edu.nju.fantasybox.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductEntity {

    private long id;

    private String productName;

    private long userId;

    private String coverUrl;

    private String fileUrl;

    private String description;

    private Date releaseTime;

    private Date modifyTime;
}
