package hr.karlovrbic.notify.v1.model.entity;

import hr.karlovrbic.notify.v1.DB;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

import javax.persistence.*;

/**
 * Created by Karlo Vrbic on 31.10.16..
 */
@Entity
@Table(name = DB.TABLE_COMMENT)
@NamedQueries({
        @NamedQuery(name = "Comment.selectByMessageId",
                query = "select e from Comment as e where e.message.id=:messageId",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
})
public class Comment {

    static final String COLUMN_ID = "id";
    static final String COLUMN_CONTENT = "content";
    static final String COLUMN_MESSAGE = "message";
    static final String COLUMN_CREATOR = "creator";

    private Long id;
    private String content;
    private Message message;
    private User creator;

    public Comment(Long id, String content, Message message, User creator) {
        this.id = id;
        this.content = content;
        this.message = message;
        this.creator = creator;
    }

    public Comment(String content, Message message, User creator) {
        this(null, content, message, creator);
    }

    public Comment() {
        this(null, null, null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    public Long getId() {
        return id;
    }

    @Column(name = COLUMN_CONTENT, length = 1024, nullable = false)
    public String getContent() {
        return content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_MESSAGE, nullable = false)
    public Message getMessage() {
        return message;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_CREATOR, nullable = false)
    public User getCreator() {
        return creator;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public CommentJson toJson() {
        return CommentJson.fromEntity(this);
    }
}
