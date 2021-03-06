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
import java.util.UUID;

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

    public void getFile(String filePath, OutputStream outputStream) {
        File file = new File(localPath + filePath);
        FileInputStream inputStream = null;
        if (!(file.exists() && file.canRead())) {
            throw new BusinessException(ResultEnums.FILE_NOT_FOUND);
        }
        try {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[20 * 1024];
            int cnt;
            while ((cnt = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, cnt);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            logger.error("context", e);
            throw new BusinessException(ResultEnums.FILE_NOT_FOUND);
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
                outputStream.close();
            } catch (IOException e) {
                logger.error("context",e);
            }
        }
    }

    public String addUrlPrefix(String rawUrl) {
        return rawUrl != null ? urlPrefix + rawUrl : null;
    }

    public ProductModel addUrlPrefix(ProductModel productModel) {
        productModel.setFileUrl(addUrlPrefix(productModel.getFileUrl()));
        productModel.setImgUrl(addUrlPrefix(productModel.getImgUrl()));
        productModel.setUserAvatar(addUrlPrefix(productModel.getUserAvatar()));
        productModel.setQrCode(addUrlPrefix(productModel.getQrCode()));
        return productModel;
    }

    public UserModel addUrlPrefix(UserModel userModel) {
        userModel.setAvatarUrl(addUrlPrefix(userModel.getAvatarUrl()));
        userModel.setQrCodeUrl(addUrlPrefix(userModel.getQrCodeUrl()));
        return userModel;
    }

    public String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ResultEnums.FILE_NOT_FOUND);
        }
        // 文件url
        String fileName = file.getOriginalFilename();
        int suffixIndex;
        if (fileName == null || (suffixIndex = fileName.lastIndexOf(".")) == -1) {
            fileName = UUID.randomUUID().toString();
        } else {
            fileName = UUID.randomUUID().toString() + fileName.substring(suffixIndex).toLowerCase();
        }
        String fileUrl =  fileName;
        // 文件保存路径
        String filePath = this.localPath + fileUrl;
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
