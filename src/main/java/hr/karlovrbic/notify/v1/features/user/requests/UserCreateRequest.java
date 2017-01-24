package hr.karlovrbic.notify.v1.features.user.requests;

import hr.karlovrbic.notify.v1.formatter.DateAdapter;
import hr.karlovrbic.notify.v1.formatter.PrivacyAdapter;
import hr.karlovrbic.notify.v1.model.Privacy;
import hr.karlovrbic.notify.v1.model.entity.ProfileConfiguration;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.PrivacyJson;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by Karlo Vrbic on 05.01.17..
 */
@XmlRootElement
public class UserCreateRequest {

    private static final String ATTRIBUTE_TOKEN = "fcm_token";
    private static final String ATTRIBUTE_USERNAME = "username";
    private static final String ATTRIBUTE_PASSWORD = "password";
    private static final String ATTRIBUTE_PASSWORD_CONFIRMATION = "password_confirmation";
    private static final String ATTRIBUTE_EMAIL = "email";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SURNAME = "surname";
    private static final String ATTRIBUTE_BIRTHDAY = "birthday";
    private static final String ATTRIBUTE_PROFILE_CONFIGURATION = "profile_configuration";

    @XmlElement(name = ATTRIBUTE_TOKEN)
    private String token;
    @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
    private String username;
    @XmlElement(name = ATTRIBUTE_PASSWORD, required = true)
    private String password;
    @XmlElement(name = ATTRIBUTE_PASSWORD_CONFIRMATION, required = true)
    private String passwordConfirmation;
    @XmlElement(name = ATTRIBUTE_EMAIL, required = true)
    private String email;
    @XmlElement(name = ATTRIBUTE_NAME, required = true)
    private String name;
    @XmlElement(name = ATTRIBUTE_SURNAME, required = true)
    private String surname;
    @XmlElement(name = ATTRIBUTE_BIRTHDAY, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDay;
    @XmlElement(name = ATTRIBUTE_PROFILE_CONFIGURATION)
    @XmlJavaTypeAdapter(PrivacyAdapter.class)
    private PrivacyJson privacyJson;

    public UserCreateRequest(String token,
                             String username,
                             String password,
                             String passwordConfirmation,
                             String email,
                             String name,
                             String surname,
                             Date birthDay,
                             PrivacyJson privacyJson) {
        this.token = token;
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;

        if (privacyJson == null) {
            this.privacyJson = new PrivacyJson(Privacy.PUBLIC);
        } else {
            this.privacyJson = privacyJson;
        }
    }

    public UserCreateRequest(String username,
                             String password,
                             String passwordConfirmation,
                             String email,
                             String name,
                             String surname,
                             Date birthDay,
                             PrivacyJson privacyJson) {
        this(null, username, password, passwordConfirmation, email, name, surname, birthDay, privacyJson);
    }

    public UserCreateRequest() {
    }

    public User toEntity() {
        User user = new User(username,
                password,
                email,
                name,
                surname,
                birthDay,
                new ProfileConfiguration(privacyJson.toEntity()),
                null,
                null,
                null,
                null);
        user.setToken(token);

        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public PrivacyJson getPrivacyJson() {
        return privacyJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserCreateRequest that = (UserCreateRequest) o;

        return new EqualsBuilder()
                .append(username, that.username)
                .append(password, that.password)
                .append(passwordConfirmation, that.passwordConfirmation)
                .append(email, that.email)
                .append(name, that.name)
                .append(surname, that.surname)
                .append(birthDay, that.birthDay)
                .append(privacyJson, that.privacyJson)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(username)
                .append(password)
                .append(passwordConfirmation)
                .append(email)
                .append(name)
                .append(surname)
                .append(birthDay)
                .append(privacyJson)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UserCreateRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                ", privacyJson=" + privacyJson +
                '}';
    }
}
