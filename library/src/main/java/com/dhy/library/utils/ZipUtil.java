package com.dhy.library.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Created by JackDeng on 2017/7/18.
 */

public class ZipUtil {
    /**
     * 使用 org.apache.tools.zip.ZipFile 解压文件，它与 java 类库中的 java.util.zip.ZipFile
     * 使用方式是一样的，只不过多了设置编码方式的 接口。
     *
     * 注，apache 没有提供 ZipInputStream 类，所以只能使用它提供的ZipFile 来读取压缩文件。
     *
     * @param zipPath
     *            压缩包路径
     * @param decompressDir
     *            解压路径
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ZipException
     */
    public static void readByApacheZipFile(String zipPath, String decompressDir)
            throws IOException, FileNotFoundException, ZipException {
        BufferedInputStream bi;
//        ZipFile zf = new ZipFile(archive, "GBK");// 支持中文
        File file = new File(zipPath);
        if (!file.exists()) {
            return;
        }
        ZipFile zf = null;// 支持中文
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            zf = new ZipFile(file,ZipFile.OPEN_READ, Charset.forName("utf-8") );
        }
        Enumeration mEnumeration = zf.entries();
        while (mEnumeration.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) mEnumeration.nextElement();
            String entryName = ze2.getName();

            decompressDir = decompressDir.replace(".","-");

            String path = decompressDir + "/" + entryName;
            if (ze2.isDirectory()) {
                File decompressDirFile = new File(path);
                if (!decompressDirFile.exists()) {
                    decompressDirFile.mkdirs();
                }
            } else {
                String fileDir = path.substring(0, path.lastIndexOf("/"));
                File fileDirFile = new File(fileDir);
                if (!fileDirFile.exists()) {
                    fileDirFile.mkdirs();
                }
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(decompressDir + "/" + entryName));
                bi = new BufferedInputStream(zf.getInputStream(ze2));
                byte[] readContent = new byte[1024];
                int readCount = bi.read(readContent);
                while (readCount != -1) {
                    bos.write(readContent, 0, readCount);
                    readCount = bi.read(readContent);
                }
                bos.close();
            }
        }
        zf.close();
    }
}
