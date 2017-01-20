package hr.karlovrbic.notify.v1.model.json.shortened;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.model.entity.Event;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;

public class EventShortJson {

    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_NAME = "name";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_NAME, required = true)
    private String name;

    public static EventShortJson fromEntity(Event event) {
        if (event == null) {
            return null;
        } else {
            return new EventShortJson(event.getId(), event.getTitle());
        }
    }

    private EventShortJson(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventShortJson() {
        this(null, null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String username) {
        this.name = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EventShortJson that = (EventShortJson) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create().toJson(this);
    }
}