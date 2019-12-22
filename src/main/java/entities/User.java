package entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "nationalCode", nullable = false, length = 10)
    private String nationalCode;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday", length = 10)
    private String birthday;

    @OneToMany(mappedBy = "User")
    private ArrayList<Article> articles = new ArrayList<>();

    //constructor =======================================

    public User() {
    }

    public User(String username, String nationalCode, String password, String birthday, ArrayList<Article> articles) {
        this.username = username;
        this.nationalCode = nationalCode;
        this.password = password;
        this.birthday = birthday;
        this.articles = articles;
    }
    // =================================================

    public Long getId() {
        return id;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
