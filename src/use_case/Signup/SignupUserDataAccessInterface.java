package use_case.Signup;

import data_access.DataAccessObject;
import entity.UserProfile;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);


    void save(UserProfile user);
}
