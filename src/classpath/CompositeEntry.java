package classpath;

import java.io.IOException;
import java.util.ArrayList;

public class CompositeEntry extends Entry{
    ArrayList<Entry> compositeEntries;
    private String pathList;

    public CompositeEntry() {
    }

    public CompositeEntry(String pathList, String pathListSeparator) {
        this.pathList = pathList;
        String[] paths = pathList.split(pathListSeparator);
        compositeEntries = new ArrayList<Entry>(paths.length);
        for (String path : paths) {
            compositeEntries.add(new DirEntry(path));
        }
    }

    @Override
    byte[] readClass(String className) throws IOException {
        byte[] data;
        for (Entry compositeEntry : compositeEntries) {
            try {
                data = compositeEntry.readClass(className);
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    String printClassName() {
        return null;
    }
}
