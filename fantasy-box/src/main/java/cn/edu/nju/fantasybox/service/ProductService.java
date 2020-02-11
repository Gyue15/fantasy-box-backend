package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<ProductListModel> getAllProductList(int hotNum);

    ProductModel getProduct(long id);

    List<ProductModel> getMyProduct(int userId);

    ProductModel postProduct(MultipartFile file,String description,String title,List<String> tags);
}
