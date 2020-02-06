package cn.edu.nju.fantasybox.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductEntity {

    private long id;

    private String productName;

    private long userId;

    private String userName;

    private String coverUrl;

    private String fileUrl;

    private String description;

    private Date releaseTime;

    private Date modifyTime;

    private List<TagEntity> tagList;

    private String selectTag;
}
