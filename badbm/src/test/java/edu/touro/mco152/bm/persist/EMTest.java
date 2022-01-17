package edu.touro.mco152.bm.persist;

import jakarta.persistence.EntityManager;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EMTest {

    /**
     * Right
     * Conformance
     */
    @Test
    void getEntityManager(){

        EntityManager em = null;
        em = EM.getEntityManager();
        assertNotNull(em);
        assertTrue(em instanceof EntityManager);
    }
}
