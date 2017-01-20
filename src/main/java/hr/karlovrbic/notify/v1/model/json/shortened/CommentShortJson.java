package hr.karlovrbic.notify.v1.model.json.shortened;

import com.google.gson.GsonBuilder;
import hr.karlovrbic.notify.v1.model.entity.Comment;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.Hibernate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentShortJson {

    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_USERNAME = "content";

    @XmlElement(name = ATTRIBUTE_ID, required = true)
    private Long id;
    @XmlElement(name = ATTRIBUTE_USERNAME, required = true)
    private String content;

    public static CommentShortJson fromEntity(Comment comment) {
        if (comment == null) {
            return null;
        } else {
            if(!Hibernate.isInitialized(comment)){
                Hibernate.initialize(comment);
            }
            return new CommentShortJson(comment.getId(), comment.getContent());
        }
    }

    private CommentShortJson(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public CommentShortJson() {
        this(null, null);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CommentShortJson that = (CommentShortJson) o;

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