package ap.services.servicesimpl;

import ap.config.HibernateConfig;
import ap.dao.PostDAO;
import ap.entity.EntityForXML.PostXML;
import ap.entity.EntityForXML.UserXML;
import ap.entity.Post;
import ap.entity.User;
import ap.services.PostService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Component
@Import(HibernateConfig.class)
public class PostServiceImpl implements PostService {

    @Autowired
    PostDAO postDAO;

    @Autowired
    UserServices userServices;


    @Override
    @Transactional
    public PostXML createPost(PostXML postXML) {
        try {
            User user = userServices.getLoggedUser();
            user = userServices.getById(user.getId());
            Post post = new Post();
            post.setDescription(postXML.getDescription());
            post.setDate(new Date());
            post.setLink(postXML.getLink());
            post.setParentid(user);
            int idpost = postDAO.add(post);
            postXML.setId(idpost);
            postXML.setDate(post.getDate());
            postXML.setParentid(user.getId());
            return postXML;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean changePost(PostXML postXML) {
        if (postDAO.checkItBD(postXML.getId())) {
            Post post = postDAO.getById(postXML.getId());
            if (userServices.allow(post.getParentid().getId())) {
                post.setDescription(postXML.getDescription());
                postDAO.update(post);
                return true;
            }
        }
        return false;

    }

    @Transactional
    @Override
    public boolean deletePost(int id) {
        try {
            if (postDAO.checkItBD(id)) {
                Post post = postDAO.getById(id);
                if (userServices.allow(post.getParentid().getId())) {
                    postDAO.delete(post);
                    return true;
                }
            }
            return false;
        } catch (HibernateException e) {
            return false;
        }

    }

    @Override
    public PostXML copyPost(int id) {
        return null;
    }

    @Override
    public PostXML ratePost(PostXML postXML) {
        return null;
    }

    @Override
    @Transactional
    public PostXML getPostXML(int id) {
        if (postDAO.checkItBD(id)) {
            Post post = postDAO.getById(id);
            PostXML postXML = new PostXML(post);
            return postXML;
        }
        return null;
    }

    @Override
    @Transactional
    public UserXML getALLPostXML() {
        List<Post> postList = new ArrayList<>(0);
        List<PostXML> postListXML = new ArrayList<>(0);
        postList = postDAO.getAll();
        if(postList.size()>0) {
            for (Post post : postList) {
                PostXML postXML = new PostXML(post);
                postListXML.add(postXML);
            }
            UserXML userXML = new UserXML();
            userXML.setWorkoutXML(null);
            userXML.setPostXMLs(postListXML);
            return userXML;
        } return null;
    }

    @Override
    public PostXML validPostXML(PostXML postXML, BindingResult bindingResult) {
        Map<String, String> mapErrors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            mapErrors.put(error.getField(), error.getDefaultMessage());
        }
        postXML.setDescription(mapErrors.get("description"));
        return postXML;

    }
}
