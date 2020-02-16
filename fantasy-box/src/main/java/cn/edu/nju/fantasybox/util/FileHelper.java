package cn.edu.nju.fantasybox.util;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FileHelper {

    @Value("${file-service.url-prefix}")
    private String urlPrefix;

    @Value("${file-service.local}")
    private String localPath;

    private final Logger logger = LoggerFactory.getLogger(FileHelper.class);


    public String readFirstLine(String filePath) {
        File file = new File(filePath);
        String str = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            str = bufferedReader.readLine();
        } catch (Exception e) {
            logger.error("context",e);
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

    public String saveFile(MultipartFile file) {
        // 文件保存路径
        String filePath = this.localPath + System.currentTimeMillis() + file.getOriginalFilename();
        // 文件url
        String fileUrl = System.currentTimeMillis() + file.getOriginalFilename();
        if (file.isEmpty()) {
            throw new BusinessException(ResultEnums.FILE_NOT_FOUND);
        }
        File dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists() && !dest.getParentFile().mkdirs()) {
            throw new BusinessException(ResultEnums.FILE_UPLOAD_ERROR);
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new BusinessException(ResultEnums.FILE_UPLOAD_ERROR);
        }
        return fileUrl;
    }
}
