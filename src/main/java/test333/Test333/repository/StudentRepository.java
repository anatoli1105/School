package test333.Test333.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test333.Test333.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Collection<Student> findByAge(int age);

    public Collection<Student> findByAgeBetween(int min,int max);
    @Query(value = "select count(*) from Student",nativeQuery = true)
    int countStudent();
    @Query(value = "select avg(age) from Student",nativeQuery = true)

    double avgAge();
    @Query(value = "select * from student order by id desc limit 4",nativeQuery = true)
    Collection<Student> getLastFive();
}
