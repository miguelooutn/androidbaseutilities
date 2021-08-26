package com.hybridss.androidbaseutils.logger;

public class LGTest {
    private static LGTest instance;
    private boolean debug;
    private String serviceUrl;

    private String testUser;
    private String tesPassword;

    private LGTest(){
        this.debug = false;
    }

    private static LGTest getInstance(){
        if (instance == null){
            instance = new LGTest();
        }
        return instance;
    }

    public static  void setDebug(boolean debug) {
        LGTest lgTest = getInstance();
        lgTest.debug = debug;
    }

    public static boolean isDebug(){
        LGTest lgTest = getInstance();
        return lgTest.debug;
    }

    public static void setUrl(String serviceUrl){
        LGTest lgTest = getInstance();
        lgTest.serviceUrl = serviceUrl;
    }

    public static String getUrl(){
        LGTest lgTest = getInstance();
        return lgTest.serviceUrl;
    }

    public static void setTestUser(String testUser){
        LGTest lgTest = getInstance();
        lgTest.testUser = testUser;
    }

    public static String getTestUser(){
        LGTest lgTest = getInstance();
        return lgTest.testUser;
    }

    public static void setTestPassword(String tesPassword){
        LGTest lgTest = getInstance();
        lgTest.tesPassword = tesPassword;
    }

    public static String getTestPassword(){
        LGTest lgTest = getInstance();
        return lgTest.tesPassword;
    }
}
