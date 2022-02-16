package classpath;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarEntry extends Entry {
    // D:\software\JDK1.8\jre\lib\test.jar
    String absDir;

    public JarEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absDir = dir.getAbsolutePath();
        }
    }
    public JarEntry(String basePath, String jarName){
        File dir = new File(basePath, jarName);
        if (dir.exists()){
            absDir = dir.getAbsolutePath();
        }
    }
    @Override
    byte[] readClass(String className) throws IOException {
        File file = new File(absDir);
        ZipFile zf = new ZipFile(file);
        ZipEntry ze = zf.getEntry(className);
        if (ze == null) {
            return null;
        }
        BufferedInputStream in = new BufferedInputStream(zf.getInputStream(ze));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        int size = 0;
        byte[] temp = new byte[1024];
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        if (in != null) {
            in.close();
        }

        if (out != null) {
            out.close();
        }
        return out.toByteArray();
    }

    @Override
    String printClassName() {
        return absDir;
    }
}
