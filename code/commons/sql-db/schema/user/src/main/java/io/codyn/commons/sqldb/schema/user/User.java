/*
 * This file is generated by jOOQ.
 */
package io.codyn.commons.sqldb.schema.user;


import io.codyn.commons.sqldb.schema.user.tables.ActivationToken;
import io.codyn.commons.sqldb.schema.user.tables.Role;
import io.codyn.commons.sqldb.schema.user.tables.SecondFactorAuthentication;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>user</code>
     */
    public static final User USER_SCHEMA = new User();

    /**
     * The table <code>user.activation_token</code>.
     */
    public final ActivationToken ACTIVATION_TOKEN = ActivationToken.ACTIVATION_TOKEN;

    /**
     * The table <code>user.role</code>.
     */
    public final Role ROLE = Role.ROLE;

    /**
     * The table <code>user.second_factor_authentication</code>.
     */
    public final SecondFactorAuthentication SECOND_FACTOR_AUTHENTICATION = SecondFactorAuthentication.SECOND_FACTOR_AUTHENTICATION;

    /**
     * The table <code>user.user</code>.
     */
    public final io.codyn.commons.sqldb.schema.user.tables.User USER = io.codyn.commons.sqldb.schema.user.tables.User.USER;

    /**
     * No further instances allowed
     */
    private User() {
        super("user", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            ActivationToken.ACTIVATION_TOKEN,
            Role.ROLE,
            SecondFactorAuthentication.SECOND_FACTOR_AUTHENTICATION,
            io.codyn.commons.sqldb.schema.user.tables.User.USER);
    }
}
