package com.battcn.repository;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

/**
 * @author Levin
 */
public class OCR {
    /**
     * @param imageFile 图片路径
     * @return 识别结果
     */
    public static String findOCR(File imageFile) {
        try {
            double start = System.currentTimeMillis();
            if (!imageFile.exists()) {
                return "图片不存在";
            }
            ITesseract instance = new Tesseract();
            instance.setDatapath("D:\\Tesseract-OCR\\tessdata");
            instance.setLanguage("chi_sim");
            String result = instance.doOCR(imageFile);
            double end = System.currentTimeMillis();
            System.out.println("耗时" + (end - start) / 1000 + " s");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "发生未知错误";
        }
    }

    public static void main(String[] args) throws Exception {
        String result = findOCR(new File("D:\\img_data\\code\\tmp\\2118.jpg"));
        System.out.println("====================" + result);
    }
}