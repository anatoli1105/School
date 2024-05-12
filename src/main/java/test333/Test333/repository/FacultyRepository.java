package test333.Test333.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test333.Test333.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findByColor(String color);
}
