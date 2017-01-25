package hr.karlovrbic.notify.v1.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Karlo Vrbic on 24.01.17..
 */
public class ApiProvider {

    private static final int NETWORK_TIMEOUT_SECONDS = 40;
    private static final String API_URL = "https://fcm.googleapis.com/";

    private static final ApiService service;

    static {
        Gson gson = provideGson();
        Converter.Factory gsonFactory = provideGsonConverter(gson);
        Converter.Factory scalarFactory = provideScalarConverter();
        HttpLoggingInterceptor.Level level = provideLogLevel();
        HttpLoggingInterceptor logger = provideLog(level);
        Integer networkTimoutSeconds = provideNetworkTimeout();
        OkHttpClient client = provideOkHttpClient(logger, networkTimoutSeconds);
        HttpUrl endpoint = provideEndpoint();

        service = provideApiService(client, gsonFactory, scalarFactory, endpoint);
    }

    public static synchronized ApiService getService() {
        return service;
    }

    private static ApiService provideApiService(OkHttpClient client,
                                                Converter.Factory gson,
                                                Converter.Factory scalar,
                                                HttpUrl endpoint) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(scalar)
                .addConverterFactory(gson)
                .build();

        return retrofit.create(ApiService.class);
    }

    private static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logger, Integer networkTimoutSeconds) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(networkTimoutSeconds, TimeUnit.SECONDS);
        builder.connectTimeout(networkTimoutSeconds, TimeUnit.SECONDS);
        builder.addInterceptor(logger);

        return builder.build();
    }

    private static HttpLoggingInterceptor provideLog(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level);
        return interceptor;
    }

    private static HttpLoggingInterceptor.Level provideLogLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }

    private static Converter.Factory provideGsonConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    private static Converter.Factory provideScalarConverter() {
        return ScalarsConverterFactory.create();
    }

    private static Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    private static HttpUrl provideEndpoint() {
        return HttpUrl.parse(API_URL);
    }

    private static Integer provideNetworkTimeout() {
        return NETWORK_TIMEOUT_SECONDS;
    }
}
