package hr.karlovrbic.notify.v1.features.comment.interactors;

import com.sun.istack.internal.NotNull;
import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.comment.IComment;
import hr.karlovrbic.notify.v1.model.entity.Comment;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class CommentByMessageIdInteractor implements IComment.GetByMessageIdInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public List<CommentJson> get(@NotNull Long eventId) {
        List<Comment> comments = JPAEMProvider.getEntityManager()
                .createNamedQuery("Comment.selectByMessageId")
                .setParameter("messageId", eventId)
                .getResultList();

        List<CommentJson> commentJsons = null;
        if (comments != null && !comments.isEmpty()) {
            commentJsons = comments.stream()
                    .map(Comment::toJson)
                    .collect(Collectors.toList());
        }

        JPAEMProvider.close();
        return commentJsons;
    }
}
