package ap.services.servicesimpl;

import ap.services.UploadService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadServiceImpl implements UploadService {
    @Override
    public String upload(MultipartFile file) {
        if(!file.isEmpty()){
            UUID id = UUID.randomUUID();
            String name= id.toString();
            File saveFile= new File("/images/"+name);
        }

        return null;
    }
}
