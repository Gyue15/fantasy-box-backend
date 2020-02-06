package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.model.ProductListModel;

import java.util.List;

public interface ProductService {

    List<ProductListModel> getAllProductList(int hotNum);
}
