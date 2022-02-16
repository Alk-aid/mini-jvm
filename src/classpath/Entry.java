package classpath;


import java.io.File;
import java.io.IOException;
// 类路径的抽象
public abstract class Entry {
    //路径分隔符,在window下,使用 ; 分割开的  在Unix/Linux下使用: 分割开的
    public static final String pathListSeparator = System.getProperty("os.name").contains("Windows") ? ";" : ":";

    abstract byte[] readClass(String className) throws IOException;
    abstract String printClassName();
    static Entry createEntry(String path) {
        if (path != null) {
            if (path.contains(pathListSeparator)) {
                return new CompositeEntry(path, pathListSeparator);
            } else if (path.contains("*")) {
                return new WildcardEntry(path);
            } else if (path.contains(".jar") || path.contains(".JAR")) {
                return new JarEntry(path);
            }
            return new DirEntry(path);
        } else {
            //如果命令行中没有显式的指定-cp选项,那么默认要找的class就在当前路径下
            File file = new File("");
            try {
                path = file.getCanonicalPath();
                return new DirEntry(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("illegal classpath format,or you should point out the classpath explicitly");
    }
}
