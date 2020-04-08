package cn.edu.nju.fantasybox.service.impl;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.UploadModel;
import cn.edu.nju.fantasybox.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileServiceTest {
    private FileService fileService;

    public FileServiceTest(FileService fileService) {
        this.fileService = fileService;
    }

    private final Logger logger = LoggerFactory.getLogger(FileServiceTest.class);


    public void test(){
        File file = new File("/Users/shea/Pictures/timg.jpeg");
        try {
            InputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("f1",inputStream);
            UploadModel uploadModel = fileService.upload(1,multipartFile);
            uploadModel.getToken();
            uploadModel.getUrl();
            logger.info("uploadModel",uploadModel);
            fileService.getFile("eb0cf55d-048f-4701-8657-dbc6983ddf7d",new ByteArrayOutputStream());
            fileService.getFile("a",new ByteArrayOutputStream());
            fileService.isPic(".jpeg");
            fileService.isPic(".aaa");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BusinessException e){
            logger.error("fileTest",e);
        }
    }

}
