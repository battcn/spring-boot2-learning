package com.battcn.repository;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Levin
 * @since 2018/4/3 0003
 */
public class OCRHelper {


    private final String LANG_OPTION = "-l";
    private final String EOL = System.getProperty("line.separator");

    /**
     * Tesseract-OCR的安装路径
     */
    private static final String tessPath = "D:\\Tesseract-OCR";

    /**
     * @param imageFile 传入的图像文件
     * @return 识别后的字符串
     */
    public String recognizeText(File imageFile) throws Exception {
        /**
         * 设置输出文件的保存的文件目录
         */
        File outputFile = new File(imageFile.getParentFile(), "output");

        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<String>();

        cmd.add(tessPath + "\\tesseract");

        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        cmd.add("chi_sim");
        //cmd.add("eng");

        ProcessBuilder pb = new ProcessBuilder();

        pb.directory(imageFile.getParentFile());
        cmd.set(1, imageFile.getName());
        pb.command(cmd);
        pb.redirectErrorStream(true);
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        Process process = pb.start();
        System.out.println(cmd.toString());
        int w = process.waitFor();
        if (w == 0)// 0代表正常退出
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
                    "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();

            long endTime = System.currentTimeMillis();
            System.out.println("strB" + strB.toString());
            System.out.println("结束时间：" + endTime);
            System.out.println("耗时：" + (endTime - startTime) + "毫秒");
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString().replaceAll("\\s*", "");
    }

    public static void main(String[] args) {
        try {
            //图片文件：此图片是需要被识别的图片
            File file = new File("D:\\img_data\\2118.jpg");
            String recognizeText = new OCRHelper().recognizeText(file);
            System.out.print(recognizeText + "\t");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
