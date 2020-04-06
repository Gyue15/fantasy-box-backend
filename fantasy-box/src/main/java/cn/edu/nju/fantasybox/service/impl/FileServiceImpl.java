package cn.edu.nju.fantasybox.service.impl;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.model.UploadModel;
import cn.edu.nju.fantasybox.service.FileService;
import cn.edu.nju.fantasybox.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file-service.local}")
    private String localPath;

    private String[] pics = {".jpg", ".jpeg", ".png", ".gif"};

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private FileHelper fileHelper;

    @Autowired
    public FileServiceImpl(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    @Override
    public void getFile(String filePath, OutputStream outputStream) {
        fileHelper.getFile(filePath, outputStream);
    }

    @Override
    public boolean isPic(String suffixName) {
        return Arrays.binarySearch(pics, suffixName) != -1;
    }

    @Override
    public UploadModel upload(long userId, MultipartFile file) {
        logger.info("upload: " + userId);
        String token = fileHelper.saveFile(file);
        String url = fileHelper.addUrlPrefix(token);
        return new UploadModel(url, token);
    }
}
