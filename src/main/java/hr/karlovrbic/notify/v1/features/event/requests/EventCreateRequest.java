package hr.karlovrbic.notify.v1.features.event.requests;

import hr.karlovrbic.notify.v1.formatter.DateAdapter;
import hr.karlovrbic.notify.v1.model.entity.Event;
import hr.karlovrbic.notify.v1.model.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by Karlo Vrbic on 04.11.16..
 */
@XmlRootElement
public class EventCreateRequest {

    private static final String ATTRIBUTE_CREATOR = "creator";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_DATE = "date";
    private static final String ATTRIBUTE_DESCRIPTION = "description";

    @XmlElement(name = ATTRIBUTE_CREATOR, required = true)
    private UserShortJson creator;
    @XmlElement(name = ATTRIBUTE_TITLE, required = true)
    private String title;
    @XmlElement(name = ATTRIBUTE_DATE, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;
    @XmlElement(name = ATTRIBUTE_DESCRIPTION, required = true)
    private String description;

    private EventCreateRequest(UserShortJson creator,
                               String title,
                               String description) {
        this.creator = creator;
        this.title = title;
        this.description = description;
    }

    public EventCreateRequest() {
    }

    public static EventCreateRequest fromEntity(Event event) {
        UserShortJson creatorJson = null;
        User creator = event.getCreator();
        if (creator != null) {
            creatorJson = new UserShortJson(creator.getId());
        }

        return new EventCreateRequest(creatorJson,
                event.getTitle(),
                event.getDescription());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EventCreateRequest that = (EventCreateRequest) o;

        return new EqualsBuilder()
                .append(creator, that.creator)
                .append(title, that.title)
                .append(date, that.date)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(creator)
                .append(title)
                .append(date)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "EventCreateRequest{" +
                "creator=" + creator +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    public static class UserShortJson {

        static final String ATTRIBUTE_ID = "id";

        @XmlElement(name = ATTRIBUTE_ID, required = true)
        private Long id;

        private UserShortJson(Long id) {
            this.id = id;
        }

        public UserShortJson() {
        }

        public static UserShortJson fromEntity(User user) {
            if (user == null) {
                return null;
            } else {
                return new UserShortJson(user.getId());
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            UserShortJson that = (UserShortJson) o;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "UserShortResponse{id=" + id + '}';
        }
    }
}
