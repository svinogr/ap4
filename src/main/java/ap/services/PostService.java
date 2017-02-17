package ap.services;

import ap.entity.EntityForXML.PostXML;
import ap.entity.EntityForXML.UserXML;
import org.springframework.validation.BindingResult;

public interface PostService {
    PostXML createPost(PostXML postXML);

    boolean changePost(PostXML postXML);

    boolean deletePost(int id);

    PostXML copyPost(int id);

    PostXML ratePost(PostXML postXML);

    PostXML getPostXML(int id);

    UserXML getALLPostXML();

    PostXML validPostXML(PostXML postXML, BindingResult bindingResult);
}
