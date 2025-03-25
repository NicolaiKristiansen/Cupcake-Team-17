package app.entities;

public class User {
    private int id;
    private String email;
    private String password;
    private float money;

    public User(int id, String email,  String password,float money) {
        this.id = id;
        this.email = email;
        this.money = money;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public float getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + money + '\'' +
                '}';
    }
}
