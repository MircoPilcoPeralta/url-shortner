package org.example.shortenerApi.config;


import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import org.example.shortenerApi.utils.JpaUtils;


@Dependent
public class BeansConfig {

    @Produces
    @RequestScoped
    @JpaEntityManager
    public EntityManager entityManager() {
        return JpaUtils.getEntityManager();
    }

    public void closeEntityManager(@Disposes @JpaEntityManager EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
