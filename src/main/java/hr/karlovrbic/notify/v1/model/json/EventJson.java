package hr.karlovrbic.notify.v1.model.json;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.json.DateDeserializer;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.json.shortened.MessageShortJson;
import hr.karlovrbic.notify.v1.model.json.shortened.UserShortJson;
import hr.karlovrbic.notify.v1.utils.ListUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
@XmlRootElement
public class EventJson {

    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_CREATOR = "creator";
    static final String ATTRIBUTE_TITLE = "title";
    static final String ATTRIBUTE_DESCRIPTION = "password";
    static final String ATTRIBUTE_CREATED_AT = "created_at";
    static final String ATTRIBUTE_PICTURE = "picture_link";
    static final String ATTRIBUTE_SUBSCRIBERS = "subscribers";
    static final String ATTRIBUTE_MESSAGES = "messages";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_CREATOR, required = true)
    private UserShortJson creator;
    @XmlElement(name = ATTRIBUTE_TITLE, required = true)
    private String title;
    @XmlElement(name = ATTRIBUTE_DESCRIPTION, required = true)
    private String description;
    @XmlElement(name = ATTRIBUTE_CREATED_AT, required = true)
    private Date createdAt;
    @XmlElement(name = ATTRIBUTE_PICTURE)
    private String pictureLink;
    @XmlElement(name = ATTRIBUTE_SUBSCRIBERS, required = true)
    private List<UserShortJson> subscribers;
    @XmlElement(name = ATTRIBUTE_MESSAGES, required = true)
    private List<MessageShortJson> messages;

    public static EventJson fromEntity(Event event) {
        UserShortJson creator = UserShortJson.fromEntity(event.getCreator());
        List<UserShortJson> subscribers = event.getSubscribedUsers().stream()
                .map(UserShortJson::fromEntity)
                .collect(Collectors.toList());
        List<MessageShortJson> messages = event.getMessages().stream()
                .map(MessageShortJson::fromEntity)
                .collect(Collectors.toList());

        return new EventJson(event.getId(),
                creator,
                event.getTitle(),
                event.getDescription(),
                event.getCreatedAt(),
                event.getPictureLink(),
                subscribers,
                messages);
    }

    private EventJson(Long id,
                      UserShortJson creator,
                      String title,
                      String description,
                      Date createdAt,
                      String pictureLink,
                      List<UserShortJson> subscribers,
                      List<MessageShortJson> messages) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.pictureLink = pictureLink;
        this.subscribers = ListUtil.getNonNull(subscribers);
        this.messages = ListUtil.getNonNull(messages);
    }

    public EventJson() {
    }

    public Long getId() {
        return id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public List<UserShortJson> getSubscribers() {
        return subscribers;
    }

    public List<MessageShortJson> getMessages() {
        return messages;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public void setSubscribers(List<UserShortJson> subscribers) {
        this.subscribers = ListUtil.getNonNull(subscribers);
    }

    public void setMessages(List<MessageShortJson> messages) {
        this.messages = ListUtil.getNonNull(messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventJson eventJson = (EventJson) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, eventJson.id)
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
