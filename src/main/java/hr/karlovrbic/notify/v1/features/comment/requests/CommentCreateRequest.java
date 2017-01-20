package hr.karlovrbic.notify.v1.features.comment.requests;

import hr.karlovrbic.notify.v1.model.entity.Comment;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
public class CommentCreateRequest {

    static final String ATTRIBUTE_CONTENT = "content";
    static final String ATTRIBUTE_CREATOR = "creator";
    static final String ATTRIBUTE_MESSAGE = "message";

    @XmlElement(name = ATTRIBUTE_CONTENT, required = true)
    private String content;
    @XmlElement(name = ATTRIBUTE_CREATOR, required = true)
    private UserShortJson creator;
    @XmlElement(name = ATTRIBUTE_MESSAGE, required = true)
    private MessageShortJson message;

    public static CommentCreateRequest fromEntity(Comment comment) {
        return new CommentCreateRequest(comment.getContent(),
                UserShortJson.fromEntity(comment.getCreator()),
                MessageShortJson.fromEntity(comment.getMessage()));
    }

    public CommentCreateRequest(String content, UserShortJson creator, MessageShortJson message) {
        this.content = content;
        this.creator = creator;
        this.message = message;
    }

    public CommentCreateRequest(){}

    public String getContent() {
        return content;
    }

    public UserShortJson getCreator() {
        return creator;
    }

    public MessageShortJson getMessage() {
        return message;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessage(MessageShortJson message) {
        this.message = message;
    }

    public void setCreator(UserShortJson creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CommentCreateRequest that = (CommentCreateRequest) o;

        return new EqualsBuilder()
                .append(content, that.content)
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(content)
                .append(message)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CommentCreateRequest{" +
                "content='" + content + '\'' +
                ", message=" + message +
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
            return "UserShortJson{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    public static class MessageShortJson {

        static final String ATTRIBUTE_ID = "id";
        static final String ATTRIBUTE_USERNAME = "content";

        @XmlElement(name = ATTRIBUTE_ID, required = true)
        private Long id;
        @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
        private String content;

        public static MessageShortJson fromEntity(Message message) {
            if (message == null) {
                return null;
            } else {
                return new MessageShortJson(message.getId(), message.getContent());
            }
        }

        private MessageShortJson(Long id, String content) {
            this.id = id;
            this.content = content;
        }

        public MessageShortJson() {
        }

        public Long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            MessageShortJson that = (MessageShortJson) o;

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
            return "MessageShortJson{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
