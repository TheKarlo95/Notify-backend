package hr.karlovrbic.notify.v1.model.json;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.formatter.PrivacyAdapter;
import hr.karlovrbic.notify.v1.model.Privacy;
import hr.karlovrbic.notify.v1.model.entity.ProfileConfiguration;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by Karlo Vrbic on 02.11.16..
 */
@XmlRootElement
public class PrivacyJson {

    static final String ATTRIBUTE_PRIVACY = "privacy";

    @XmlElement(name = ATTRIBUTE_PRIVACY, required = true)
    @XmlJavaTypeAdapter(PrivacyAdapter.class)
    private hr.karlovrbic.notify.v1.model.Privacy privacy;

    public PrivacyJson(hr.karlovrbic.notify.v1.model.Privacy privacy) {
        this.privacy = privacy;
    }

    public PrivacyJson() {
        this(null);
    }

    public static PrivacyJson fromEntity(ProfileConfiguration configuration) {
        return new PrivacyJson(configuration.getProfilePrivacy());
    }

    public Privacy toEntity() {
        return privacy;
    }

    public hr.karlovrbic.notify.v1.model.Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(hr.karlovrbic.notify.v1.model.Privacy privacy) {
        this.privacy = privacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PrivacyJson that = (PrivacyJson) o;

        return new EqualsBuilder()
                .append(privacy, that.privacy)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(privacy)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create().toJson(this);
    }
}
