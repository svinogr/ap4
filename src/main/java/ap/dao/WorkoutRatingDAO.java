package ap.dao;

import ap.entity.WorkoutRating;

public interface WorkoutRatingDAO extends BasicDAO<WorkoutRating>  {
    WorkoutRating getRate(int userId, int worrkoutId);
}
