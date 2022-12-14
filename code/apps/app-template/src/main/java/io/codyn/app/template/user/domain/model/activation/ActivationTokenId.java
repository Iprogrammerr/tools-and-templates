package io.codyn.app.template.user.domain.model.activation;

import java.util.UUID;

public record ActivationTokenId(UUID userId, ActivationTokenType tokenType) {


    public static ActivationTokenId ofNewEmail(UUID userId) {
        return new ActivationTokenId(userId, ActivationTokenType.NEW_EMAIL);
    }

    public static ActivationTokenId ofNewUser(UUID userId) {
        return new ActivationTokenId(userId, ActivationTokenType.NEW_USER);
    }

    public static ActivationTokenId ofPasswordReset(UUID userId) {
        return new ActivationTokenId(userId, ActivationTokenType.PASSWORD_RESET);
    }
}
