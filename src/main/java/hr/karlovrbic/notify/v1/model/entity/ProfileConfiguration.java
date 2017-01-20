package hr.karlovrbic.notify.v1.model.entity;

import hr.karlovrbic.notify.v1.model.json.PrivacyJson;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by thekarlo95 on 10/30/16.
 */
@Embeddable
public class ProfileConfiguration implements Serializable {

    static final String COLUMN_PRIVACY = "profile_privacy";

    private hr.karlovrbic.notify.v1.model.Privacy profilePrivacy;

    public ProfileConfiguration(hr.karlovrbic.notify.v1.model.Privacy profilePrivacy) {
        setProfilePrivacy(profilePrivacy);
    }

    public ProfileConfiguration() {
    }

    public PrivacyJson toJson() {
        return new PrivacyJson(profilePrivacy);
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = COLUMN_PRIVACY)
    public hr.karlovrbic.notify.v1.model.Privacy getProfilePrivacy() {
        return profilePrivacy;
    }

    public void setProfilePrivacy(hr.karlovrbic.notify.v1.model.Privacy profilePrivacy) {
        if (profilePrivacy == null) {
            this.profilePrivacy = hr.karlovrbic.notify.v1.model.Privacy.PUBLIC;
        } else {
            this.profilePrivacy = profilePrivacy;
        }
    }
}
