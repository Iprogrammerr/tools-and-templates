package io.codyn.app.template.user.domain.model;

import java.util.UUID;

public record User(UUID id,
                   String name,
                   String email,
                   String password,
                   UserState state) {
}
