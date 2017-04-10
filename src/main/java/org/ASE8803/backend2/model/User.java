package org.ASE8803.backend2.model;

/**
 * Created by ranchy on 2017/4/1.
 */
public class User {
    private boolean hasuser;
    private String email;
    private String username;
    private String firstname;

    public boolean isHasuser() {
        return hasuser;
    }

    public void setHasuser(boolean hasuser) {
        this.hasuser = hasuser;
    }

    private String lastname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
