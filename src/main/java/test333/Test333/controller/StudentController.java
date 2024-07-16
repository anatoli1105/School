package test333.Test333.controller;

import org.springframework.web.bind.annotation.*;
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;
import test333.Test333.servis.StudentServis;

import java.util.Collection;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentServis studentServis;

    public StudentController(StudentServis studentServis) {
        this.studentServis = studentServis;


    }

    @PostMapping(path = "/add")
    public Student add(@RequestBody Student student) {
        return studentServis.add(student);
    }

    @GetMapping(path = "/get")
    public Student get(@RequestParam Long id) {
        return studentServis.get(id);
    }

    @DeleteMapping(path = "/delete")
    public boolean delete(@RequestParam Long id) {
        return studentServis.delete(id);
    }

    @PutMapping(path = "/udate")
    public Student update(@RequestBody Student student) {
        return studentServis.update(student);
    }

    @GetMapping
    public Collection<Student> getAllStudent(@RequestParam(required = false) Integer age,
                                             @RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        if (age != null) {
            return studentServis.byAge(age);
        }
        if (min != null || max != null) {
            return studentServis.findByAgeBetween(min, max);
        }

        return studentServis.getAll();
    }

    @GetMapping(path = "facultyStudent")
    public Faculty facultyStudent(@RequestParam Long id) {
        return studentServis.get(id).getFaculty();
    }

    @GetMapping(path = "countStudent")
    public int countStudent() {
        return studentServis.studentInSchool();
    }

    @GetMapping(path = "studentAge")
    public double getStudentAge() {
        return studentServis.getStudentsAge();
    }

    @GetMapping(path = "studentLimit")
    public Collection<Student> getStudentLimit() {
        return studentServis.getStudentsLimit();
    }

    @GetMapping(path = "/sortedStudent")
    public Collection<String> getSortedStudent() {
        return studentServis.getAllStudentNameSelection();
    }

    @GetMapping(path = "/midAgeStudents")
    public double getMidAgeStudents() {
        return studentServis.getMidAgeStudents();
    }


}
