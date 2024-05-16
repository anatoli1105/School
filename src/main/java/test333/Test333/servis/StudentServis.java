package test333.Test333.servis;

import org.springframework.stereotype.Service;
import test333.Test333.model.Student;
import test333.Test333.repository.StudentRepository;

import java.util.Collection;
@Service
public class StudentServis {

    private final StudentRepository studentRepository;


    public StudentServis(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student){
        return studentRepository.save(student);
    }
    public Student get(Long id){
        return studentRepository.findById(id).orElse(null);
    }
    public void delete(Long id){
        studentRepository.deleteById(id);
    }
    public Student update(Student student) {
        return studentRepository.save(student);

    }
    public Collection<Student> getAll(){
        return studentRepository.findAll();
    }
    public Collection<Student> byAge(int age) {
     return studentRepository.findByAge(age);
     }
     public Collection<Student> findByAgeBetween(int min,int max){
        return studentRepository.findByAgeBetween(min,max);
     }


}


