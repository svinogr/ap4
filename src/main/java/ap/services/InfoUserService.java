package ap.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InfoUserService {
    byte[] upload(MultipartFile file) throws IOException;
}
