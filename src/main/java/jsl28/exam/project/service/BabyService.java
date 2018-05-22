package jsl28.exam.project.service;

import jsl28.exam.project.model.Baby;
import jsl28.exam.project.repository.BabyRepository;
import jsl28.exam.project.repository.SitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob on 19-05-2018.
 */

@Service
public class BabyService {

    @Autowired
    private BabyRepository babyRepository;
    @Autowired
    private SitterRepository sitterRepository;

    public ArrayList<Baby> getAllBabies() {
        ArrayList<Baby> babies = new ArrayList<>();
        babyRepository.findAll().forEach(babies::add); //method reference
        return babies;
    }

    public Baby getBaby(String id) {
        return babyRepository.findById(id).orElse(null);
    }

    public List<Baby> getBabyByName(String name) {
        return babyRepository.findByName(name);
    }

    public void addBaby(Baby baby) {
        babyRepository.save(baby);
    }

    public void updateBaby(Baby baby) {
        babyRepository.save(baby);
    }

    public void removeBaby(String id) {
        babyRepository.deleteById(id);
    }

    public void addBabyToSitter(String id) {
        Baby newBaby = new Baby("5", "TestBaby" + id, 6);
        //newBaby.setSitter(sitterRepository.findById(id).orElse(null));
        babyRepository.save(newBaby);
    }

    public void createDummyBabies() {
        babyRepository.save(new Baby("1", "Bob", 2));
        babyRepository.save(new Baby("2", "Bon", 3));
        babyRepository.save(new Baby("3", "Boi", 4));
        babyRepository.save(new Baby("4", "Bro", 5));
    }

    public List<Baby> getAllBabiesForSitter(String id) {
        return babyRepository.findBySitter_Id(id);
    }
}