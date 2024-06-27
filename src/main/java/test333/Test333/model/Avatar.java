package test333.Test333.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private Student student;

    public byte[] getData() {
        return data;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(fileSize, avatar.fileSize) && Objects.equals(mediaType, avatar.mediaType) && Objects.deepEquals(data, avatar.data) && Objects.equals(student, avatar.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, Arrays.hashCode(data), student);
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "data=" + Arrays.toString(data) +
                ", id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", student=" + student +
                '}';
    }
}
