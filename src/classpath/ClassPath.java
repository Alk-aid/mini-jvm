package classpath;

import java.io.File;
import java.io.IOException;

public class ClassPath {
    // jre路径
    private static String jreDir = System.getenv("JAVA_HOME") + File.separator + "jre";
    //分别存放三种类路径
    private static Entry bootClasspath;
    private static Entry extClasspath;
    private Entry userClasspath;
    static {
        bootClasspath = parseBootClasspath();
        extClasspath = parseExtClasspath();
    }

    public ClassPath(String cpOption) {
        userClasspath = parseUserClasspath(cpOption);
    }
    private static Entry parseBootClasspath() {
        //可能出现的情况是: jre/lib/*
        String jreLibPath = jreDir + File.separator + "lib" + File.separator + "*";
        return new WildcardEntry(jreLibPath);
    }
    private static Entry parseExtClasspath() {
        //可能出现的情况是: jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        return new WildcardEntry(jreExtPath);
    }

    private Entry parseUserClasspath(String cpOption) {
        return Entry.createEntry(cpOption);
    }

    public byte[] readClass(String className) {
        //注意，用命令行加载java文件时，只写文件名，所有这里统一为文件名后补上“.class”的后缀；
        if (className.endsWith(".class")) {
            throw new RuntimeException("can't find or can't load the class: " + className);
        }
        className = className.replace(".", "/");
        className = className + ".class";
        byte[] data;
        try {
            data = bootClasspath.readClass(className);
            if (data != null) {
                return data;
            }

            data = extClasspath.readClass(className);
            if (data != null) {
                return data;
            }

            data = userClasspath.readClass(className);
            if (data != null) {
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't find class!");
    }

}
