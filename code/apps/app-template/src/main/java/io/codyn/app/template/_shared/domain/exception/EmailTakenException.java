package io.codyn.app.template._shared.domain.exception;

public class EmailTakenException extends AppException {

    public EmailTakenException(String email) {
        super("%s email is taken".formatted(email));
    }
}
