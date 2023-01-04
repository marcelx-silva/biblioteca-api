package com.newgo.bibliotecaapi.model;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Concrete class to represent the user who modified an entity.
 * @see com.newgo.bibliotecaapi.config.AuditConfig*/
public class EntityAuditorAware implements AuditorAware<String> {
    /**
     * Method to get the current Auditor, an user.
     * @return The current user
     * @author Thiago Marcelo*/
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Administrator");
    }
}
