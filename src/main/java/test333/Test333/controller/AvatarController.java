package test333.Test333.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test333.Test333.model.Avatar;
import test333.Test333.servis.AvatarServis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/avatar")
public class AvatarController {
   private final AvatarServis avatarServis;


    public AvatarController(AvatarServis avatarServis) {
        this.avatarServis = avatarServis;
    }
    
    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveFile(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {

    avatarServis.save(studentId,avatar);
    return ResponseEntity.ok().build();
    }
    @GetMapping(path = "/getAvatar")
    public Collection<Avatar> getAvtar(@RequestParam int page,@RequestParam int size){
        return avatarServis.getPage(page,size);
    }




}
