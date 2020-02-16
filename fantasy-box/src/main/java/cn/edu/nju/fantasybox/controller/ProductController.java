package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.annotation.Authentication;
import cn.edu.nju.fantasybox.model.*;
import cn.edu.nju.fantasybox.service.ProductService;
import cn.edu.nju.fantasybox.util.ResponseDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Value("${hot-num}")
    private int DEFAULT_HOT_NUM;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all-list")
    public ResponseData getAllProductList(@RequestParam(name = "hot-num", required = false) Integer hotNum) {
        if (hotNum == null) {
            hotNum = DEFAULT_HOT_NUM;
        }
        return ResponseDataUtil.buildSuccess(productService.getAllProductList(hotNum));
    }

    @GetMapping("get-product")
    public ResponseData getProduct(@RequestParam("id") Long id) {
        return ResponseDataUtil.buildSuccess(productService.getProduct(id));
    }

    @PostMapping("post-product")
    @Authentication
    public ResponseData postProduct(@RequestParam("file") MultipartFile file,
                                    @RequestParam("description") String description,
                                    @RequestParam("title") String title, @RequestParam("tags") List<String> tags,
                                    HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        logger.info(httpSession.getId());
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildSuccess(productService.postProduct(file, description, title, tags, userId));
    }

    @GetMapping("get-my-product")
    @Authentication
    public ResponseData getMyProduct(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildSuccess(productService.getMyProduct(userId));
    }

    @GetMapping("search")
    public ResponseData search(@RequestParam("keywords") String keywords) {
        String[] keywordList = keywords.trim().split("\\s+");
        return ResponseDataUtil.buildSuccess(productService.search(Arrays.asList(keywordList)));
    }

}
