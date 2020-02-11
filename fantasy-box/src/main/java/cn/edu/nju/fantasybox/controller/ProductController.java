package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final int DEFAULT_HOT_NUM = 9;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all-list")
    public List<ProductListModel> getAllProductList(@RequestParam(name = "hot-num", required = false) Integer hotNum) {
        if (hotNum == null) {
            hotNum = DEFAULT_HOT_NUM;
        }
        return productService.getAllProductList(hotNum);
    }

    @GetMapping("get-product")
    public ProductModel getProduct(@RequestParam("id") Long id) {
        return productService.getProduct(id);
    }

//    @PostMapping("post-product")
//    public ProductModel postProduct(@RequestParam("file")MultipartFile file,@RequestParam("description")String description,
//                                    @RequestParam("title")String title,@RequestParam("tags")List<String> tags){
//
//        return new ProductModel();
//    }
//
//    @GetMapping("get-my-product")
//    public List<ProductModel> getMyProduct(){
//        return new ArrayList<>();
//    }

}
