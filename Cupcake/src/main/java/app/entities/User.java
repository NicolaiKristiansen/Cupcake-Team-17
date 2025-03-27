package app.entities;

public class User {
    //Class made by Sofus
    //Altered by Nicolai to work with new database table

    private int id;
    private String email;
    private String password;
    private float money;
    private String role;

    public User(int id, String email, String password, float money, String role) {
        this.id = id;
        this.email = email;
        this.money = money;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, float money, String role) {
        this.email = email;
        this.password = password;
        this.money = money;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", role='" + role + '\'' +
                '}';
    }
}
