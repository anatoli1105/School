package test333.Test333.servis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import test333.Test333.Main;
import test333.Test333.model.Student;
import test333.Test333.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

import static test333.Test333.Test333Application.main;

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

    public Collection<String> getAllStudentNameSelection() {
        logger.info("method of selecting students by name");
        return studentRepository.findAll()
                .stream()
                .map(o -> o.getName())
                .map(o -> o.toUpperCase())
                .filter(n -> n.startsWith("A"))
                .sorted((n1, n2) -> n1.compareTo(n2))
                .toList();
    }

    public double getMidAgeStudents() {
        return studentRepository.findAll()
                .stream()
                .map(o -> o.getAge())
                .mapToInt(o -> o)
                .average().orElseGet(() -> 0.0);
    }

    public void printParallelStudent() {
        var students = studentRepository.findAll();

        Main main = new Main();
        new Thread(() -> {
            System.out.println(students.get(0).getName());
            System.out.println(students.get(1).getName());
        })
                .start();
        new Thread(() -> {

            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        })
                .start();
        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        })
                .start();


    }

    public void printSynchronizedStudent() {
        var students = studentRepository.findAll();


        new Thread(() -> {

            studentPrint(students.get(0));
            studentPrint(students.get(1));
        })
                .start();
        new Thread(() -> {

            studentPrint(students.get(2));
            studentPrint(students.get(3));
        })
                .start();
        new Thread(() -> {
            studentPrint(students.get(4));
            studentPrint(students.get(5));
        })
                .start();
    }

    public static synchronized void studentPrint(Student student) {
        System.out.println(student.getName());
    }


}





