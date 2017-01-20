package hr.karlovrbic.notify.v1.model.entity;

import hr.karlovrbic.notify.v1.DB;
import hr.karlovrbic.notify.v1.model.Privacy;
import hr.karlovrbic.notify.v1.model.json.UserJson;
import hr.karlovrbic.notify.v1.utils.ListUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by thekarlo95 on 10/29/16.
 */
@Entity
@Table(name = DB.TABLE_USER)
@NamedQueries({
        @NamedQuery(name = "User.selectAll", query = "select u from User as u",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        @NamedQuery(name = "User.selectByUsername", query = "select u from User as u where u.username=:username",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
})
public class User implements Serializable {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_PROFILE_PICTURE = "profile_picture_link";

    private Long id;
    private String token;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Date birthDay;
    private Date createdAt;
    private String profilePictureLink;
    private ProfileConfiguration profileConfiguration;
    private List<Event> createdEvents;
    private List<Event> subscribedEvents;
    private List<Comment> comments;


    public User(Long id,
                String username,
                String password,
                String email,
                String name,
                String surname,
                Date birthDay,
                Date createdAt,
                ProfileConfiguration profileConfiguration,
                String profilePictureLink,
                List<Event> createdEvents,
                List<Event> subscribedEvents,
                List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.profilePictureLink = profilePictureLink;
        if (profileConfiguration != null) {
            this.profileConfiguration = profileConfiguration;
        } else {
            this.profileConfiguration = new ProfileConfiguration(Privacy.PUBLIC);
        }
        this.createdEvents = ListUtil.getNonNull(createdEvents);
        this.subscribedEvents = ListUtil.getNonNull(subscribedEvents);
        this.comments = ListUtil.getNonNull(comments);
    }

    public User(String username,
                String password,
                String email,
                String name,
                String surname,
                Date birthDay,
                Date createdAt,
                ProfileConfiguration profileConfiguration,
                String profilePictureLink,
                List<Event> createdEvents,
                List<Event> subscribedEvents,
                List<Comment> comments) {
        this(null,
                username,
                password,
                email,
                name,
                surname,
                birthDay,
                createdAt,
                profileConfiguration,
                profilePictureLink,
                createdEvents,
                subscribedEvents,
                comments);
    }

    public User(String username,
                String password,
                String email,
                String name,
                String surname,
                Date birthDay,
                ProfileConfiguration profileConfiguration,
                String profilePictureLink,
                List<Event> createdEvents,
                List<Event> subscribedEvents,
                List<Comment> comments) {
        this(null,
                username,
                password,
                email,
                name,
                surname,
                birthDay,
                new Date(),
                profileConfiguration,
                profilePictureLink,
                createdEvents,
                subscribedEvents,
                comments);
    }

    public User(String username,
                String password,
                String email,
                String name,
                String surname,
                Date birthDay,
                ProfileConfiguration profileConfiguration,
                String profilePictureLink) {
        this(null,
                username,
                password,
                email,
                name,
                surname,
                birthDay,
                new Date(),
                profileConfiguration,
                profilePictureLink,
                null,
                null,
                null);
    }

    public User() {
    }

    public UserJson toJson() {
        return UserJson.fromEntity(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Column(name = COLUMN_USERNAME, length = 50, unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Column(name = COLUMN_PASSWORD, length = 50, nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = COLUMN_EMAIL, length = 254, unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = COLUMN_NAME, length = 50, nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = COLUMN_SURNAME, length = 50, nullable = false)
    public String getSurname() {
        return surname;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = COLUMN_BIRTHDAY, nullable = false)
    public Date getBirthDay() {
        return birthDay;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = COLUMN_CREATED_AT, nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    @Column(name = COLUMN_PROFILE_PICTURE, length = 50)
    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    @Embedded
    public ProfileConfiguration getProfileConfiguration() {
        return profileConfiguration;
    }

    @OneToMany(mappedBy = Event.COLUMN_CREATOR, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @OrderBy(Event.COLUMN_CREATED_AT)
    public List<Event> getCreatedEvents() {
        return createdEvents;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = DB.TABLE_USER_EVENT,
            catalog = DB.DATABASE_NAME,
            joinColumns = {@JoinColumn(name = DB.TABLE_USER + "_" + User.COLUMN_ID,
                    referencedColumnName = User.COLUMN_ID,
                    nullable = false,
                    updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = DB.TABLE_EVENT + "_" + Event.COLUMN_ID,
                    referencedColumnName = User.COLUMN_ID,
                    nullable = false,
                    updatable = false)})
    public List<Event> getSubscribedEvents() {
        return subscribedEvents;
    }

    @OneToMany(mappedBy = Comment.COLUMN_CREATOR)
    public List<Comment> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public void setProfileConfiguration(ProfileConfiguration profileProfileConfiguration) {
        this.profileConfiguration = profileProfileConfiguration;
    }

    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public void setSubscribedEvents(List<Event> subscribedEvents) {
        this.subscribedEvents = subscribedEvents;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
