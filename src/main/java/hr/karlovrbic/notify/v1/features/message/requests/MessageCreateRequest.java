package hr.karlovrbic.notify.v1.features.message.requests;

import hr.karlovrbic.notify.v1.model.entity.Message;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Karlo Vrbic on 12.01.17..
 */
@XmlRootElement
public class MessageCreateRequest {

    static final String ATTRIBUTE_CONTENT = "content";

    @XmlElement(name = ATTRIBUTE_CONTENT, required = true)
    private String content;
    public MessageCreateRequest(String content) {
        this.content = content;
    }

    public MessageCreateRequest() {
    }

    public static MessageCreateRequest fromEntity(Message message) {
        return new MessageCreateRequest(message.getContent());
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

        MessageCreateRequest that = (MessageCreateRequest) o;

        return new EqualsBuilder()
                .append(content, that.content)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(content)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "MessageCreateRequest{" +
                "content='" + content + '\'' +
                '}';
    }
}
