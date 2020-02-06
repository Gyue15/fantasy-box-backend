package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    List<ProductEntity> findProductByTypes(String tagName);

    List<ProductEntity> findHotProduct(int num);
}
