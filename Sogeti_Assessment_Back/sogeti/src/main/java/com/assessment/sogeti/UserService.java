package com.assessment.sogeti;

public interface UserService {
    User getUserByEmail(String email);
    User findByEmail(String email);
    void saveUser(User user);
}
