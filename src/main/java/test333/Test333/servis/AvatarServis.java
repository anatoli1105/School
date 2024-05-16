package test333.Test333.servis;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import test333.Test333.model.Avatar;
import test333.Test333.repository.AvatarRepository;
import test333.Test333.repository.StudentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
public class AvatarServis {
    @Value("${path.to.avatars.folder}")
   private String kavatarsDir;
    private final StudentServis studentServis;
    private final AvatarRepository avatarRepository;
    private final Path avatarsDir;
    private  final StudentRepository studentRepository;


    public AvatarServis(StudentServis studentServis, AvatarRepository avatarRepository, @Value("${avatars.dir}")Path avatarsDir, StudentRepository studentRepository) {
        this.studentServis = studentServis;
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
        this.studentRepository = studentRepository;
    }

    public Avatar save(Long id, MultipartFile multipartFile) throws IOException {
        Files.createDirectories(Path.of("avatarsDir"));
        var index= multipartFile.getName().lastIndexOf('.');
        var extension=multipartFile.getName().substring(index);

        Path filePath=avatarsDir.resolve(id + "." + extension);
        try (var in=multipartFile.getInputStream()) {


            Files.copy(in,filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        Avatar avatar=avatarRepository.findAllByStudentId(id).orElse(new Avatar());
        avatar.setFileSize(multipartFile.getSize());
        avatar.setMediaType(multipartFile.getContentType());
        avatar.setData(multipartFile.getBytes());
        avatar.setStudent(studentRepository.getReferenceById(id));
        avatar.setFilePath(filePath.toString());
        return avatarRepository.save(avatar);
    }
    private Avatar getById(Long id){
        return avatarRepository.findById(id).orElse(null);
    }
}
