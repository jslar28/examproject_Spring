package jsl28.exam.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.ArrayList;

/**
 * Created by Jakob on 19-05-2018.
 */

@Entity
public class Sitter {

    @Id @GeneratedValue
    private int id;

    @Version
    private Long version;

    private String name;
    private int age;

    private ArrayList<Integer> zipCodes = new ArrayList<>();
    private String email;
    private String telephone;
    private ArrayList<Integer> ratings = new ArrayList<>();
    private int yearsOfExperience;
    private ArrayList<Message> inbox = new ArrayList<>();

    public Sitter(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Sitter(String name, int age, ArrayList<Integer> zipCodes, String email, String telephone, int yearsOfExperience) {
        this.name = name;
        this.age = age;
        this.zipCodes = zipCodes;
        this.email = email;
        this.telephone = telephone;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Sitter() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ArrayList<Integer> getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(ArrayList<Integer> zipCodes) {
        this.zipCodes = zipCodes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public ArrayList<Message> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Message> inbox) {
        this.inbox = inbox;
    }

    public void addToInbox(Message message) {
        this.inbox.add(message);
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Sitter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", zipCodes=" + zipCodes +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", inbox='" + inbox + '\'' +
                ", ratings=" + ratings +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}