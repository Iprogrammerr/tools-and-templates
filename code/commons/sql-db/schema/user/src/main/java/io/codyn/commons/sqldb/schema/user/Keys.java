/*
 * This file is generated by jOOQ.
 */
package io.codyn.commons.sqldb.schema.user;


import io.codyn.commons.sqldb.schema.user.tables.ActivationToken;
import io.codyn.commons.sqldb.schema.user.tables.User;
import io.codyn.commons.sqldb.schema.user.tables.records.ActivationTokenRecord;
import io.codyn.commons.sqldb.schema.user.tables.records.UserRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * user.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ActivationTokenRecord> ACTIVATION_TOKEN_PKEY = Internal.createUniqueKey(ActivationToken.ACTIVATION_TOKEN, DSL.name("activation_token_pkey"), new TableField[] { ActivationToken.ACTIVATION_TOKEN.ID, ActivationToken.ACTIVATION_TOKEN.TYPE }, true);
    public static final UniqueKey<ActivationTokenRecord> ACTIVATION_TOKEN_TOKEN_KEY = Internal.createUniqueKey(ActivationToken.ACTIVATION_TOKEN, DSL.name("activation_token_token_key"), new TableField[] { ActivationToken.ACTIVATION_TOKEN.TOKEN }, true);
    public static final UniqueKey<UserRecord> USER_EMAIL_KEY = Internal.createUniqueKey(User.USER, DSL.name("user_email_key"), new TableField[] { User.USER.EMAIL }, true);
    public static final UniqueKey<UserRecord> USER_PKEY = Internal.createUniqueKey(User.USER, DSL.name("user_pkey"), new TableField[] { User.USER.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ActivationTokenRecord, UserRecord> ACTIVATION_TOKEN__ACTIVATION_TOKEN_ID_FKEY = Internal.createForeignKey(ActivationToken.ACTIVATION_TOKEN, DSL.name("activation_token_id_fkey"), new TableField[] { ActivationToken.ACTIVATION_TOKEN.ID }, Keys.USER_PKEY, new TableField[] { User.USER.ID }, true);
}