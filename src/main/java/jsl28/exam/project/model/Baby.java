package jsl28.exam.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Jakob on 19-05-2018.
 */

@Entity
public class Baby {

    @Id
    private String id;
    private String name;
    private int age;
    @ManyToOne
    private Sitter sitter;

    public Baby(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Baby() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }
}