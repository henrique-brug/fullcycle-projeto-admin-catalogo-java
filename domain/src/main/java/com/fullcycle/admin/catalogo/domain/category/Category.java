package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.AggregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final boolean aActive,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = aActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdatedAt;
        this.deletedAt = aDeletedAt;
    }

    public static Category newCategory(
            final String aName,
            final String aDescription,
            final boolean isActive) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Category(CategoryID.unique(), aName, aDescription, isActive, now, now, deletedAt);
    }

    public Category activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category deactivate() {
        if(getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category update(
            final String aName,
            final String aDescription,
            final boolean isActive) {
        if(isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();
        return this;
    }
    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public Category clone() {
        try {
            Category clone = (Category) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}