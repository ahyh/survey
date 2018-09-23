package com.yh.survey.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片工具类
 *
 * @author yanhuan
 */
public final class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
     *
     * @param inputStream
     * @param realPath    /surveyLogos目录的真实路径，后面没有斜杠
     * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
     */
    public static String resizeImages(InputStream inputStream, String realPath) {
        OutputStream out = null;
        try {
            //1.构造原始图片对应的Image对象
            BufferedImage sourceImage = ImageIO.read(inputStream);
            //2.获取原始图片的宽高值
            int sourceWidth = sourceImage.getWidth();
            int sourceHeight = sourceImage.getHeight();
            //3.计算目标图片的宽高值
            int targetWidth = sourceWidth;
            int targetHeight = sourceHeight;
            if (sourceWidth > 100) {
                //按比例压缩目标图片的尺寸
                targetWidth = 100;
                targetHeight = sourceHeight / (sourceWidth / 100);
            }
            //4.创建压缩后的目标图片对应的Image对象
            BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            //5.绘制目标图片
            targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
            //6.构造目标图片文件名
            String targetFileName = System.nanoTime() + ".jpg";
            //7.创建目标图片对应的输出流
            out = new FileOutputStream(realPath + "/" + targetFileName);
            //8.获取JPEG图片编码器
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //9.JPEG编码
            encoder.encode(targetImage);
            //10.返回文件名
            return "surveyLogos/" + targetFileName;
        } catch (Exception e) {
            logger.error("ImageUtil resizeImages error:{}", e);
            return null;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("out close error:{}", e);
                }
            }
        }
    }
}
