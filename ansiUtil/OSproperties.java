package ansiUtil;

public class OSproperties {

    public static String getOS() {
        return System.getProperty("os.name");
    }

    public static char getFileSeperator() {
        String OS = getOS();
        if (OS.startsWith("Windows")) {
            return '\\';
        } else {
            return '/';
        }
    }

    public static String getCurrDirOfProject() {
        return System.getProperty("user.dir");
    }

    public static String createPathToProjectFile(String[] dirInOrder, String fileName) {
        char seperator = getFileSeperator();
        StringBuffer sb = new StringBuffer();
        sb.append(getCurrDirOfProject() + seperator);

        for (String file : dirInOrder) {
            sb.append(file + seperator);
        }
        sb.append(fileName);

        return sb.toString();
    }
}
