package hr.karlovrbic.notify.v1.features.event.response;

import hr.karlovrbic.notify.v1.formatter.DateAdapter;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.json.shortened.MessageShortJson;
import hr.karlovrbic.notify.v1.model.json.shortened.UserShortJson;
import hr.karlovrbic.notify.v1.utils.ListUtil;

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
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date createdAt;
    @XmlElement(name = ATTRIBUTE_PICTURE)
    private String pictureLink;
    @XmlElement(name = ATTRIBUTE_SUBSCRIBERS, required = true)
    private List<UserShortJson> subscribers;
    @XmlElement(name = ATTRIBUTE_MESSAGES, required = true)
    private List<MessageShortJson> messages;

    private EventResponse(Long id,
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

    public EventResponse() {
    }

    public static EventResponse fromEntity(Event event) {
        UserShortJson creator = UserShortJson.fromEntity(event.getCreator());
        List<UserShortJson> subscribers = event.getSubscribedUsers().stream()
                .map(UserShortJson::fromEntity)
                .collect(Collectors.toList());
        List<MessageShortJson> messages = event.getMessages().stream()
                .map(MessageShortJson::fromEntity)
                .collect(Collectors.toList());

        return new EventResponse(event.getId(),
                creator,
                event.getTitle(),
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

    public UserShortJson getCreator() {
        return creator;
    }

    public void setCreator(UserShortJson creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<UserShortJson> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<UserShortJson> subscribers) {
        this.subscribers = ListUtil.getNonNull(subscribers);
    }

    public List<MessageShortJson> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageShortJson> messages) {
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
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", pictureLink='" + pictureLink + '\'' +
                ", subscribers=" + subscribers +
                ", messages=" + messages +
                '}';
    }
}
