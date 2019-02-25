package com.vip.qa.autov.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kevin02.zhou on 2016/8/16.
 */
public class FileLockUtils {

    private static Logger logger = LoggerFactory.getLogger(FileLockUtils.class);

    private static String PID = getPid();

    public static boolean isFileInUsed(File file) {
        boolean beingUsed = false;
        if (isOSLinux()) {
            beingUsed = isFileBeingUsedLinux(file);
        }
        else if (isOSWindows()) {
            beingUsed = isFileBeingUsedWindows(file);
        }
        return beingUsed;
    }

    private static boolean isFileBeingUsedLinux(File file) {
        boolean beingUsed = false;
        BufferedReader reader = null;
        try {
            Process process = new ProcessBuilder("lsof", file.getAbsolutePath())
                    .redirectErrorStream(true).start();
            //process.waitFor(); // Be careful if the command returns lots of chars
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            List<String> resultSet = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                resultSet.add(line);
            }
            Collection<String> pidList = convertToPIDList(resultSet);
            pidList.remove(PID);
            beingUsed = (!pidList.isEmpty());

        } catch (Exception e) {
            logger.error(ExceptionUtils.getRootCauseMessage(e));
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //Ignore
                }
            }
        }
        return beingUsed;
    }

    private static boolean isFileBeingUsedWindows(File file) {
        boolean beingUsed = false;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
            randomAccessFile.close();
            beingUsed = !file.renameTo(file);
        } catch (FileNotFoundException e) {
            beingUsed = true;
        } catch (IOException e) {
            logger.error(ExceptionUtils.getRootCauseMessage(e));
            beingUsed = true;
            //Ignore
        }
        return beingUsed;
    }

    private static Collection<String> convertToPIDList(Collection<String> lsofResultList) {
        Set<String> pidSet = new HashSet<>();
        if (lsofResultList.isEmpty()) {
            return pidSet;
        }
        String headerLine = lsofResultList.iterator().next();
        String[] headers = headerLine.split("\\s+");
        if (headers.length > 2 && headers[1].equals("PID")) {
            lsofResultList.remove(headerLine);
            for (String resultLine : lsofResultList) {
                String[] commands = resultLine.split("\\s+");
                if (commands.length > 2 && StringUtils.isNumeric(commands[1])) {
                    pidSet.add(commands[1]);
                }
            }
        }
        return pidSet;
    }

    private static String getPid() {
        try {
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            return jvmName.split("@")[0];
        } catch (Throwable ex) {
            return null;
        }
    }

    private static boolean isOSWindows() {
        final String osName = System.getProperty("os.name").toLowerCase();
        return osName.startsWith("windows");
    }

    private static boolean isOSLinux() {
        final String osName = System.getProperty("os.name").toLowerCase();
        return osName.equals("linux");
    }
}
