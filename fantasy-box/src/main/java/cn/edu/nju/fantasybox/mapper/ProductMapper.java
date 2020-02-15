package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    List<ProductEntity> findProductByTag(String tagName);

    List<ProductEntity> findProductByTagList(List<String> tagList);

    List<ProductEntity> findHotProduct(int num);

    List<ProductEntity> findProductByUserId(long userId);

    ProductEntity select(long id);

    int insertProduct(ProductEntity productEntity);

    List<ProductEntity> search(List<String> keywords);
}
