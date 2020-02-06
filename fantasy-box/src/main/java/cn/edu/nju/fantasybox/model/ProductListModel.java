package cn.edu.nju.fantasybox.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductListModel {

    private String name;

    private List<ProductModel> content;
}
