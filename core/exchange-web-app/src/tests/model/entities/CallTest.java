package model.entities;

import com.google.common.io.ByteProcessor;
import com.google.common.io.Files;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class CallTest {
    protected static EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("em");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    @Before
    public void beginTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Call").executeUpdate();
    }

    @After
    public void rollbackTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    public void addRemoveCall() {
        Call call = new Call("111", 1);
        em.persist(call);
        assertTrue(em.contains(call));

        Query query = em.createNamedQuery("Call.getAll");
        List<Call> calls = (List<Call>)query.getResultList();
        assertEquals(1, calls.size());
        Call dbCall = calls.get(0);
        assertNotNull(dbCall);
        assertSame(call, dbCall);
        em.remove(call);
    }
}