package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.model.UploadModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface FileService {

    void getFile(String filePath, OutputStream outputStream);

    boolean isPic(String suffixName);

    UploadModel upload(long userId, MultipartFile file);
}
