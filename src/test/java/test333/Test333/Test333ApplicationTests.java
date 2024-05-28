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

import java.util.List;

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
        student.setId(6L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class));

        restTemplate.delete("http://localhost:" + port + "/student/delete/id=4", Student.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/get" + 6, Student.class))
                .isEqualTo(new Student(null, 0, null, null));


    }
    @Test
    void getAllStudentTest()throws Exception{
        Student student = new Student();
        student.setName("ivan");
        student.setAge(35);
        student.setId(56L);


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/add", student, Student.class));

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" , List.class))
                .isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?age=35" , List.class))
                .isEqualTo(student);



    }


}
