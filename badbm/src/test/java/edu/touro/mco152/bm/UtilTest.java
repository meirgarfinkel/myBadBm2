package edu.touro.mco152.bm;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import static edu.touro.mco152.bm.Util.*;
import static edu.touro.mco152.bm.persist.EM.getEntityManager;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

class UtilTest {

    /**
     * cross checking
     */
    @Test
    void deleteDirectoryTest() {
        File doc = new File("/newTestDoc/java");
        System.out.println(doc.mkdirs());

        deleteDirectory(doc);

        assertTrue(doc.mkdirs());
    }

    /**
     * error condition
     */
    @Test
    void errorConditionTest(){
        File doc = null;
        assertThrows(NullPointerException.class, () -> deleteDirectory(doc));
    }

    /**
     * boundary
     * range
     */
    @ParameterizedTest
    @ValueSource(ints= { 3, 6, 8 })
    void randIntTest(int args) throws InterruptedException {
        int min = 1;

        int randNum = randInt(min, args);

        assertTrue(randNum >= min && randNum <= args);
    }

    /**
     * right
     */
    @Test
    void getEntityManagerTest(){
        EntityManager em = null;

        em = getEntityManager();

        assertNotNull(em, "em is not null");
    }

    /**
     * performance
     */
    @Test
    void randIntTimerTest(){
        assertTimeout(ofMillis(1000), () -> {
            for (int i = 0; i < 1000; i++) {
                randInt(1, Integer.MAX_VALUE);
            }
        });
    }
}