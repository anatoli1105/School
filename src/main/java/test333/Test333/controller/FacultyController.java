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
    public Collection<Faculty> getAllStudent(@RequestParam Faculty faculty) {
        return facultyServis.getAll(faculty);
    }

    @GetMapping(path = "/color")
    public Collection<Faculty> findColor(@RequestParam String color) {
        return facultyServis.findColor(color);
    }

}
