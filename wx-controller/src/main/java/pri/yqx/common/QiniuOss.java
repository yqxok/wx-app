package pri.yqx.common;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class QiniuOss {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String host;
    public String uploadQiniu(MultipartFile file, String prefix) throws IOException {
        Configuration cfg = new Configuration(Region.huanan());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //生成文件名
        int i = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        UUID uuid = UUID.randomUUID();
        String uri=prefix+"/"+uuid.toString()+file.getOriginalFilename().substring(i);
        try {
            Response response = uploadManager.put(file.getInputStream(), uri, upToken,null,null);
            //解析上传成功的结果

            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return host+putRet.key;

        } catch (IOException ex) {
            throw ex;
        }
    }
}
