package com.baz.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.baz.logger.LGLogger.PATH;

public class LGFileLogger {
    private static final int DEFAULT_LOG_MAX_FILE_SIZE = 1024;  // 1 MB
    private static final int DEFAULT_LOG_MAX_NUM_LOG_FILES = 5; // 5 archivos
    private static String mLogsPath;

    //Función general que sirve para validar si se debe crear archivo nuevo, escribir en uno existen o eliminar el primero que se creo.
    public static void saveFile(StringBuilder logBuilder) {
        createFolders();

        int totalLogs = getTotalLogFiles();
        if (totalLogs == 0) {
            createLog(logBuilder, getLogFileName());
        } else {
            String nameFile = getNameOfFile(true);
            if (getSizeFile(nameFile)) {
                createLog(logBuilder, nameFile);
            } else {
                if (totalLogs == DEFAULT_LOG_MAX_NUM_LOG_FILES) {
                    deleteFile(getNameOfFile(false));
                }
                createLog(logBuilder, getLogFileName());
            }
        }
    }

    //Crea la carpeta de logs
    private static void createFolders() {
        File documents = new File(PATH);
        documents.mkdirs();

        mLogsPath = PATH + "logs/";
        File folderLogs = new File(mLogsPath);
        folderLogs.mkdirs();
    }

    //Obtiene el total de archivos logs en la carpeta de log
    private static int getTotalLogFiles() {
        FilenameFilter textFilter = (dir, name) -> name.endsWith(".txt");
        File folder = new File(mLogsPath);
        File[] listOfFiles = folder.listFiles(textFilter);
        return listOfFiles.length;
    }

    //Escribe la bitacora en el archivo
    private static void createLog(StringBuilder logBuilder, String logFileName) {
        File logFile = new File(mLogsPath + logFileName);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(new File(mLogsPath + logFileName), true);
            outputStream.write(logBuilder.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Obtiene el nombre del archivo mas reciente o el más viejo
    private static String getNameOfFile(boolean recent) {
        FilenameFilter textFilter = (dir, name) -> name.endsWith(".txt");
        File folder = new File(mLogsPath);
        File[] listOfFiles = folder.listFiles(textFilter);
        List<String> files = new ArrayList<>();
        for (File file:listOfFiles) {
            files.add(file.getName());
        }
        Collections.sort(files);
        Collections.reverse(files);
        if (recent) {
            return files.get(0);
        } else {
            return files.get(4);
        }
    }

    //Verifica si se puede escribir en el mismo archivo validando el peso del mismo
    private static boolean getSizeFile(String name) {
        File file = new File(mLogsPath+name);
        long size = file.length();
        return (int) (size / DEFAULT_LOG_MAX_FILE_SIZE) < DEFAULT_LOG_MAX_FILE_SIZE;
    }

    //Genera nombre para un nuevo archivo log
    private static String getLogFileName() {
        Date currentTime = Calendar.getInstance().getTime();
        String today = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(currentTime);
        return "log-" + today + ".txt";
    }

    //Elimina un archivo log
    private static void deleteFile(String name) {
        File file = new File(mLogsPath+name);
        file.delete();
    }
}
