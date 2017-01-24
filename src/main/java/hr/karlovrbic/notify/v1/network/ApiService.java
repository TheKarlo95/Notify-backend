package hr.karlovrbic.notify.v1.network;

import com.google.gson.JsonObject;
import hr.karlovrbic.notify.v1.features.message.response.NotificationRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TheKarlo95 on 18.10.2016..
 */
public interface ApiService {

    @POST("fcm/send/")
    Call<JsonObject> sendNotification(@Body NotificationRequest request);
}
