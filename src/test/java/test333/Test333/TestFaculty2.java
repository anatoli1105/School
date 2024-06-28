package test333.Test333;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import test333.Test333.controller.FacultyController;
import test333.Test333.controller.StudentController;
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestFaculty2 {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextTest() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void facultyAddTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("name");
        faculty.setColor("color");


        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class))
                .isEqualTo(new Faculty(faculty.getId(), "name", "color", null));

    }

    @Test
    public void facultyGetTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/get?id=1", Faculty.class))
                .isNotNull()
                .isEqualTo(new Faculty(1L, "name", "color", null));


    }

    @Test
    void facultyDeleteTest() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("name");
        faculty.setColor("color");


        Assertions
                .assertThat(
                        this.restTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class));
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/get?id=1", Faculty.class))
                .isEqualTo(new Faculty(1L, "name", "color", null));
        restTemplate.delete("http://localhost:" + port + "/faculty/delete?id=1", Faculty.class);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/get?id=1", Faculty.class))
                .isEqualTo(null);

    }

    @Test
    void getNameTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("name2");
        faculty.setColor("color");
        Assertions
                .assertThat(
                        this.restTemplate.postForObject("http://localhost:" + port + "/faculty/add", faculty, Faculty.class));
        var listName = restTemplate.getForObject("http://localhost:" + port + "/faculty?name=name2", Faculty[].class);

        for (int i = 0; i < listName.length; i++) {
            if (listName[i] == faculty) {
                Assertions
                        .assertThat(listName[i])
                        .isEqualTo(faculty);
            }
            //  Assertions
            //  .assertThat(
            // Arrays.stream(listName).filter(x->x==faculty).map(x->x.toString()))
            //  .isEqualTo(faculty);

            //  }


        }
    }
}


