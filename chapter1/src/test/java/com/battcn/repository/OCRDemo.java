package com.battcn.repository;

import net.sourceforge.tess4j.*;

import java.io.File;

/**
 * 本教程由 http://bbs.datahref.com/ 提供
 * Tess4j验证码识别示例
 * 工程中tessdata文件夹包含了识别英文所需的数据
 * 需要识别其他语言课到https://github.com/tesseract-ocr/tessdata下载相关数据
 * 放到tessdata文件夹中
 * @author hu
 *
 */
public class OCRDemo {

    public static void main(String[] args) throws TesseractException {
        ITesseract instance = new Tesseract();
        File imgDir = new File("D:/img_data");
        instance.setDatapath("D:\\Tesseract-OCR\\tessdata");
        //对img_data文件夹中的每个验证码进行识别
        //文件名即正确的结果
        for (File imgFile : imgDir.listFiles()) {
            //该例子输入的是文件，也可输入BufferedImage
            String ocrResult = instance.doOCR(imgFile);
            //输出图片文件名，即正确识别结果
            System.out.println("ImgFile: "+imgFile.getAbsolutePath());
            //输出识别结果
            System.out.println("OCR Result: " + ocrResult);
        }
    }
}