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
    //private final AvatarServis avatarService;

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
   /* @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> getFile(@PathVariable Long studentId) {
       // Avatar avatar=new Avatar();
       var avatar=avatarServis.findAvatar(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
   /* @GetMapping(path = "return")
    public void returnFromDisk(@PathVariable Long id, HttpServletResponse httpServletResponse)throws IOException{
        var avatar=avatarServis.getByDb(id);
        if(avatar!=null){
            httpServletResponse.setContentLength(Math.toIntExact(avatar.getFileSize()));
            httpServletResponse.setContentType(avatar.getFilePath());
            try(var fis =new FileInputStream(avatar.getFilePath())){
                fis.transferTo(httpServletResponse.getOutputStream());
            }
        }

    }
    @GetMapping(path = "returnFromDb")
    public ResponseEntity<byte[]> returnFromDb(@PathVariable Long id){

        var avatar=avatarServis.getByDb(id);
        if (avatar==null){
            return ResponseEntity.ofNullable(null);
        }
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentLength(avatar.getFileSize());
        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        return ResponseEntity.status(200).headers(httpHeaders).body(avatar.getData());




    }*/

}
