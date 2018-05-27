package jsl28.exam.project.service;

import jsl28.exam.project.model.SitterUser;
import jsl28.exam.project.repository.SitterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob on 22-05-2018.
 */

@Service
public class SitterUserService {

    @Autowired
    private SitterUserRepository sitterUserRepository;
    @Autowired
    private SitterService sitterService;

    public SitterUser findByUsername(String username) {
        return sitterUserRepository.findByUsername(username);
    }

    public void createDummyUsers() {
        if (sitterUserRepository.findByUsername("jsl") == null && sitterUserRepository.findByUsername("kw") == null) {
            sitterUserRepository.save(new SitterUser("jsl", "123", sitterService.getSitter(1)));
            sitterUserRepository.save(new SitterUser("kw", "123", sitterService.getSitter(2)));
        } else {
            System.out.println("Already created dummy sitter users.");
        }
    }

    public SitterUser findSitterUserByUsernameAndPassword(String username, String password) {
        return sitterUserRepository.findFirstByUsernameAndPassword(username, password);
    }

    public void addSitterUser(SitterUser sitterUser) {
        sitterUserRepository.save(sitterUser);
    }

    public SitterUser findById(int id) {
        return sitterUserRepository.findById(id);
    }

    public void updateSitterUser(SitterUser sitterUser) {
        sitterUserRepository.save(sitterUser);
    }

    public List<SitterUser> getAllUser() {
        List<SitterUser> sitterUsers = new ArrayList<>();
        sitterUserRepository.findAll().forEach(sitterUsers::add);
        return sitterUsers;
    }
}