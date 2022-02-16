package test;

import classpath.ClassPath;
import utils.Cmd;

import java.util.Scanner;

/**
 * @Author: Alk-aid
 * @Date: 1/31/2022 15:45
 * @Description:
 */
public class Test02ClassPath {
    public static void main(String[] args) {
        System.out.println("Usage: java [-options] class [args...]");
        while (true) {
            Scanner in = new Scanner(System.in);
            String cmdLine = in.nextLine();
            Cmd cmd = new Cmd(cmdLine);
            if (!cmd.isRightFmt()) {
                System.out.println("Unrecognized command!");
                cmd.printUsage();
            } else {
                if (cmd.isVersionFlag()) {
                    System.out.println("java version \"1.8.0_20\"\n"
                            + "Java(TM) SE Runtime Environment (build 1.8.0_20-b26)\n"
                            + "Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)");
                } else if (cmd.isHelpFlag()) {
                    cmd.printUsage();
                } else {
                    System.out.println("cmd pared successful!");
                    ClassPath classPath = new ClassPath(cmd.getCpOption());
                    byte[] classData = classPath.readClass(cmd.getClassName());
                    int len = classData.length;
                    System.out.println("the length of file is: " + len);
                    for (int i = 0; i < len; i++) {
                        if (i % 20 == 0) {
                            System.out.println();
                        }
                        System.out.printf("%04X ", classData[i], 16);

                    }
                }
            }
            System.out.println();
        }
    }
}
