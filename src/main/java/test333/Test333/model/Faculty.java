package test333.Test333.model;

import jakarta.persistence.*;

//import javax.persistence.Entity;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;


    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private Collection<Student> student;

    public Faculty(Long id, String name, String color, Collection<Student> student) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.student = student;
    }

    public Faculty() {
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Student> getStudent() {
        return student;
    }

    public void setStudent(Collection<Student> student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "color='" + color + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}