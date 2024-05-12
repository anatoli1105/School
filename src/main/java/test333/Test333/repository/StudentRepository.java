package test333.Test333.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test333.Test333.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Collection<Student> findByAge(int age);
    public Collection<Student> findByAgeBetween(int min,int max);

}
