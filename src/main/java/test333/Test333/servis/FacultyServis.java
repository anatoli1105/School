package test333.Test333.servis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;
import test333.Test333.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyServis {
    private final FacultyRepository facultyRepository;
    private final static Logger logger = LoggerFactory.getLogger(FacultyServis.class);

    public FacultyServis(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty get(Long id) {
        logger.info("method of searching for a faculty by id");

        return facultyRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        logger.info("faculty removal method");
        return facultyRepository.findById(id).
                map(entity -> {
                    facultyRepository.delete(entity);
                    return true;
                }).orElse(false);
    }

    public Faculty update(Faculty faculty) {
        logger.info("faculty adding method");
        return facultyRepository.save(faculty);

    }

    public Collection<Faculty> getAll() {
        logger.info("method for getting a list of all faculty");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findColor(String color) {
        logger.info("the method of squeaking acultets by color was called");
        return facultyRepository.findByColorIgnoreCase(color);

    }

    public Collection<Faculty> findByNameIgnoreCase(String name) {
        logger.info("method of searching faculties by name");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public String longNameFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(o -> o.getName())
                .max(Comparator.comparingInt(String::length))
                .toString();
    }

}
