package ap.dao.daoimpl;

import ap.dao.BasicDAO;

import ap.dao.ExersiceDAO;
import ap.entity.Exercise;

public class ExerciseDAOImpl extends BasicDAOImpl<Exercise> implements ExersiceDAO {
    public ExerciseDAOImpl() {super(Exercise.class);}
}
