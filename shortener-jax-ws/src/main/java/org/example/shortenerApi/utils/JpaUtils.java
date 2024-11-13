package org.example.shortenerApi.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
   private static String PERSISTENCE_UNIT_NAME = "default";

   public static EntityManager getEntityManager() {
       final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       return emf.createEntityManager();
   }

}
