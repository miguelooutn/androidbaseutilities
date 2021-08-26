package com.hybridss.androidbaseutils.utilities.utils;


import com.hybridss.androidbaseutils.logger.LGLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class UTFileUtilities {
    public static final String REG_DATA_FILENAME = "RegData.json";

    public static final String SEPARADOR = "/";
    public static final String EXT_JPG= ".jpg";
    public static final String EXT_PNG= ".png";
    public static final String EXT_PDF= ".pdf";
    public static final String EXT_TXT= ".txt";
    public static final String EXT_JSON= ".json";
    public static final String EXT_XML= ".xml";
    public static final String SIGNATURES_DIRECTORY = "Firmas";

    private static final String TAG = UTFileUtilities.class.getSimpleName();

    public UTFileUtilities(){ }

    public static boolean existeArchivo(String path){
        File file = new File(LGLogger.PATH + path);
        return file.exists();
    }

    public static boolean crearDirectorio(String nombre){
        String path = LGLogger.PATH + nombre;
        File dir = new File(path);
        if (!dir.exists() && !dir.isDirectory()){
            return dir.mkdir();
        }else{
            return false;
        }
    }

    public static String leerArchivo(String nombreArchivo) {

        String path = LGLogger.PATH + nombreArchivo;
        String cadena = "";

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            cadena = sb.toString();
            br.close();
        } catch (Exception e) {
            LGLogger.e(TAG, e);
        }
        return cadena;
    }

    public static boolean guardarArchivo(String nombreArchivo, String cadena) {
        try {
            File file=new File(LGLogger.PATH + nombreArchivo);
            File directory=new File(file.getParent());
            if(!directory.exists()&&!directory.mkdirs()){
                LGLogger.e(TAG,"No se pudo crear el directorio: "+directory.getAbsolutePath());
                return false;
            }
            nombreArchivo = file.getAbsolutePath();
            FileOutputStream outputStream = new FileOutputStream(new File(nombreArchivo));
            outputStream.write(cadena.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            LGLogger.e(TAG, e);
            return false;
        }
    }

    public static boolean eliminarArchivo(String archivo) {
        String pathArchivo = LGLogger.PATH + archivo;
        File file = new File(pathArchivo);

        if (file.isDirectory()) {
            for (File lista : file.listFiles()) {
                lista.delete();
            }
        }
        return file.delete();
    }

}
