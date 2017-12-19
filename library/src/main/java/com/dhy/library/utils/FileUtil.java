package com.dhy.library.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhy on 2017/12/19.
 */

public class FileUtil {
    private static final int BUFFER = 1024;
    private static final String TAG = FileUtil.class.getSimpleName();

    public FileUtil() {
    }

    public static FileUtil getInstance() {
        return FileHolder.newInstance;
    }

    private static class FileHolder {
        private final static FileUtil newInstance = new FileUtil();
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String readTextFile(File file) throws IOException {
        String text = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            text = readTextInputStream(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }

    /**
     * 从流中读取文件
     *
     * @param is
     * @return
     * @throws IOException
     */
    public String readTextInputStream(InputStream is) throws IOException {
        StringBuffer strBuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                strBuffer.append(line).append("\r\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return strBuffer.toString();
    }

    /**
     * 将文本内容写入文件
     *
     * @param file
     * @param str
     * @throws IOException
     */
    public void writeTextFile(File file, String str) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            out.write(str.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] buffer = new byte[BUFFER];
            int length;
            while ((length = inBuff.read(buffer)) != -1) {
                outBuff.write(buffer, 0, length);
            }
            outBuff.flush();
        } finally {
            if (inBuff != null) {
                inBuff.close();
            }
            if (outBuff != null) {
                outBuff.close();
            }
        }
    }

    /**
     * 获取缓存文件
     *
     * @param imageUri
     * @return
     */
    public File getCacheFile(String imageUri) {
        File cacheFile = null;
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();
                String fileName = getFileName(imageUri);
                File dir = new File(sdCardDir.getCanonicalPath());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                cacheFile = new File(dir, fileName);
                Log.w(TAG, "exists:" + cacheFile.exists() + ",dir:" + dir
                        + ",file:" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cacheFile;
    }

    /**
     * 获取文件大小
     *
     * @param size 字节
     * @return
     */
    public String getFileSize(long size) {
        if (size <= 0)
            return "0";
        DecimalFormat df = new DecimalFormat("##.##");
        float temp = (float) size / 1024;
        if (temp > 1048576) {
            return df.format(temp / 1024) + "G";
        } else if (temp >= 1024) {
            return df.format(temp / 1024) + "M";
        } else {
            return df.format(temp) + "K";
        }
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }


    /**
     * 获取目录文件个数
     *
     * @param dir
     * @return
     */
    public long getFileList(File dir) {
        long count = 0;
        File[] files = dir.listFiles();
        count = files.length;
        for (File file : files) {
            if (file.isFile()) {
                count++;
            } else if (file.isDirectory()) {
                count = count + getFileList(file);// 递归
            }
        }
        return count;
    }


    /**
     * 获取目录文件文件列表
     *
     * @param Path
     * @param Extension
     * @param IsIterative
     * @return
     */
    private List<String> resultList = new ArrayList<>(); // 结果 List
    public List<String> GetFiles(String Path, String Extension,
                                 boolean IsIterative) // 搜索目录，扩展名(判断的文件类型的后缀名)，是否进入子文件夹
    {
        File[] files = new File(Path).listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isFile()) {
                    if (f.getPath()
                            .substring(f.getPath().length() - Extension.length())
                            .equals(Extension)) // 判断扩展名
                        resultList.add(f.getPath());
                    if (!IsIterative)
                        break;  //如果不进入子集目录则跳出
                } else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) // 忽略点文件（隐藏文件/文件夹）
                    GetFiles(f.getPath(), Extension, IsIterative);  //这里就开始递归了
            }
            return resultList;
        }

        return null;
    }

    /**
     * 获取文件名
     *
     * @param path
     * @return
     */
    public String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }
}
