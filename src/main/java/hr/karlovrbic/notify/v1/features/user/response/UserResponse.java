package hr.karlovrbic.notify.v1.features.user.response;

import hr.karlovrbic.notify.v1.formatter.DateAdapter;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 21.01.17..
 */
@XmlRootElement
public class UserResponse {

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_TOKEN = "fcm_token";
    private static final String ATTRIBUTE_USERNAME = "username";
    private static final String ATTRIBUTE_EMAIL = "email";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SURNAME = "surname";
    private static final String ATTRIBUTE_BIRTHDAY = "birthday";
    private static final String ATTRIBUTE_CREATED_AT = "created_at";
    private static final String ATTRIBUTE_PROFILE_PICTURE = "profile_picture_link";
    private static final String ATTRIBUTE_EVENTS = "events";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_TOKEN)
    private String token;
    @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
    private String username;
    @XmlElement(name = ATTRIBUTE_EMAIL, required = true)
    private String email;
    @XmlElement(name = ATTRIBUTE_NAME, required = true)
    private String name;
    @XmlElement(name = ATTRIBUTE_SURNAME, required = true)
    private String surname;
    @XmlElement(name = ATTRIBUTE_BIRTHDAY, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDay;
    @XmlElement(name = ATTRIBUTE_CREATED_AT)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date createdAt;
    @XmlElement(name = ATTRIBUTE_PROFILE_PICTURE)
    private String profilePictureLink;
    @XmlElement(name = ATTRIBUTE_EVENTS)
    private List<EventResponse> events;

    private UserResponse(Long id,
                         String token,
                         String username,
                         String email,
                         String name,
                         String surname,
                         Date birthDay,
                         Date createdAt,
                         String profilePictureLink,
                         List<EventResponse> events) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.createdAt = createdAt;
        this.profilePictureLink = profilePictureLink;
        this.events = events;
    }

    public UserResponse() {
    }

    public static UserResponse fromEntity(User user) {
        List<EventResponse> events = user.getCreatedEvents()
                .stream()
                .map(EventResponse::fromEntity)
                .collect(Collectors.toList());

        return new UserResponse(user.getId(),
                user.getToken(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getBirthDay(),
                user.getCreatedAt(),
                user.getProfilePictureLink(),
                events);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public List<EventResponse> getEvents() {
        return events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserResponse userResponse = (UserResponse) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, userResponse.id)
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
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                ", createdAt=" + createdAt +
                ", profilePictureLink='" + profilePictureLink + '\'' +
                ", events=" + events +
                '}';
    }

    static class EventResponse {

        private static final String ATTRIBUTE_ID = "id";
        private static final String ATTRIBUTE_TITLE = "title";

        @XmlElement(name = ATTRIBUTE_ID)
        private Long id;
        @XmlElement(name = ATTRIBUTE_TITLE)
        private String title;

        public EventResponse(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public EventResponse() {
        }

        public static EventResponse fromEntity(Event event) {
            return new EventResponse(event.getId(), event.getTitle());
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            EventResponse eventResponse = (EventResponse) o;

            return new org.apache.commons.lang3.builder.EqualsBuilder()
                    .append(id, eventResponse.id)
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
            return "EventShortResponse{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}