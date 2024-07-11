package test333.Test333.servis;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import test333.Test333.model.Avatar;
import test333.Test333.model.Student;
//import test333.Test333.repository.AvatarRepository;
//import test333.Test333.repository.AvatarRepository;
import test333.Test333.repository.AvatarRepository;
import test333.Test333.repository.StudentRepository;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServis {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;


    private final static Logger logger = LoggerFactory.getLogger(AvatarServis.class);

    private final AvatarRepository avatarRepository;

    private final StudentRepository studentRepository;

    public AvatarServis(AvatarRepository avatarRepository, StudentRepository studentRepository) {

        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }


    public void save(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("the image saving method was called");
        Student student = studentRepository.getById(studentId);

        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Collection<Avatar> getPage(int page, int size) {
        return avatarRepository.findAll(PageRequest.of(page, size)).toList();
    }


}
