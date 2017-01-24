package hr.karlovrbic.notify.v1.features.user.requests;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Karlo Vrbic on 24.01.17..
 */
@XmlRootElement
public class TokenUpdateRequest {

    private static final String ATTRIBUTE_TOKEN = "fcm_token";

    @XmlElement(name = ATTRIBUTE_TOKEN, required = true)
    private String token;

    public TokenUpdateRequest(String token) {
        this.token = token;
    }

    public TokenUpdateRequest() {
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TokenUpdateRequest that = (TokenUpdateRequest) o;

        return new EqualsBuilder()
                .append(token, that.token)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(token)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "TokenUpdateRequest{" +
                "token='" + token + '\'' +
                '}';
    }
}
