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
    public boolean delete(Long id){
        return studentRepository.findById(id).
                map(entity->{studentRepository.delete(entity);
                    return true;}).orElse(false);



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
     public int studentInSchool(){
        return studentRepository.countStudent();
     }
     public double getStudentsAge(){
        return studentRepository.avgAge();
     }
     public Collection<Student> getStudentsLimit(){
        return studentRepository.getLastFive();
     }


}


