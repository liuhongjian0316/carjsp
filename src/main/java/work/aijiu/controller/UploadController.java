package work.aijiu.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.aijiu.common.config.FileConfig;
import work.aijiu.common.utils.MultipartFileToFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class UploadController {


    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        File file1 = MultipartFileToFile.multipartFileToFile(file);
        StorePath storePath = fastFileStorageClient.uploadFile(null, new FileInputStream(file1), file1.length(), MultipartFileToFile.getType(file));
        MultipartFileToFile.delteTempFile(file1);
        return FileConfig.service +storePath.getFullPath();
    }
}
