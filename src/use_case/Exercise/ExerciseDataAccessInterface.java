package use_case.Exercise;

import entity.UserProfile;

public interface ExerciseDataAccessInterface {
    UserProfile get(String username);

    void save(UserProfile user);

    void updateCalories(String username, float calories);
}
