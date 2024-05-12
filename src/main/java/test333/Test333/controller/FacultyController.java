package test333.Test333.controller;

import org.springframework.web.bind.annotation.*;
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;
import test333.Test333.servis.FacultyServis;

import java.util.Collection;

@RestController
@RequestMapping(path = "/faculty")
public class FacultyController {
    private final FacultyServis facultyServis;

    public FacultyController(FacultyServis facultyServis) {
        this.facultyServis = facultyServis;
    }

    @PostMapping(path = "/add")
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyServis.add(faculty);
    }

    @GetMapping(path = "/get")
    public Faculty get(@PathVariable Long id) {
        return facultyServis.get(id);
    }

    @DeleteMapping(path = "/delete")
    public void delete(@PathVariable Long id) {
        facultyServis.delete(id);
    }

    @PutMapping(path = "/udate")
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyServis.update(faculty);
    }

    @GetMapping
    public Collection<Faculty> getAllStudent(@RequestParam(required = false)String color,
                                             @RequestParam(required = false)String name) {
        if(color != null &&!color.isBlank()){
            return facultyServis.findColor(color);
        }
        if(name!=null&&!name.isBlank()){
            return facultyServis.findByNameIgnoreCase(name);
        }
        return facultyServis.getAll();
    }
    @GetMapping(path = "liststudent")
    public Collection<Student> studentFaculty(@RequestParam Long id){
        return facultyServis.get(id).getStudent();
    }



}
