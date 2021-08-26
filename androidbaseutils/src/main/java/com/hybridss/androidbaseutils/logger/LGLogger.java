package com.baz.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class LGLogger {
    public final static int VERBOSE = 0;
    public final static int DEBUG = 1;
    public final static int INFO = 2;
    public final static int WARN = 3;
    public final static int ERROR = 4;

    //paths
    public static String resourceDocuments = "/Documents/";

    public static String PATH;

    public static int debugLevel = DEBUG;
    private static int line;
    private static String stacktrace;

    //formato de los mensajes de salida en los archivos
    //yyyy/mm/dd | hh:mm:ss:SSS | debugLevel | TAG | método | linea | mensaje
    private static void guardarEnArchivo(String TAG, int debugLevel, String mensaje) {
        try {
            if (LGLogger.debugLevel <= debugLevel) {
                Date currentTime = Calendar.getInstance().getTime();
                String fecha = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);
                String hora = new SimpleDateFormat("HH:mm:ss:SSS").format(currentTime);
                String separador = " [~] ";

                String method = Thread.currentThread().getStackTrace()[4].getMethodName();
                line = Thread.currentThread().getStackTrace()[4].getLineNumber();

                String debugTag = "";
                switch (debugLevel) {
                    case VERBOSE:
                        debugTag = "V";
                        break;
                    case DEBUG:
                        debugTag = "D";
                        break;
                    case INFO:
                        debugTag = "I";
                        break;
                    case WARN:
                        debugTag = "W";
                        break;
                    case ERROR:
                        debugTag = "E";
                        break;
                }
                StringBuilder logBuilder = new StringBuilder()
                        .append(fecha).append(" ").append(hora).append(separador)
                        .append(debugTag).append(separador)
                        .append(TAG).append(separador)
                        .append(method).append(separador)
                        .append(line).append(separador)
                        .append(mensaje).append("[~~]")
                        .append("\n");

                LGFileLogger.saveFile(logBuilder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void getErrorLine(Throwable throwable) {
        for (int i = 0; i < throwable.getStackTrace().length; i++) {
            if (Thread.currentThread().getStackTrace()[4].getClassName().equals(throwable.getStackTrace()[i].getClassName())) {
                line = throwable.getStackTrace()[i].getLineNumber();
            }
        }
    }

    private static void getErrorStackTrace(Throwable throwable) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        stacktrace = writer.toString();
    }

    public static void v(String TAG, String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            System.out.println("(" + TAG + ")V--> " + mensaje);
            guardarEnArchivo(TAG, VERBOSE, mensaje);
        }
    }

    public static void d(String TAG, String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            System.out.println("(" + TAG + ")D--> " + mensaje);
            guardarEnArchivo(TAG, DEBUG, mensaje);
        }
    }

    public static void i(String TAG, String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            System.out.println("(" + TAG + ")I--> " + mensaje);
            guardarEnArchivo(TAG, INFO, mensaje);
        }
    }

    public static void w(String TAG, String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            System.out.println("(" + TAG + ")W--> " + mensaje);
            guardarEnArchivo(TAG, WARN, mensaje);
        }
    }

    public static void e(String TAG, Throwable throwable) {
        String stackStrace = "";
        for (String token : Arrays.toString(throwable.getStackTrace()).split(",")) {
            stackStrace += token + "\n";
        }
        getErrorLine(throwable);
        getErrorStackTrace(throwable);
        throwable.printStackTrace();
        guardarEnArchivo(TAG, ERROR, stackStrace);
        guardarEnArchivo(TAG, ERROR, stacktrace);
    }

    public static void e(String TAG, String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            System.out.println("(" + TAG + ")E--> " + mensaje);
            guardarEnArchivo(TAG, ERROR, mensaje);
        }
    }
}