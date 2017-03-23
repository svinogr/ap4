package ap.dao;

import ap.entity.Post;

import java.util.List;

public interface PostDAO extends BasicDAO<Post> {
    List<Post> getListAllPost(int start);
}
