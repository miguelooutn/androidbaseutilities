package com.hybridss.androidbaseutils.utilities.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.hybridss.androidbaseutils.logger.LGLogger;

import java.util.ArrayList;

/**
 * Created by usrmdk on 06/05/18.
 */

public class UTJson {

    private static final String TAG = UTJson.class.getSimpleName();

    public static boolean isJsonObject(String jsonInString) {
        try {
            JsonObject ob = new Gson().fromJson(jsonInString, JsonObject.class);
            if (ob != null) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            LGLogger.e(TAG, ex);
            return false;
        }
    }

    public static boolean isJsonArray(String jsonInString) {
        try {
            JsonArray ob = new Gson().fromJson(jsonInString, JsonArray.class);
            if (ob != null) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            LGLogger.e(TAG, ex);
            return false;
        }
    }


    public static boolean isJsonPrimitive(String jsonInString) {
        try {
            JsonPrimitive ob = new Gson().fromJson(jsonInString, JsonPrimitive.class);

            if (ob != null) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            LGLogger.e(TAG, ex);
            return false;
        }
    }

    public static String isJsonStringValide(JsonObject object, String field) {
        if (object.get(field) != null && !object.get(field).isJsonNull()) {
            if (object.get(field).getAsString() != null && !object.get(field).toString().contains("null")) {
                return object.get(field).getAsString();
            }
            return "";
        }
        return "";
    }

    public static int isJsonIntValide(JsonObject object, String field) {

        if (object.get(field) != null && !object.get(field).isJsonNull()) {
            return object.get(field).getAsInt();
        }
        return 0;
    }

    public static boolean isElementValido(JsonObject object, String elemento) {
        return (object.get(elemento) != null && !object.get(elemento).isJsonNull());
    }

    public static double jsonGetDoble(JsonElement jsonElement) {
        try {
            if (jsonElement != null) {
                if (!jsonElement.isJsonNull()) {
                    return jsonElement.getAsDouble();
                }
            }
        } catch (Exception ex) {
            LGLogger.e(TAG, "Error al parsear Doble: " + jsonElement);
        }
        return 0.0;

    }

    public static Boolean jsonGetBoolean(JsonElement jsonElement) {
        try {
            if (jsonElement != null) {
                if (!jsonElement.isJsonNull()) {
                    return jsonElement.getAsBoolean();
                }
            }
        } catch (Exception ex) {
            LGLogger.e(TAG, "Error al obtener doble");
        }
        return null;

    }

    public static String jsonGetString(JsonElement jsonElement) {
        try {
            if (jsonElement != null) {
                if (!jsonElement.isJsonNull()) {
                    return jsonElement.getAsString().trim();
                }
            }
        } catch (Exception ex) {
            LGLogger.e(TAG, "Error al parsear String: " + jsonElement);
        }
        return "";
    }

    public static int jsonGetInt(JsonElement jsonElement) {
        try {
            if (jsonElement != null) {
                if (!jsonElement.isJsonNull()) {
                    return jsonElement.getAsInt();
                }
            }
        } catch (Exception ex) {
            LGLogger.e(TAG, "Error al parsear Int: " + jsonElement);
        }
        return 0;
    }

    public static JsonObject jsonGetJsonObject(JsonElement jsonElement) {
        try {
            if (jsonElement != null) {
                if (!jsonElement.isJsonNull() && jsonElement.isJsonObject()) {
                    return jsonElement.getAsJsonObject();
                }
            }
        } catch (Exception ex) {
            LGLogger.e(TAG, "Error al obtener JsonObject");
        }
        return new JsonObject();
    }


    public static String isJsonIntValideToString(JsonObject object, String field)
    {
        if (object.get(field) != null && !object.get(field).isJsonNull() && object.get(field).getAsString() != null && !object.get(field).toString().contains("null")) {
            return object.get(field).getAsString();
        }
        return "0";
    }

    public static int isJsonIntValideDefault(JsonObject object, String field, int def) {

        if (object.get(field) != null && !object.get(field).isJsonNull()) {
            try {
                return object.get(field).getAsInt();
            } catch (Exception e){
                LGLogger.e(TAG,"Error al parcear");
                return def;
            }
        }
        return def;
    }

    public static JsonObject convertirAGsonObject(Object object) {
        Gson gson = new GsonBuilder().create();
        return gson.toJsonTree(object).getAsJsonObject();
        //return new Gson().fromJson(new Gson().toJson(object.getClass().cast(object)), JsonObject.class);
    }

    public static Object convertirGsonAObject(Class clase, JsonObject jsonObject) {
        return new Gson().fromJson(jsonObject, clase);
    }

    public static JsonArray convertirArrayAGsonArray(ArrayList array) {
        Gson gson = new GsonBuilder().create();
        return gson.toJsonTree(array).getAsJsonArray();
    }

    public static double isJsonDoubleValideDefault(JsonObject object, String field, double def) {

        if (object.get(field) != null && !object.get(field).isJsonNull()) {
            return object.get(field).getAsDouble();
        }
        return def;
    }

    public static String isJsonStringValideDefault(JsonObject object, String field, String def) {

        if (object.get(field) != null && !object.get(field).isJsonNull()) {
            if (object.get(field).getAsString() != null && !object.get(field).toString().contains("null")) {
                return object.get(field).getAsString();
            }
            return def;
        }
        return def;
    }

    public static boolean isJsonBooleanValideDefault(JsonObject object, String field, boolean def) {
        if (object.get(field) != null && !object.get(field).isJsonNull()) {
            return object.get(field).getAsBoolean();
        }
        return def;
    }

    public static JsonArray jsonGetJsonArray(JsonElement jsonElement) {
        try {
            if (jsonElement != null) {
                if (!jsonElement.isJsonNull() && jsonElement.isJsonArray()) {
                    return jsonElement.getAsJsonArray();
                }
            }
        } catch (Exception ex) {
            LGLogger.e(TAG, "Error al obtener JsonArray");
        }
        return new JsonArray();
    }

    /**
     * Obtiene las llaves de un JsonObject y devuelve un ArrayList de las mismas
     */
    public static ArrayList<String> allKeys(JsonObject jsonObject) {
        ArrayList<String> keys = null;
        try {
            keys = new ArrayList<>(jsonObject.keySet());
        }
        catch (Exception e)
        {
            LGLogger.e(TAG, e.getMessage());
        }
        return keys;
    }

    /**
     * Obtiene las llaves de un objeto de un JsonObject y devuelve un ArrayList de las mismas
     */
    public static ArrayList<String> allKeysForObject(JsonObject jsonObject, String object) {
        ArrayList<String> keys = allKeys(jsonObject);
        ArrayList<String> keysForObject = new ArrayList<>();
        for (String key : keys) {
            if (jsonGetString(jsonObject.get(key)).equals(object)) {
                keysForObject.add(key);
            }

        }
        return keysForObject;
    }

    /**
     * Retorna un Arraylist de los elementos de a-b
     */
    public static ArrayList<String> minusSet(ArrayList<String> a, ArrayList<String> b) {
        ArrayList<String> minusSetArray = new ArrayList<>(a);
        for (String s : b) {
            minusSetArray.remove(s);
        }
        return minusSetArray;
    }

    /**
     * Obtiene un JsonObject de un String
     */
    public static JsonObject stringToJsonObject(String s) {
        JsonObject jsonObject = null;
        try {
            jsonObject = new Gson().fromJson(s, JsonObject.class);

        } catch (Exception e) {
            LGLogger.e(TAG, e);
        }
        return jsonObject;
    }


}
