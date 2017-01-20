package hr.karlovrbic.notify.v1.model.json;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.formatter.DateAdapter;
import hr.karlovrbic.notify.v1.formatter.PrivacyAdapter;
import hr.karlovrbic.notify.v1.json.DateDeserializer;
import hr.karlovrbic.notify.v1.model.Privacy;
import hr.karlovrbic.notify.v1.model.entity.ProfileConfiguration;
import hr.karlovrbic.notify.v1.model.entity.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by Karlo Vrbic on 02.11.16..
 */
@XmlRootElement
public class UserJson {

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_USERNAME = "username";
    private static final String ATTRIBUTE_PASSWORD = "password";
    private static final String ATTRIBUTE_EMAIL = "email";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SURNAME = "surname";
    private static final String ATTRIBUTE_BIRTHDAY = "birthday";
    private static final String ATTRIBUTE_CREATED_AT = "created_at";
    private static final String ATTRIBUTE_PROFILE_PICTURE = "profile_picture_link";
    private static final String ATTRIBUTE_PROFILE_CONFIGURATION = "profile_configuration";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
    private String username;
    @XmlElement(name = ATTRIBUTE_PASSWORD, required = true)
    private String password;
    @XmlElement(name = ATTRIBUTE_EMAIL, required = true)
    private String email;
    @XmlElement(name = ATTRIBUTE_NAME, required = true)
    private String name;
    @XmlElement(name = ATTRIBUTE_SURNAME, required = true)
    private String surname;
    @XmlElement(name = ATTRIBUTE_BIRTHDAY, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDay;
    @XmlElement(name = ATTRIBUTE_CREATED_AT)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date createdAt;
    @XmlElement(name = ATTRIBUTE_PROFILE_PICTURE)
    private String profilePictureLink;
    @XmlElement(name = ATTRIBUTE_PROFILE_CONFIGURATION, required = true)
    @XmlJavaTypeAdapter(PrivacyAdapter.class)
    private PrivacyJson privacyJson;

    private UserJson(Long id,
                     String username,
                     String password,
                     String email,
                     String name,
                     String surname,
                     Date birthDay,
                     Date createdAt,
                     String profilePictureLink,
                     PrivacyJson privacyJson) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.profilePictureLink = profilePictureLink;

        if (privacyJson == null) {
            this.privacyJson = new PrivacyJson(Privacy.PUBLIC);
        } else {
            this.privacyJson = privacyJson;
        }
    }

    public UserJson() {
    }

    public static UserJson fromEntity(User user) {
        return new UserJson(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getBirthDay(),
                user.getCreatedAt(),
                user.getProfilePictureLink(),
                PrivacyJson.fromEntity(user.getProfileConfiguration()));
    }

    public User toEntity() {
        return new User(id,
                username,
                password,
                email,
                name,
                surname,
                birthDay,
                createdAt,
                new ProfileConfiguration(privacyJson.toEntity()),
                profilePictureLink,
                null,
                null,
                null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public PrivacyJson getPrivacyJson() {
        return privacyJson;
    }

    public void setPrivacyJson(PrivacyJson privacyJson) {
        this.privacyJson = privacyJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserJson userJson = (UserJson) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, userJson.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .setPrettyPrinting()
                .create().toJson(this);
    }
}