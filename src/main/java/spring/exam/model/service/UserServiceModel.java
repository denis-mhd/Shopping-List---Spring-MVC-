package spring.exam.model.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UserServiceModel extends BaseServiceModel{

    private String username;
    private String password;
    private String email;

    public UserServiceModel() {
    }

    @Length(min = 3, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
