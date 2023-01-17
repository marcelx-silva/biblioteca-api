package com.newgo.bibliotecaapi.config;

import com.newgo.bibliotecaapi.model.EntityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * Configuration class to enable JPA auditing.
 * @author Thiago Marcelo*/
@Configuration
@EnableJpaAuditing
public class AuditConfig {
    /**
     * Bean to inform which user is making modifications to persistence entities.
     * @see EntityAuditorAware
     * @return the current user
     * @author Thiago Marcelo
     * */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }
}
