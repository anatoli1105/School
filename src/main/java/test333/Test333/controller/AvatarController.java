package test333.Test333.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test333.Test333.servis.AvatarServis;

import java.io.IOException;

@RestController
@RequestMapping(path = "/avatar")
public class AvatarController {
    private final AvatarServis avatarServis;

    public AvatarController(AvatarServis avatarServis) {
        this.avatarServis = avatarServis;
    }
    @PostMapping(path = "save")
    public ResponseEntity<byte[]> saveFile(@RequestParam Long id, @RequestBody MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ofNullable(avatarServis.save(id,multipartFile).getData());

    }
    @GetMapping(path = "return")
    public void returnFromDb(@RequestParam Long id,@RequestParam MultipartFile multipartFile){

    }
    @GetMapping(path = "returnFromFolder")
    public void returnFromFolder(@RequestParam Long id,@RequestParam MultipartFile multipartFile){

    }

}
