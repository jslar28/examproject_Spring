package jsl28.exam.project.repository;

import jsl28.exam.project.model.Sitter;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jakob on 19-05-2018.
 */
public interface SitterRepository extends CrudRepository<Sitter, Integer> {
}