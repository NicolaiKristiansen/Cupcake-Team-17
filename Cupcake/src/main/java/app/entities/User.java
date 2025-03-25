package app.entities;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String role;

    public User(int userId, String userName, String role, String password) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
