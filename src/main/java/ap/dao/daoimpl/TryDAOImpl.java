package ap.dao.daoimpl;

import ap.dao.TryDAO;
import ap.entity.Try;

public class TryDAOImpl extends BasicDAOImpl<Try> implements TryDAO {
    public TryDAOImpl(){super(Try.class);}
}
