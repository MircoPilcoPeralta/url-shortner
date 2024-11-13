package org.example.shortenerApi.interceptor;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.example.shortenerApi.config.JpaEntityManager;

import java.util.logging.Logger;

@EntityManagerInterceptorBinding
@Interceptor
public class EntityManagerInterceptor {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    @JpaEntityManager
    private EntityManager em;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ctx) throws Exception {
        try {
            logger.info("Inicia transacción");
            em.getTransaction().begin();
            Object execution = ctx.proceed();
            em.getTransaction().commit();
            logger.info("Finaliza la transacción");
            return execution;
        }
        catch (Exception e) {
            logger.info("Error en la transacción, rollback");
            em.getTransaction().rollback();
            throw e;
        }
    }
}
