package jsl28.exam.project.repository;

import jsl28.exam.project.model.Baby;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jakob on 19-05-2018.
 */
public interface BabyRepository extends CrudRepository<Baby, String> {
    List<Baby> findBySitter_Id(String sitter_Id);
    List<Baby> findByName(String name);
}