package hr.karlovrbic.notify.v1.model.json.shortened;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.model.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;

public class UserShortJson {

    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_USERNAME = "username";

    @XmlElement(name = ATTRIBUTE_ID, required = true)
    private Long id;
    @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
    private String username;

    public static UserShortJson fromEntity(User user) {
        if (user == null) {
            return null;
        } else {
            return new UserShortJson(user.getId(), user.getUsername());
        }
    }

    private UserShortJson(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserShortJson() {
        this(null, null);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserShortJson that = (UserShortJson) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(username, that.username)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(username)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create().toJson(this);
    }
}