package test333.Test333.servis;

import org.springframework.stereotype.Service;
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;
import test333.Test333.repository.FacultyRepository;

import java.util.Collection;
@Service
public class FacultyServis {
    private final FacultyRepository facultyRepository;

    public FacultyServis(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty add(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public Faculty get(Long id){
        return facultyRepository.findById(id).get();
    }
    public void delete(Long id){
        facultyRepository.deleteById(id);
    }
    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
    public Collection<Faculty> getAll(Faculty faculty){
        return facultyRepository.findAll();
    }
    public Collection<Faculty> findColor(String color) {
        return facultyRepository.findByColor(color);
    }
}