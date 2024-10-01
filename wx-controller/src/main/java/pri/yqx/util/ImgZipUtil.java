package pri.yqx.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ImgZipUtil {
    public static File zipImage800x300(File file) throws IOException {
        return ImgZipUtil.zipImageCustom(file,800,300);
    }
    public static File zipImageCustom(File file, int i, int y) throws IOException {

        File file1=new File("D:\\img\\tmp\\tmp1.jpg");
        try {
            Thumbnails.of(file)
                    .size(i, y) // 调整大小
                    .outputQuality(0.5f) // 设置压缩质量（0.0-1.0）
                    .toFile(file1);
            return file1;
        } catch (IOException e) {
            throw new IOException("图片压缩失败");
        }

    }
}
