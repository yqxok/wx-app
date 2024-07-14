package pri.yqx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.yqx.common.Result;
import pri.yqx.util.QiniuOss;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Resource
    private QiniuOss qiniuOss;
    @PostMapping("/{name}")
    public Result<String> uploadFile(@RequestParam("pic") MultipartFile file, @PathVariable String name) throws IOException {
        log.info("file={}",file);
        //TODO,硬编码
        String url="";
        if(name.equals("user")){
           url=qiniuOss.uploadQiniu(file, "user");
        }else if(name.equals("good"))
           url=qiniuOss.uploadQiniu(file, "good");

        return Result.success(url,"文件上传成功");
    }
}
