package io.codyn.app.template._shared.test;

import io.codyn.app.template.user.domain.PasswordHasher;

public class TestPasswordHasher implements PasswordHasher {

    @Override
    public String hash(String password) {
        return "test_hash-" + password;
    }
}
