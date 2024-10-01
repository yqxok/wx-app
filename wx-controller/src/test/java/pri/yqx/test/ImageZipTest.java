package pri.yqx.test;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ImageZipTest {
    @Test
    public void zipImage(){
        File inputFile = new File("C:\\Users\\19328\\Pictures\\红色眼睛 粉色头发 好看厚涂画美女4k动漫壁纸_彼岸图网.jpg");
        File outputFile = new File("C:\\Users\\19328\\Pictures\\压缩图片.jpg");

        try {
            Thumbnails.of(inputFile)

                    .size(800, 300) // 调整大小
                    .outputQuality(0.5f) // 设置压缩质量（0.0-1.0）
                    .toFile(outputFile);
            System.out.println("图像压缩完成！");
        } catch (IOException e) {
            System.out.println("压缩图像时出错: " + e.getMessage());
        }
    }
}
