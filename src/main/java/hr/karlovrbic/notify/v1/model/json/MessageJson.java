package hr.karlovrbic.notify.v1.model.json;

import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.json.shortened.CommentShortJson;
import hr.karlovrbic.notify.v1.model.json.shortened.EventShortJson;
import hr.karlovrbic.notify.v1.model.json.shortened.UserShortJson;
import hr.karlovrbic.notify.v1.utils.ListUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
@XmlRootElement
public class MessageJson {

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_CONTENT = "content";
    private static final String ATTRIBUTE_CREATOR = "creator";
    private static final String ATTRIBUTE_EVENT = "event";
    private static final String ATTRIBUTE_COMMENTS = "comments";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_CONTENT)
    private String content;
    @XmlElement(name = ATTRIBUTE_CREATOR)
    private UserShortJson user;
    @XmlElement(name = ATTRIBUTE_EVENT)
    private EventShortJson event;
    @XmlElement(name = ATTRIBUTE_COMMENTS)
    private List<CommentShortJson> comments;

    public static MessageJson fromEntity(Message message) {
        List<CommentShortJson> comments = message.getComments().stream()
                    .map(CommentShortJson::fromEntity)
                    .collect(Collectors.toList());

        return new MessageJson(message.getId(),
                message.getContent(),
                UserShortJson.fromEntity(message.getEvent().getCreator()),
                EventShortJson.fromEntity(message.getEvent()),
                comments);
    }

    public MessageJson(Long id, String content, UserShortJson user, EventShortJson event, List<CommentShortJson> comments) {
        setId(id);
        setContent(content);
        setUser(user);
        setEvent(event);
        setComments(comments);
    }

    public MessageJson() {
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UserShortJson getUser() {
        return user;
    }

    public EventShortJson getEvent() {
        return event;
    }

    public List<CommentShortJson> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(UserShortJson user) {
        this.user = user;
    }

    public void setEvent(EventShortJson event) {
        this.event = event;
    }

    public void setComments(List<CommentShortJson> comments) {
        this.comments = ListUtil.getNonNull(comments);
    }
}
