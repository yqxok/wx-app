package pri.yqx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pri.yqx.common.Result;
import pri.yqx.common.QiniuOss;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.json.PicUrl;
import pri.yqx.util.ImgZipUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Resource
    private QiniuOss qiniuOss;
    @Value("${oss.tmp-file}")
    private String tmpFile;
    @Value("${oss.tmp-file1}")
    private String tmpFile1;
    @PostMapping("/{name}")
    public Result<PicUrl> uploadFile(@RequestParam("pic") MultipartFile pic, @PathVariable String name){
        try {
            File file=new File(tmpFile);
            pic.transferTo(file);
            //TODO,多线程不阻塞
            File file1 = zipGoodShowImage(file);//压缩图片

            //TODO,硬编码
            String url="";
            if(name.equals("user")){
               url=qiniuOss.uploadQiniu(file1, "user");
            }else if(name.equals("good"))
               url=qiniuOss.uploadQiniu(file1, "good");
//        thread.join();
//        url=qiniuOss.uploadLocal(file1,name);//保存图片
            BufferedImage image = ImageIO.read(file1);
            PicUrl picUrl = new PicUrl().setUrl(url).setHeight(image.getHeight()).setWidth(image.getWidth());
            return Result.success(picUrl,"upload success");
        } catch (Exception e) {
            log.error("uploadFile出错",e);
            throw new BusinessException("系统出错,图片上传失败");
        }
    }



    private File zipGoodShowImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        float width =(float) image.getWidth();
        float height =(float) image.getHeight();
        if(width>500){
            float tmp = width/500;
            width=500;
            height/=tmp;
        }
        return ImgZipUtil.zipImageCustom(file, (int) width, (int) height,new File(tmpFile1));
    }
    private File zipProfileImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        float width =(float) image.getWidth();
        float height =(float) image.getHeight();
        if(width>100){
            float tmp = width/100;
            width=100;
            height/=tmp;
        }
        return ImgZipUtil.zipImageCustom(file, (int) width, (int) height,new File(tmpFile1));
    }
}
