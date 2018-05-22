package jsl28.exam.project.repository;

import jsl28.exam.project.model.SitterUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jakob on 22-05-2018.
 */
public interface SitterUserRepository extends CrudRepository<SitterUser, Integer> {
    SitterUser findByUsername(String username);
    SitterUser findFirstByPassword(String username);
    SitterUser findFirstByUsernameAndPassword(String username, String password);
}