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
import test333.Test333.model.Faculty;
import test333.Test333.model.Student;
import test333.Test333.repository.AvatarRepository;
import test333.Test333.repository.FacultyRepository;
import test333.Test333.repository.StudentRepository;
import test333.Test333.servis.AvatarServis;
import test333.Test333.servis.FacultyServis;
import test333.Test333.servis.StudentServis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class TestFaculty {
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
    StudentController studentController;
    @SpyBean
    AvatarServis avatarServis;
    @SpyBean
    StudentServis studentServis;
    @SpyBean
    FacultyServis facultyServis;
    @InjectMocks
    FacultyController facultyController;

    @Test
    public void facultyAddTest() throws Exception {

        final String name = "faculty";
        final String color = "color";
        final Long id = 1L;

        JSONObject objectFaulty = new JSONObject();
        objectFaulty.put("id", id);
        objectFaulty.put("name", name);
        objectFaulty.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mvc.perform(MockMvcRequestBuilders
                        .post("/faculty/add")
                        .content(objectFaulty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
    @Test
    public void faultyGetAndDeleteTest() throws Exception {


        JSONObject objectFaulty = new JSONObject();
        objectFaulty.put("id", 1L);
        objectFaulty.put("name", "name");
        objectFaulty.put("color", "color");
        Faculty faculty=new Faculty(1L,"name","color",null);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get?id=1")
                        .content(objectFaulty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.color").value("color"));
        mvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/delete?id=1")
                        .content(objectFaulty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));


    }
    @Test
    void testColorFaulty()throws Exception{
        final String name = "faculty";
        final String color = "color";
        final Long id = 1L;

        JSONObject objectFaulty = new JSONObject();
        objectFaulty.put("id", id);
        objectFaulty.put("name", name);
        objectFaulty.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        Collection<Faculty>collection=new ArrayList<>(List.of(faculty));

        when(facultyRepository.findByColorIgnoreCase(any(String.class))).thenReturn(collection);

        mvc.perform(MockMvcRequestBuilders
                .get("/faculty?color=color")
                .content(objectFaulty.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].color").value(color));
    }
    @Test
    void testNameFaulty()throws Exception{
        final String name = "faculty";
        final String color = "color";
        final Long id = 1L;

        JSONObject objectFaulty = new JSONObject();
        objectFaulty.put("id", id);
        objectFaulty.put("name", name);
        objectFaulty.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        Collection<Faculty>collection=new ArrayList<>(List.of(faculty));

        when(facultyRepository.findByNameIgnoreCase(any(String.class))).thenReturn(collection);

        mvc.perform(MockMvcRequestBuilders
                        .get("/faculty?name=name")
                        .content(objectFaulty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].color").value(color));
    }

    }
