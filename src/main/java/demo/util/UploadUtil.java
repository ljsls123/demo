package demo.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    public static String upload(MultipartFile file, String basePath) {
        String uuid = UUID.randomUUID().toString();
        String orgFileName = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(orgFileName);
        String fileName = uuid + ext;
        try {
            File targetFile = new File(basePath, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
            return fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
