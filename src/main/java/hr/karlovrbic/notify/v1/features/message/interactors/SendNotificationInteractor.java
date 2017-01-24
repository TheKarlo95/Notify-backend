package hr.karlovrbic.notify.v1.features.message.interactors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hr.karlovrbic.notify.v1.features.message.IMessage;
import hr.karlovrbic.notify.v1.features.message.response.NotificationRequest;
import hr.karlovrbic.notify.v1.network.ApiProvider;
import hr.karlovrbic.notify.v1.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Karlo Vrbic on 24.01.17..
 */
public class SendNotificationInteractor implements IMessage.SendNotificationInteractor {

    private static final String RESPONSE_SUCCESS = "message_id";
    private static final String RESPONSE_FAILURE = "error";

    private ApiService service;

    public SendNotificationInteractor() {
        this.service = ApiProvider.getService();
    }

    @Override
    public void send(NotificationRequest request) {
        service.sendNotification(request).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject json = response.body();

                    JsonElement element = null;
                    if ((element = json.get(RESPONSE_SUCCESS)) != null) {
                        System.out.println("Notification sending succeeded.");
                        System.out.println(element.toString());
                    } else if ((element = json.get(RESPONSE_FAILURE)) != null) {
                        System.out.println("Notification sending failed.");
                        System.out.println(element.toString());
                    } else {
                        System.out.println("Notification sending failed - unknown.");
                        System.out.println(element.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                System.out.println("Notification sending succeeded.");
                throwable.printStackTrace();
            }
        });
    }
}
