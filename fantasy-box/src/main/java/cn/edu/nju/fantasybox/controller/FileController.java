package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.annotation.Authentication;
import cn.edu.nju.fantasybox.model.ResponseData;
import cn.edu.nju.fantasybox.service.FileService;
import cn.edu.nju.fantasybox.service.impl.ProductServiceImpl;
import cn.edu.nju.fantasybox.util.ResponseDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/get/{file-path}")
    public void getFile(@PathVariable("file-path") String filePath, HttpServletRequest request,
                        HttpServletResponse response) {
        // 后缀名
        int suffixIndex = filePath.lastIndexOf(".");
        if (suffixIndex != -1) {
            String suffixName = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
            logger.info("get file, suffixName: " + suffixName);
            if (fileService.isPic(suffixName)) {
                if (".gif".equals(suffixName)) {
                    response.setContentType("image/gif;charset=utf-8");
                    logger.info("gif pics");
                } else {
                    response.setContentType("image/png;charset=utf-8");
                    logger.info("normal pics: " + suffixName);
                }
            }
        }

        try {
            fileService.getFile(filePath, response.getOutputStream());
        } catch (IOException e) {
            logger.error("context", e);
        }

    }

    @PostMapping("upload")
    @Authentication
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildSuccess(fileService.upload(userId, file));
    }

}
