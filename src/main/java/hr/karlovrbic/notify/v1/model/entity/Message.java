package hr.karlovrbic.notify.v1.model.entity;

import hr.karlovrbic.notify.v1.DB;
import hr.karlovrbic.notify.v1.utils.ListUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Karlo Vrbic on 31.10.16..
 */
@Entity
@Table(name = DB.TABLE_MESSAGE)
@NamedQueries({
        @NamedQuery(name = "Message.selectByEventId",
                query = "select e from Message as e where e.event.id=:eventId",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
})
public class Message implements Serializable {

    static final String COLUMN_ID = "id";
    static final String COLUMN_CONTENT = "content";
    static final String COLUMN_EVENT = "event";
    static final String COLUMN_COMMENTS = "comments";

    private Long id;
    private String content;
    private Event event;
    private List<Comment> comments;

    public Message(Long id,
                   String content,
                   Event event,
                   List<Comment> comments) {
        setId(id);
        setContent(content);
        setEvent(event);
        setComments(comments);
    }

    public Message(String content,
                   Event event,
                   List<Comment> comments) {
        setId(id);
        setContent(content);
        setEvent(event);
        setComments(comments);
    }

    public Message() {
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

    @Column(name = COLUMN_CONTENT, length = 1024, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_EVENT, nullable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event messageAndEvents) {
        this.event = messageAndEvents;
    }

    @OneToMany(mappedBy = Comment.COLUMN_MESSAGE, cascade = CascadeType.PERSIST, orphanRemoval = true)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = ListUtil.getNonNull(comments);
    }
}
