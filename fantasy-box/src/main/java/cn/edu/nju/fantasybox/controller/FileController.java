package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channel;
import java.util.Arrays;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${file-service.local}")
    private String localPath;

    private String[] pics = {".jpg", ".jpeg", ".png", ".gif"};

    @GetMapping("/get/{file-path}")
    public void getFile(@PathVariable("file-path") String filePath, HttpServletRequest request,
                        HttpServletResponse response) {
        File file = new File(localPath + filePath);
        logger.info("filePath: " + localPath + filePath);
        FileInputStream inputStream = null;
        if (!(file.exists() && file.canRead())) {
            logger.error("File not exist");
            throw new BusinessException(ResultEnums.FILE_NOT_FOUND);
        }
        try {
            // 后缀名
            int suffixIndex = filePath.lastIndexOf(".");
            if (suffixIndex != -1) {
                String suffixName = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
                logger.info("get file, suffixName: " + suffixName);
                if (Arrays.binarySearch(pics, suffixName, String::compareTo) != -1) {
                    if (".gif".equals(suffixName)) {
                        response.setContentType("image/gif;charset=utf-8");
                        logger.info("gif pics");
                    } else {
                        response.setContentType("image/png;charset=utf-8");
                        logger.info("normal pics: " + suffixName);
                    }
                }
            }
            inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
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
        }
    }

}
