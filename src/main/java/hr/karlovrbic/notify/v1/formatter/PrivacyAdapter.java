package hr.karlovrbic.notify.v1.formatter;

import hr.karlovrbic.notify.v1.model.Privacy;
import hr.karlovrbic.notify.v1.model.json.PrivacyJson;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
public class PrivacyAdapter extends XmlAdapter<String, PrivacyJson> {

    @Override
    public String marshal(PrivacyJson privacy) throws Exception {
        return privacy.getPrivacy().toNumber();
    }

    @Override
    public PrivacyJson unmarshal(String v) throws Exception {
        return new PrivacyJson(Privacy.fromString(v));
    }

}