package ap.services.servicesimpl;

import ap.entity.UploadImageException;
import ap.services.InfoUserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.UUID;

public class InfoUserServiceImpl implements InfoUserService {

    @Override
    public String upload(MultipartFile file) throws UploadImageException, IOException {
        validateImage(file);
        byte[] base= Base64.encodeBase64(file.getBytes());
        String name = new String(base,"UTF-8");
        return name;
    }

    private void validateImage(MultipartFile file) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/jpg") && !file.getContentType().equals("image/gif") && !file.getContentType().equals("image/png")){
            throw new UploadImageException("not supported MyFormat fille");
        }
    }
}
