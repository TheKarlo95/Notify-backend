package hr.karlovrbic.notify.v1.model.json;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.json.DateDeserializer;
import hr.karlovrbic.notify.v1.model.json.shortened.MessageShortJson;
import hr.karlovrbic.notify.v1.model.entity.Comment;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * Created by Karlo Vrbic on 16.12.16..
 */
public class CommentJson {

    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_CONTENT = "content";
    static final String ATTRIBUTE_MESSAGE = "message";

    @XmlElement(name = ATTRIBUTE_ID)
    private Long id;
    @XmlElement(name = ATTRIBUTE_CONTENT, required = true)
    private String content;
    @XmlElement(name = ATTRIBUTE_MESSAGE, required = true)
    private MessageShortJson message;

    public static CommentJson fromEntity(Comment comment) {
        return new CommentJson(comment.getId(),
                comment.getContent(),
                MessageShortJson.fromEntity(comment.getMessage()));
    }

    private CommentJson(Long id, String content, MessageShortJson message) {
        this.id = id;
        this.content = content;
        this.message = message;
    }

    public CommentJson() {
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public MessageShortJson getMessage() {
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessage(MessageShortJson message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CommentJson that = (CommentJson) o;

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
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .setPrettyPrinting()
                .create().toJson(this);
    }
}
