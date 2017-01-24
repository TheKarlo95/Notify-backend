package hr.karlovrbic.notify.v1.features.message.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Karlo Vrbic on 24.01.17..
 */
@XmlRootElement
public class NotificationRequest {

    private static final String TOPIC_EVENT_FORMAT = "/topics/event-%d-%d";

    private static final String ATTRIBUTE_TO = "to";
    private static final String ATTRIBUTE_NOTIFICATION = "notification";

    @XmlElement(name = ATTRIBUTE_TO, required = true)
    private String to;
    @XmlElement(name = ATTRIBUTE_NOTIFICATION, required = true)
    private Notification notification;

    public NotificationRequest(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    public NotificationRequest(Long userId, Long eventId, String title, String body, String clickAction) {
        this(String.format(TOPIC_EVENT_FORMAT, userId, eventId),
                new Notification(title,
                        body,
                        clickAction));
    }

    public NotificationRequest() {
    }

    public String getTo() {
        return to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        NotificationRequest that = (NotificationRequest) o;

        return new EqualsBuilder()
                .append(to, that.to)
                .append(notification, that.notification)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(to)
                .append(notification)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "to='" + to + '\'' +
                ", notification=" + notification +
                '}';
    }

    @XmlRootElement
    public static class Notification {

        public static final String MAIN_CLICK_ACTION = "main";

        private static final String ATTRIBUTE_TITLE = "title";
        private static final String ATTRIBUTE_BODY = "body";
        private static final String ATTRIBUTE_CLICK_ACTION = "click_action";

        @XmlElement(name = ATTRIBUTE_TITLE, required = true)
        private String title;
        @XmlElement(name = ATTRIBUTE_BODY, required = true)
        private String body;
        @XmlElement(name = ATTRIBUTE_CLICK_ACTION)
        private String clickAction;

        public Notification(String title, String body, String clickAction) {
            this.title = title;
            this.body = body;
            this.clickAction = clickAction;
        }

        public Notification() {
        }

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Notification that = (Notification) o;

            return new EqualsBuilder()
                    .append(title, that.title)
                    .append(body, that.body)
                    .append(clickAction, that.clickAction)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(title)
                    .append(body)
                    .append(clickAction)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    ", clickAction='" + clickAction + '\'' +
                    '}';
        }
    }
}
