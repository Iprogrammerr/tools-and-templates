/*
 * This file is generated by jOOQ.
 */
package io.codyn.sqldb.schema.project.tables.records;


import io.codyn.sqldb.schema.project.tables.ProjectUser;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProjectUserRecord extends UpdatableRecordImpl<ProjectUserRecord> implements Record2<UUID, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>project.project_user.project_id</code>.
     */
    public ProjectUserRecord setProjectId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>project.project_user.project_id</code>.
     */
    public UUID getProjectId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>project.project_user.user_id</code>.
     */
    public ProjectUserRecord setUserId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>project.project_user.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<UUID, UUID> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, UUID> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<UUID, UUID> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return ProjectUser.PROJECT_USER.PROJECT_ID;
    }

    @Override
    public Field<UUID> field2() {
        return ProjectUser.PROJECT_USER.USER_ID;
    }

    @Override
    public UUID component1() {
        return getProjectId();
    }

    @Override
    public UUID component2() {
        return getUserId();
    }

    @Override
    public UUID value1() {
        return getProjectId();
    }

    @Override
    public UUID value2() {
        return getUserId();
    }

    @Override
    public ProjectUserRecord value1(UUID value) {
        setProjectId(value);
        return this;
    }

    @Override
    public ProjectUserRecord value2(UUID value) {
        setUserId(value);
        return this;
    }

    @Override
    public ProjectUserRecord values(UUID value1, UUID value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProjectUserRecord
     */
    public ProjectUserRecord() {
        super(ProjectUser.PROJECT_USER);
    }

    /**
     * Create a detached, initialised ProjectUserRecord
     */
    public ProjectUserRecord(UUID projectId, UUID userId) {
        super(ProjectUser.PROJECT_USER);

        setProjectId(projectId);
        setUserId(userId);
    }
}
