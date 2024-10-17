package pri.yqx.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class ImgZipUtil {
    public static File zipImage800x300(File file,File tmp) throws IOException {
        return ImgZipUtil.zipImageCustom(file,800,300,tmp);
    }
    public static File zipImageCustom(File file, int i, int y,File tmpFile) throws IOException {


        try {
            Thumbnails.of(file)
                    .size(i, y) // 调整大小
                    .outputQuality(0.5f) // 设置压缩质量（0.0-1.0）
                    .toFile(tmpFile);
            return tmpFile;
        } catch (IOException e) {
            throw new IOException("图片压缩失败");
        }

    }
}
