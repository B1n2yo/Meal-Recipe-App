package use_case.Signup;

import entity.UserProfile;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);


    void save(UserProfile user);
}
