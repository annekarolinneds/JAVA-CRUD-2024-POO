package user;

import entity.Entity;

import java.time.LocalDateTime;

public class User extends Entity {

    private String name;

    private String email;

    private String password;

    private LocalDateTime lastAccess;

    private boolean active = true;

    public User(){

    }

    public User(String name, String email, String password, LocalDateTime lastAccess, boolean active) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setLastAccess(lastAccess);
        setActive(active);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name +  '\'' +
                ", email='" + email +  '\'' +
                ", password='" + password +  '\'' +
                ", lastAccess=" + lastAccess +
                ", active=" + active +  '}' + "\n" ;
    }

}
