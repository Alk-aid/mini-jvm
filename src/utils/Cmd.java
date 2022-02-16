package utils;

public class Cmd {
    //是否是正确的格式
    private boolean isRightFmt = true;
    // 是否是Help指令
    private boolean helpFlag;
    // 是否是查看版本指令
    private boolean versionFlag;
    // classPath，也就是类路径
    private String cpOption;
    // 类名
    private String className;
    // 传递的参数
    private String[] args;

    public Cmd(String cmdLine) {
        parseCmd(cmdLine);
    }
    public void parseCmd(String cmdLine) {
        //NOTE:解析命令行参数,以单个或者多个空格分开,这种方式目前不支持,因为如果输入的 路径名 中间有空格会导致下面解析失败
        String[] args = cmdLine.trim().split("\\s+");
        parseCmd(args);
    }
    // 解析命令
    public void parseCmd(String[] args) {
        // 参数必须大于等于2，否则不合法。最简短的java命令：java -version
        if (args.length < 2) {
            isRightFmt = false;
            return;
        }
        //首先判断开头是不是 java ,如果连这个都不是,直接退出吧,提示正确的使用方法;
        if (!"java".equals(args[0])) {
            isRightFmt = false;
            return;
        }
        if ("-help".equals(args[1]) || "-?".equals(args[1])) {
            helpFlag = true;
        } else if ("-version".equals(args[1])) {
            versionFlag = true;
        } else if ("-cp".equals(args[1]) || "classpath".equals(args[1])) {
            //如果走到这一步,那么命令行必定是java -cp aa/bb test 11 22 33 的形式,所以应该至少有4项;
            if (args.length < 4) {
                isRightFmt = false;
            }
            this.cpOption = args[2];
            this.className = args[3];
            this.args = new String[args.length - 4];
            if (args.length - 4 >= 0)
                System.arraycopy(args, 4, this.args, 0, args.length - 4);
        } else {
            isRightFmt = false;
        }
    }

    public void printUsage() {
        System.out.println("Usage: java [-options] class [args...]");
    }

    public boolean isRightFmt() {
        return isRightFmt;
    }

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public String getCpOption() {
        return cpOption;
    }

    public String getClassName() {
        return className;
    }

    public String[] getArgs() {
        return args;
    }
}
