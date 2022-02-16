package classpath;

import java.io.*;

// 目录路径
public class DirEntry extends Entry {

    private String absDir;

    public DirEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absDir = dir.getAbsolutePath();
        }
    }

    @Override
    byte[] readClass(String className) throws IOException {
        File file = new File(absDir, className);
        if (!file.exists()) {
            return null;
        }
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
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
