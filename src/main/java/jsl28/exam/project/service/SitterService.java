package jsl28.exam.project.service;

import jsl28.exam.project.model.Sitter;
import jsl28.exam.project.repository.SitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jakob on 19-05-2018.
 */

@Service
public class SitterService {

    @Autowired
    private SitterRepository sitterRepository;

    public ArrayList<Sitter> getAllSitters() {
        ArrayList<Sitter> sitters = new ArrayList<>();
        sitterRepository.findAll().forEach(sitters::add); //method reference
        return sitters;
    }

    public Sitter getSitter(int id) {
        System.out.println("int: " + id + " | string: " + Integer.toString(id));
        System.out.println("inSitterService: " + sitterRepository.findById(id).orElse(null));
        return sitterRepository.findById((id)).orElse(null);
    }

    public void addSitter(Sitter sitter) {
        sitterRepository.save(sitter);
    }

    public void updateSitter(Sitter sitter) {
        sitterRepository.save(sitter);
    }

    public void removeSitter(int id) {
        sitterRepository.deleteById(id);
    }

    public void createDummySitters() {
        ArrayList<Integer> zipCodes = new ArrayList<>();
        zipCodes.add(2860);
        zipCodes.add(2000);
        sitterRepository.save(new Sitter("Joe", 25, zipCodes, "test1@email.com", "61307580", 2));
        sitterRepository.save(new Sitter("Jon", 35, zipCodes, "test2@email.com", "60307580", 4));
        sitterRepository.save(new Sitter("Jay", 45, zipCodes, "test3@email.com", "50307580", 6));
    }

    public List<Sitter> getAllSittersByZipCode(int zipCode) {
        List<Sitter> sitters = getAllSitters().stream().filter(sitter ->
                sitter.getZipCodes().contains(zipCode)).collect(Collectors.toList());
        System.out.println("FB ZIPCODES: " + sitters);
        return sitters;
    }
}