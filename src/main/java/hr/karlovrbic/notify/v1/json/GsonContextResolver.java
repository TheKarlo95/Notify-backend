package hr.karlovrbic.notify.v1.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class GsonContextResolver implements ContextResolver<Gson> {

    @Override
    public Gson getContext(final Class<?> type) {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();
    }
}