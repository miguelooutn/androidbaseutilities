package com.hybridss.androidbaseutils.utilities.retrofit;



import com.hybridss.androidbaseutils.logger.LGLogger;
import com.hybridss.androidbaseutils.logger.LGTest;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

class UnsafeOkHttpClient {
    private static final String TAG = UnsafeOkHttpClient.class.getSimpleName();
    static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            LGLogger.i(TAG, authType);
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            LGLogger.i(TAG, authType);
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);

            builder.hostnameVerifier((hostname, session) -> true);


            if(LGTest.isDebug()){
                //leer https://stackoverflow.com/questions/32514410/logging-with-retrofit-2 si se quiere modificar
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                return builder.addInterceptor(interceptor);
            }

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
