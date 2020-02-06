package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;

import java.util.List;

public interface ProductService {

    List<ProductListModel> getAllProductList(int hotNum);

    ProductModel getProduct(long id);
}
