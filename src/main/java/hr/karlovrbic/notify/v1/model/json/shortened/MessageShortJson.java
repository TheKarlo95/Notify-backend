package hr.karlovrbic.notify.v1.model.json.shortened;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.model.entity.Message;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Karlo Vrbic on 16.12.16..
 */
public class MessageShortJson {

    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_USERNAME = "content";

    @XmlElement(name = ATTRIBUTE_ID, required = true)
    private Long id;
    @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
    private String content;

    private MessageShortJson(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public MessageShortJson() {
    }

    public static MessageShortJson fromEntity(Message message) {
        if (message == null) {
            return null;
        } else {
            return new MessageShortJson(message.getId(), message.getContent());
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
        return new GsonBuilder()
                .setPrettyPrinting()
                .create().toJson(this);
    }
}
