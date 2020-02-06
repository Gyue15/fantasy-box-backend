package cn.edu.nju.fantasybox.model;

import lombok.Data;

@Data
public class ProductModel {

    private long id;

    private String productName;

    private String imgUrl;

    private String fileUrl;

    private String description;

}
