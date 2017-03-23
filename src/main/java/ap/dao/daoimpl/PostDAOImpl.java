package ap.dao.daoimpl;

import ap.dao.PostDAO;
import ap.entity.Post;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl extends BasicDAOImpl<Post> implements PostDAO {
    public PostDAOImpl() {
        super(Post.class);
    }
    @Override
    @Transactional
    public List<Post> getListAllPost(int start) {
        List<Post> list = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Post.class);
        criteria.setMaxResults(20);
        criteria.setFirstResult(start);
        list.addAll(criteria.list());
        return list;
    }
}
