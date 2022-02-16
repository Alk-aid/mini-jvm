package classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WildcardEntry extends Entry{
    public CompositeEntry compositeEntry;
    public String path;

    public WildcardEntry(String jreLibPath) {
        String baseDir = jreLibPath.substring(0, jreLibPath.length() - 1);  //去掉最后的一个字符 *
        path = baseDir;
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        compositeEntry = new CompositeEntry();
        compositeEntry.compositeEntries = new ArrayList<Entry>();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                compositeEntry.compositeEntries.add(new JarEntry(baseDir,file.getName()));
            }
        }
    }
    @Override
    byte[] readClass(String className) throws IOException {
        return compositeEntry.readClass(className);
    }

    @Override
    String printClassName() {
        return null;
    }
}
