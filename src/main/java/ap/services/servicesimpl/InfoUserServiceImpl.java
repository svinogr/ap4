package ap.services.servicesimpl;

import ap.entity.UploadImageException;
import ap.services.InfoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.UUID;

public class InfoUserServiceImpl implements InfoUserService {
    @Autowired
    ServletContext context;
    @Override
    public byte[] upload(MultipartFile file) throws UploadImageException, IOException {
        validateImage(file);
       // UUID id = UUID.randomUUID();
        byte[] name =file.getBytes();
        System.err.println(name+"|"+file.getSize());
       // File saveFile = new File(name);
      /*  try {
            name = context.getRealPath("\\")+"\\images\\"+id.toString();
            System.err.println(name+"|"+file.getSize());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(saveFile));
            bufferedOutputStream.write(file.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }*/

        return name;
    }

    private void validateImage(MultipartFile file) {
        if (!file.getContentType().equals("image/jpeg")) {
            throw new UploadImageException("not supported MyFormat fille");
        }
    }
}
