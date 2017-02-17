package ap.dao.daoimpl;

import ap.dao.PostDAO;
import ap.entity.Post;

public class PostDAOImpl extends BasicDAOImpl<Post> implements PostDAO {
    public PostDAOImpl() {
        super(Post.class);
    }
}
