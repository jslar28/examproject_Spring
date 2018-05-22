package jsl28.exam.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob on 21-05-2018.
 */

@Entity
public class SitterUser {

    @Id @GeneratedValue
    private int id;
    private String username;
    private String password;
    @OneToOne
    private Sitter sitter;
    private ArrayList<String> inbox = new ArrayList<>();

    public SitterUser(String username, String password, Sitter sitter) {
        this.username = username;
        this.password = password;
        this.sitter = sitter;
    }

    public SitterUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }
}