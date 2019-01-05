package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class FindFiles {

    private static List<File> listFile;

    public void devices() {
        System.out.println("-------------");
        File[] files = File.listRoots();
        for (File f : files) {
            System.out.println(f.getPath());
        }
        System.out.println("-------------");
    }

    public void find(String parentDirectory) {
        parseAllFiles(parentDirectory);
    }

    private static void parseAllFiles(String parentDirectory) {
        listFile = new ArrayList<>();
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        if (filesInDirectory != null) {
            for (File f : filesInDirectory) {
                if (isMp3OrDirectory(f)) {
                    System.out.println(f.getName());
                    listFile.add(f);
                }
            }
        }
    }

    private static boolean isMp3OrDirectory(File file) {
        boolean r = false;

        if (file.isDirectory()) {
            File[] filesInDirectory = file.listFiles();
            if (filesInDirectory != null) {
                for (File f : filesInDirectory) {
                    if (getExtension(f.getName()).equals("mp3")) {
                        r = true;
                        break;
                    }
                }
            }
        } else {
            if (getExtension(file.getName()).equals("mp3")) {
                r = true;
            }
        }
        return r;
    }

    private static String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
