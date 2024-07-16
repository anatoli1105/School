package test333.Test333.servis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import test333.Test333.model.Student;
import test333.Test333.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServis {

    private final StudentRepository studentRepository;
    private final static Logger logger = LoggerFactory.getLogger(StudentServis.class);


    public StudentServis(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        logger.info("method of searching for a student by id");

        return studentRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        logger.info("student removal method");
        return studentRepository.findById(id).
                map(entity -> {
                    studentRepository.delete(entity);
                    return true;
                }).orElse(false);


    }

    public Student update(Student student) {
        logger.info("student adding method");
        return studentRepository.save(student);

    }

    public Collection<Student> getAll() {
        logger.info("method for getting a list of all students");
        return studentRepository.findAll();
    }

    public Collection<Student> byAge(int age) {
        logger.info("method for obtaining a list of students by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("method for obtaining a list of students by age according to specified restrictions");
        return studentRepository.findByAgeBetween(min, max);
    }

    public int studentInSchool() {
        logger.info("this method outputs the number of students");
        return studentRepository.countStudent();
    }

    public double getStudentsAge() {
        logger.info("the method of average age of students was called");
        return studentRepository.avgAge();
    }

    public Collection<Student> getStudentsLimit() {
        logger.info("list of students with limited number of students");
        return studentRepository.getLastFive();
    }


}


