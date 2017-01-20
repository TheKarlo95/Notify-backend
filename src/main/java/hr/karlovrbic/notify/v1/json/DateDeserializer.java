package hr.karlovrbic.notify.v1.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String jsonDate = json.getAsString();
        if (!(jsonDate == null || jsonDate.isEmpty())) {
            return new Date(Long.parseLong(jsonDate) * 1000);
        } else {
            return null;
        }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {
            return context.serialize(src.getTime() / 1000);
        } else {
            return null;
        }
    }
}