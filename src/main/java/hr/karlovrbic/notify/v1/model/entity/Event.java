package hr.karlovrbic.notify.v1.model.entity;

import hr.karlovrbic.notify.v1.DB;
import hr.karlovrbic.notify.v1.model.json.EventJson;
import hr.karlovrbic.notify.v1.utils.ListUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by thekarlo95 on 10/30/16.
 */
@Entity
@Table(name = DB.TABLE_EVENT)
@NamedQueries({
        @NamedQuery(name = "Event.selectAll", query = "select e from Event as e",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        @NamedQuery(name = "Event.selectByCreatorId", query = "select e from Event as e where e.creator.id=:creatorId",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
})
public class Event implements Serializable {

    static final String COLUMN_ID = "id";
    static final String COLUMN_CREATOR = "creator";
    static final String COLUMN_TITLE = "title";
    static final String COLUMN_DESCRIPTION = "password";
    static final String COLUMN_CREATED_AT = "created_at";
    static final String COLUMN_PICTURE = "picture_link";

    private Long id;
    private User creator;
    private String title;
    private String description;
    private Date createdAt;
    private String pictureLink;
    private List<User> subscribedUsers;
    private List<Message> messages;

    public Event(Long id,
                 User creator,
                 String title,
                 String description,
                 Date createdAt,
                 String pictureLink,
                 List<User> subscribedUsers,
                 List<Message> messages) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.pictureLink = pictureLink;
        this.subscribedUsers = ListUtil.getNonNull(subscribedUsers);
        this.messages = ListUtil.getNonNull(messages);
    }

    public Event(User creator,
                 String title,
                 String description,
                 Date createdAt,
                 String pictureLink,
                 List<User> subscribedUsers,
                 List<Message> messages) {
        this(null, creator, title, description, createdAt, pictureLink, subscribedUsers, messages);
    }

    public Event(User creator,
                 String title,
                 String description,
                 String pictureLink,
                 List<User> subscribedUsers,
                 List<Message> messages) {
        this(null, creator, title, description, new Date(), pictureLink, subscribedUsers, messages);
    }

    public Event() {
    }

    public EventJson toJson() {
        return EventJson.fromEntity(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_CREATOR, nullable = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    @Column(name = COLUMN_TITLE, length = 50, unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    @Column(name = COLUMN_DESCRIPTION, length = 1024, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = COLUMN_CREATED_AT, nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = COLUMN_PICTURE, length = 50)
    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    @ManyToMany(mappedBy = "subscribedEvents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(List<User> subscribedUsers) {
        this.subscribedUsers = ListUtil.getNonNull(subscribedUsers);
    }

    @OneToMany(mappedBy = Message.COLUMN_EVENT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
