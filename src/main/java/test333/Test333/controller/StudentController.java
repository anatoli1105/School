package test333.Test333.controller;

import org.springframework.web.bind.annotation.*;
import test333.Test333.model.Student;
import test333.Test333.servis.StudentServis;

import java.util.Collection;

@RestController
@RequestMapping(path ="/student")
public class StudentController {

    private final StudentServis studentServis;

    public StudentController(StudentServis studentServis) {
        this.studentServis = studentServis;


    }
    @PostMapping(path = "/add")
    public Student add(@RequestBody Student student){
        return studentServis.add(student);
    }
    @GetMapping(path = "/get")
    public Student get(@PathVariable Long id){
        return studentServis.get(id);
    }
    @DeleteMapping(path = "/delete")
    public void delete(@PathVariable Long id){
        studentServis.delete(id);
    }
    @PutMapping(path = "/udate")
    public Student update(@RequestBody Student student){
        return studentServis.update(student);
    }

    @GetMapping
    public Collection<Student> getAllStudent(@RequestParam Student student){
        return studentServis.getAll(student);
    }

    @GetMapping(path = "/age")
    public Collection<Student> ageFilter(@RequestParam int age) {
        return studentServis.byAge(age);

     }

}
