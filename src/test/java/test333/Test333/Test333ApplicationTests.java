package test333.Test333;

//import org.junit.jupiter.api.Test;

import jakarta.validation.OverridesAttribute;
import nonapi.io.github.classgraph.utils.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import test333.Test333.controller.StudentController;
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Test333ApplicationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextTest() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void studentAddTest() throws Exception {
        Student student = new Student();
        student.setName("ivan");
        student.setAge(34);
        student.setId(34L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class))
                .isEqualTo(new Student(student.getId(), 34, "ivan", null));


    }

    @Test
    void studentAddTest2() throws Exception {
        Student student = new Student();
        student.setName("ivan");
        student.setAge(34);
        student.setId(34L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class))
                .isEqualTo(new Student(student.getId(), 34, "ivan", null));
    }


    @Test
    public void studentGetTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/get" + 2, Student.class))
                .isNotNull();


    }

    @Test
    public void studentDeleteTest() throws Exception {
        Student student = new Student();
        student.setName("ivan");
        student.setAge(34);
        student.setId(69L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class));

        restTemplate.delete("http://localhost:" + port + "/student/delete?id=69", Student.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/get?id= 69", Student.class))
                .isEqualTo(null);


    }

    @Test
    void getAllStudentAndAgeStudentsTest() throws Exception {
        Student student = new Student();
        student.setName("ivan");
        student.setAge(35);
        student.setId(56L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class));

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", List.class))
                .isNotNull();


        var listStudents = (this.restTemplate.getForObject("http://localhost:" + port + "/student?age=35", Student[].class));

        for (int i = 0; i < listStudents.length; i++) {
            if(listStudents[i].getAge()==35) {
                Assertions
                        .assertThat(listStudents[i].getAge())
                        .isEqualTo(35);
            }
        }


    }

    @Test
    void testAgeStudents() throws Exception {

        Student student = new Student();
        student.setName("ivanov");
        student.setAge(39);
        student.setId(5L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class));
        var age = (this.restTemplate.getForObject("http://localhost:" + port + "/student?min=39&max=40 ", Student[].class));
        for (int i = 0; i < age.length; i++) {
            Assertions
                    .assertThat(age[i].getAge())
                    .isEqualTo(39);

        }
    }


}
