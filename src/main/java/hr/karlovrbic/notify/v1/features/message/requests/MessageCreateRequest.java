package hr.karlovrbic.notify.v1.features.message.requests;

import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
@XmlRootElement
public class MessageCreateRequest {

    static final String ATTRIBUTE_CONTENT = "content";
    static final String ATTRIBUTE_CREATOR = "creator";
    static final String ATTRIBUTE_EVENT = "event";

    @XmlElement(name = ATTRIBUTE_CONTENT, required = true)
    private String content;
    @XmlElement(name = ATTRIBUTE_CREATOR, required = true)
    private UserShortJson creator;
    @XmlElement(name = ATTRIBUTE_EVENT, required = true)
    private EventShortJson event;

    public MessageCreateRequest(String content, UserShortJson creator, EventShortJson event) {
        this.content = content;
        this.creator = creator;
        this.event = event;
    }

    public MessageCreateRequest() {
    }

    public static MessageCreateRequest fromEntity(Message message) {
        return new MessageCreateRequest(message.getContent(),
                UserShortJson.fromEntity(message.getEvent().getCreator()),
                EventShortJson.fromEntity(message.getEvent()));
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserShortJson getCreator() {
        return creator;
    }

    public void setCreator(UserShortJson creator) {
        this.creator = creator;
    }

    public EventShortJson getEvent() {
        return event;
    }

    public void setEvent(EventShortJson event) {
        this.event = event;
    }

    public static class UserShortJson {

        static final String ATTRIBUTE_ID = "id";
        static final String ATTRIBUTE_USERNAME = "username";

        @XmlElement(name = ATTRIBUTE_ID, required = true)
        private Long id;
        @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
        private String username;

        private UserShortJson(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public UserShortJson() {
            this(null, null);
        }

        public static UserShortJson fromEntity(User user) {
            if (user == null) {
                return null;
            } else {
                return new UserShortJson(user.getId(), user.getUsername());
            }
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
            return "UserShortJson{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    public static class EventShortJson {

        static final String ATTRIBUTE_ID = "id";
        static final String ATTRIBUTE_TITLE = "title";

        @XmlElement(name = ATTRIBUTE_ID)
        private Long id;
        @XmlElement(name = ATTRIBUTE_TITLE, required = true)
        private String title;

        private EventShortJson(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public EventShortJson() {
            this(null, null);
        }

        public static EventShortJson fromEntity(Event event) {
            if (event == null) {
                return null;
            } else {
                return new EventShortJson(event.getId(), event.getTitle());
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String username) {
            this.title = username;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            EventShortJson that = (EventShortJson) o;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .append(title, that.title)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(title)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "EventShortJson{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
