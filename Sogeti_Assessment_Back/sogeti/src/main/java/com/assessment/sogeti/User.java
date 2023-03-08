package com.assessment.sogeti;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;


/*
This class represents a user in the system. It is an Entity class, meaning that it is mapped to a database table.
It contains fields such as the user's unique identifier, username, password, and authentication token.
The class has methods for setting and getting these fields, as well as for checking the validity of a password and finding a username.
Additionally, it has a method for getting a subscription by category, which returns null in the current implementation.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String authToken;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public Long getId() {
        return id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String findUsername(String username) {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Subscription getSubscriptionByCategory(Category category) {
        return null;
    }
}
