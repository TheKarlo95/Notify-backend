package hr.karlovrbic.notify.v1.features.comment.interactors;

import hr.karlovrbic.notify.v1.dao.manager.JPAEMProvider;
import hr.karlovrbic.notify.v1.features.comment.IComment;
import hr.karlovrbic.notify.v1.features.comment.requests.CommentCreateRequest;
import hr.karlovrbic.notify.v1.model.entity.Comment;
import hr.karlovrbic.notify.v1.model.entity.Message;
import hr.karlovrbic.notify.v1.model.entity.User;
import hr.karlovrbic.notify.v1.model.json.CommentJson;

import javax.persistence.EntityManager;

/**
 * Created by Karlo Vrbic on 03.11.16..
 */
public class CommentCreateInteractor implements IComment.CreateInteractor {

    private static Comment toEntity(EntityManager em, CommentCreateRequest request) {
        User creator = em.getReference(User.class, request.getCreator().getId());
        Message message = em.getReference(Message.class, request.getMessage().getId());

        return new Comment(request.getContent(),
                message,
                creator);
    }

    @Override
    public CommentJson create(CommentCreateRequest request) {
        EntityManager em = JPAEMProvider.getEntityManager();
        Comment comment = toEntity(em, request);
        em.persist(comment);

        CommentJson commentJson = comment.toJson();

        JPAEMProvider.close();
        return commentJson;
    }
}
