package test333.Test333;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test333.Test333.controller.AvatarController;
import test333.Test333.controller.FacultyController;
import test333.Test333.controller.StudentController;
import test333.Test333.model.Student;
import test333.Test333.repository.AvatarRepository;
import test333.Test333.repository.FacultyRepository;
import test333.Test333.repository.StudentRepository;
import test333.Test333.servis.AvatarServis;
import test333.Test333.servis.FacultyServis;
import test333.Test333.servis.StudentServis;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest

public class Test2 {
    @Autowired
    private MockMvc mvc;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    FacultyRepository facultyRepository;
    @MockBean
    AvatarRepository avatarRepository;
    @SpyBean
    AvatarController avatarController;
    @SpyBean
    FacultyController facultyController;
    @SpyBean
    AvatarServis avatarServis;
    @SpyBean
    StudentServis studentServis;
    @SpyBean
    FacultyServis facultyServis;
    @InjectMocks
    StudentController studentController;

    @Test
    public void studentAddTest()throws Exception{

        final String name="ivan";
        final int age=18;
        final  Long id=1L;
        JSONObject objectStudent=new JSONObject();
        objectStudent.put("id",id);
        objectStudent.put("name",name);
        objectStudent.put("age",age);
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mvc.perform(MockMvcRequestBuilders
                .post("/student/add")
                .content(objectStudent.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));





    }
    @Test
    public void studentGetAndDeleteTest()throws Exception{

        final String name="ivan";
        final int age=18;
        final  Long id=1L;
        JSONObject objectStudent=new JSONObject();
        objectStudent.put("id",id);
        objectStudent.put("name",name);
        objectStudent.put("age",age);
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mvc.perform(MockMvcRequestBuilders
                        .get("/student/get?id=1")
                        .content(objectStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
        mvc.perform(MockMvcRequestBuilders
                        .delete("/student/delete?id=1")
                        .content(objectStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));






    }
    @Test
    void getAllStudentTest()throws Exception{
        final String name="ivan";
        final int age=18;
        final  Long id=1L;
        JSONObject objectStudent=new JSONObject();
        objectStudent.put("id",id);
        objectStudent.put("name",name);
        objectStudent.put("age",age);
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        final String name2="ivan2";
        final int age2=13;
        final  Long id2=2L;
        JSONObject objectStudent2=new JSONObject();
        objectStudent.put("id",id2);
        objectStudent.put("name",name2);
        objectStudent.put("age",age2);
        Student student2=new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);


        when(studentRepository.findByAge(anyInt())).thenReturn(List.of(student,student2));

        mvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .content(objectStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("13").value(age2));
               // .andExpect(jsonPath("$.name").value(name))
               // .andExpect(jsonPath("$.age").value(age));


    }


}
