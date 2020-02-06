package cn.edu.nju.fantasybox.entity;

import lombok.Data;

@Data
public class TagEntity {

    private long id;

    private String tagName;

    private long productId;
}
