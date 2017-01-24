package hr.karlovrbic.notify.v1.network.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Karlo Vrbic on 24.01.17..
 */
public class AuthenticationInterceptor implements Interceptor {

    private String apiKey;
    private Request.Builder jsonContentType;

    public AuthenticationInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        setJsonContentType(builder);
        setAuthHeader(builder, apiKey);

        request = builder.build();
        return chain.proceed(request);
    }

    private void setJsonContentType(Request.Builder builder) {
        if (builder != null) {
            builder.header("Content-Type", "application/json");
        }
    }

    private void setAuthHeader(Request.Builder builder, String token) {
        if (builder != null && token != null) {
            builder.header("Authorization", "key=" + token);
        }
    }
}
