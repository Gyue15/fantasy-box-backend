package cn.edu.nju.fantasybox.util;

import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class FileHelper {

    @Value("${file-service.url-prefix}")
    private String urlPrefix;

    public String readFirstLine(String filePath) {
        File file = new File(filePath);
        String str = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String addUrlPrefix(String rawUrl) {
        return rawUrl != null ? urlPrefix + rawUrl : null;
    }

    public ProductModel addUrlPrefix(ProductModel productModel) {
        productModel.setFileUrl(addUrlPrefix(productModel.getFileUrl()));
        productModel.setImgUrl(addUrlPrefix(productModel.getImgUrl()));
        productModel.setUserAvatar(addUrlPrefix(productModel.getUserAvatar()));
        return productModel;
    }

    public UserModel addUrlPrefix(UserModel userModel) {
        userModel.setAvatarUrl(addUrlPrefix(userModel.getAvatarUrl()));
        userModel.setQrCodeUrl(addUrlPrefix(userModel.getQrCodeUrl()));
        return userModel;
    }
}
