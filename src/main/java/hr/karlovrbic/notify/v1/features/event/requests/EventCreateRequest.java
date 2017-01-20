package hr.karlovrbic.notify.v1.features.event.requests;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
@XmlRootElement
public class EventCreateRequest {

    static final String ATTRIBUTE_CREATOR = "creator";
    static final String ATTRIBUTE_TITLE = "title";
    static final String ATTRIBUTE_DESCRIPTION = "description";

    @XmlElement(name = ATTRIBUTE_CREATOR, required = true)
    private UserShortJson creator;
    @XmlElement(name = ATTRIBUTE_TITLE, required = true)
    private String title;
    @XmlElement(name = ATTRIBUTE_DESCRIPTION, required = true)
    private String description;

    public static EventCreateRequest fromEntity(Event event) {
        UserShortJson creatorJson = null;
        User creator = event.getCreator();
        if (creator != null) {
            creatorJson = new UserShortJson(creator.getId(), creator.getUsername());
        }

        return new EventCreateRequest(creatorJson,
                event.getTitle(),
                event.getDescription());
    }

    private EventCreateRequest(UserShortJson creator,
                               String title,
                               String description) {
        this.creator = creator;
        this.title = title;
        this.description = description;
    }

    public EventCreateRequest() {
    }

    public UserShortJson getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setCreator(UserShortJson creator) {
        this.creator = creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EventCreateRequest that = (EventCreateRequest) o;

        return new EqualsBuilder()
                .append(creator, that.creator)
                .append(title, that.title)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(creator)
                .append(title)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "EventCreateRequest{" +
                "creator=" + creator +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class UserShortJson {

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
}
