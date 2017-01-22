package hr.karlovrbic.notify.v1.features.event.response;

import hr.karlovrbic.notify.v1.formatter.DateAdapter;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.utils.ListUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
@XmlRootElement
public class EventResponse {

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_CREATOR = "creator";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_DATE = "date";
    private static final String ATTRIBUTE_DESCRIPTION = "description";
    private static final String ATTRIBUTE_CREATED_AT = "created_at";
    private static final String ATTRIBUTE_PICTURE = "picture_link";
    private static final String ATTRIBUTE_SUBSCRIBERS = "subscribers";
    private static final String ATTRIBUTE_MESSAGES = "messages";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_CREATOR, required = true)
    private UserShortResponse creator;
    @XmlElement(name = ATTRIBUTE_TITLE, required = true)
    private String title;
    @XmlElement(name = ATTRIBUTE_DATE, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;
    @XmlElement(name = ATTRIBUTE_DESCRIPTION, required = true)
    private String description;
    @XmlElement(name = ATTRIBUTE_CREATED_AT, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date createdAt;
    @XmlElement(name = ATTRIBUTE_PICTURE)
    private String pictureLink;
    @XmlElement(name = ATTRIBUTE_SUBSCRIBERS, required = true)
    private List<UserShortResponse> subscribers;
    @XmlElement(name = ATTRIBUTE_MESSAGES, required = true)
    private List<MessageShortResponse> messages;

    public EventResponse(Long id,
                         UserShortResponse creator,
                         String title,
                         Date date,
                         String description,
                         Date createdAt,
                         String pictureLink,
                         List<UserShortResponse> subscribers,
                         List<MessageShortResponse> messages) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.date = date;
        this.description = description;
        this.createdAt = createdAt;
        this.pictureLink = pictureLink;
        this.subscribers = subscribers;
        this.messages = messages;
    }

    public EventResponse() {
    }

    public static EventResponse fromEntity(Event event) {
        UserShortResponse creator = UserShortResponse.fromEntity(event.getCreator());
        List<UserShortResponse> subscribers = event.getSubscribedUsers().stream()
                .map(UserShortResponse::fromEntity)
                .collect(Collectors.toList());
        List<MessageShortResponse> messages = event.getMessages().stream()
                .map(MessageShortResponse::fromEntity)
                .collect(Collectors.toList());

        return new EventResponse(event.getId(),
                creator,
                event.getTitle(),
                event.getDate(),
                event.getDescription(),
                event.getCreatedAt(),
                event.getPictureLink(),
                subscribers,
                messages);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserShortResponse getCreator() {
        return creator;
    }

    public void setCreator(UserShortResponse creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public List<UserShortResponse> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<UserShortResponse> subscribers) {
        this.subscribers = ListUtil.getNonNull(subscribers);
    }

    public List<MessageShortResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageShortResponse> messages) {
        this.messages = ListUtil.getNonNull(messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventResponse response = (EventResponse) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, response.id)
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
        return "EventResponse{" +
                "id=" + id +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", pictureLink='" + pictureLink + '\'' +
                ", subscribers=" + subscribers +
                ", messages=" + messages +
                '}';
    }

    public static class UserShortResponse {

        static final String ATTRIBUTE_ID = "id";
        static final String ATTRIBUTE_USERNAME = "username";

        @XmlElement(name = ATTRIBUTE_ID, required = true)
        private Long id;
        @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
        private String username;

        private UserShortResponse(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public UserShortResponse() {
        }

        public static UserShortResponse fromEntity(User user) {
            if (user == null) {
                return null;
            } else {
                return new UserShortResponse(user.getId(), user.getUsername());
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

            UserShortResponse that = (UserShortResponse) o;

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
            return "UserShortResponse{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    public static class MessageShortResponse {

        static final String ATTRIBUTE_ID = "id";
        static final String ATTRIBUTE_USERNAME = "content";

        @XmlElement(name = ATTRIBUTE_ID, required = true)
        private Long id;
        @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
        private String content;

        private MessageShortResponse(Long id, String content) {
            this.id = id;
            this.content = content;
        }

        public MessageShortResponse() {
        }

        public static MessageShortResponse fromEntity(Message message) {
            if (message == null) {
                return null;
            } else {
                return new MessageShortResponse(message.getId(), message.getContent());
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            MessageShortResponse that = (MessageShortResponse) o;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .append(content, that.content)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(content)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "MessageShortResponse{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
