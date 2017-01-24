package hr.karlovrbic.notify.v1.features.message.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import hr.karlovrbic.notify.v1.model.entity.Comment;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 22.01.17..
 */
@XmlRootElement
public class MessageResponse {

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_CONTENT = "content";
    private static final String ATTRIBUTE_EVENT = "event";
    private static final String ATTRIBUTE_COMMENTS = "comments";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_CONTENT, required = true)
    private String content;
    @XmlElement(name = ATTRIBUTE_EVENT, required = true)
    private EventShortResponse event;
    @XmlElement(name = ATTRIBUTE_COMMENTS, required = true)
    private List<CommentShortResponse> comments;

    public MessageResponse(Long id, String content, EventShortResponse event, List<CommentShortResponse> comments) {
        this.id = id;
        this.content = content;
        this.event = event;
        this.comments = comments;
    }

    public MessageResponse() {
    }

    public static MessageResponse fromEntity(Message message) {
        if (message == null) {
            return null;
        } else {
            List<CommentShortResponse> comments = message.getComments().stream()
                    .map(CommentShortResponse::fromEntity)
                    .collect(Collectors.toList());

            return new MessageResponse(message.getId(),
                    message.getContent(),
                    EventShortResponse.fromEntity(message.getEvent()),
                    comments);
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

    public EventShortResponse getEvent() {
        return event;
    }

    public void setEvent(EventShortResponse event) {
        this.event = event;
    }

    public List<CommentShortResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentShortResponse> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MessageResponse that = (MessageResponse) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(content, that.content)
                .append(event, that.event)
                .append(comments, that.comments)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(content)
                .append(event)
                .append(comments)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", event=" + event +
                ", comments=" + comments +
                '}';
    }

    public static class EventShortResponse {

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_CREATOR = "creator";
        private static final String ATTRIBUTE_TITLE = "username";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_TITLE)
        @Expose
        private String title;
        @SerializedName(ATTRIBUTE_CREATOR)
        @Expose
        private UserShortResponse creator;

        public EventShortResponse(Long id, String title, UserShortResponse creator) {
            this.id = id;
            this.title = title;
            this.creator = creator;
        }

        public EventShortResponse() {
        }

        public static EventShortResponse fromEntity(Event event) {
            if (event == null) {
                return null;
            } else {
                return new EventShortResponse(event.getId(),
                        event.getTitle(),
                        UserShortResponse.fromEntity(event.getCreator()));
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

        public void setTitle(String title) {
            this.title = title;
        }

        public UserShortResponse getCreator() {
            return creator;
        }

        public void setCreator(UserShortResponse creator) {
            this.creator = creator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            EventShortResponse eventShortResponse = (EventShortResponse) o;

            return new EqualsBuilder()
                    .append(id, eventShortResponse.id)
                    .append(title, eventShortResponse.title)
                    .append(creator, eventShortResponse.creator)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(title)
                    .append(creator)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "EventShortResponse{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", creator=" + creator +
                    '}';
        }
    }

    public static class UserShortResponse {

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_USERNAME = "username";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_USERNAME)
        @Expose
        private String username;

        public UserShortResponse(Long id, String username) {
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

            UserShortResponse user = (UserShortResponse) o;

            return new EqualsBuilder()
                    .append(id, user.id)
                    .append(username, user.username)
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

    public static class CommentShortResponse {

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_CREATOR = "creator";
        private static final String ATTRIBUTE_CONTENT = "content";

        @SerializedName(ATTRIBUTE_ID)
        @Expose
        private Long id;
        @SerializedName(ATTRIBUTE_CONTENT)
        @Expose
        private String content;
        @SerializedName(ATTRIBUTE_CREATOR)
        @Expose
        private UserShortResponse creator;

        public CommentShortResponse(Long id, String content, UserShortResponse creator) {
            this.id = id;
            this.content = content;
            this.creator = creator;
        }

        public CommentShortResponse() {
        }

        public static CommentShortResponse fromEntity(Comment comment) {
            if (comment == null) {
                return null;
            } else {
                return new CommentShortResponse(comment.getId(),
                        comment.getContent(),
                        UserShortResponse.fromEntity(comment.getCreator()));
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

        public UserShortResponse getCreator() {
            return creator;
        }

        public void setCreator(UserShortResponse creator) {
            this.creator = creator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            CommentShortResponse comment = (CommentShortResponse) o;

            return new EqualsBuilder()
                    .append(id, comment.id)
                    .append(content, comment.content)
                    .append(creator, comment.creator)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(content)
                    .append(creator)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "CommentShortResponse{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", creator=" + creator +
                    '}';
        }
    }
}
