package pri.yqx.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownLoadController {
    @GetMapping("/{prefix}/{imgId}")
    public ResponseEntity<Resource> getImg(@PathVariable String prefix, @PathVariable String imgId){
        String filePath="D:\\img\\"+prefix+"\\"+imgId;
        String suffix = imgId.substring(imgId.lastIndexOf("."));
        HttpHeaders headers = new HttpHeaders();
        if(suffix.contains("jpg")||suffix.contains("jpeg"))
            headers.setContentType(MediaType.IMAGE_JPEG);
        else if(suffix.contains("png"))
            headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(new FileSystemResource(filePath),headers, HttpStatus.OK);
    }
}
