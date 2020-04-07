package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.UploadModel;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class FileControllerTest {
    private MockMvc mockMvc;
    private FileController fileController;

    public FileControllerTest(MockMvc mockMvc,FileController fileController) {
        this.mockMvc = mockMvc;
        this.fileController = fileController;
    }

    public void testUpload(){
        File file = new File("/Users/shea/Pictures/timg.jpeg");
        try {
            InputStream inputStream = new FileInputStream(file);
//            MultipartFile multipartFile = new MockMultipartFile("f1",inputStream);
//            mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/file/upload")
//                    .file(
//                            new MockMultipartFile("file",inputStream)
//                    ));
            MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
            HttpSession session = new MockHttpSession();
            session.setAttribute("userId",1l);
            mockHttpServletRequest.setSession(session);
            fileController.uploadFile(new MockMultipartFile("file", inputStream), mockHttpServletRequest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UploadModel uploadModel = new UploadModel("756","8w");
        System.out.println(uploadModel);

    }

    public void testGetFile() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        HttpSession session = new MockHttpSession();
        session.setAttribute("userId",1l);
        mockHttpServletRequest.setSession(session);
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        fileController.getFile("97e224b1-1550-4c41-96ea-616589ed7fd8",mockHttpServletRequest,mockHttpServletResponse);
        fileController.getFile("1581763203671BD6CF92C29102D6B2C2503042D2BABB9.jpg",mockHttpServletRequest,mockHttpServletResponse);
//        try {
//            mockMvc.perform(get("/api/file/get/97e224b1-1550-4c41-96ea-616589ed7fd8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
