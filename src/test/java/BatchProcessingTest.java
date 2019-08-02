import junit.framework.TestCase;
import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pojoclasses.Player;
import pojoclasses.SkillLevel;

@RunWith(JUnit4.class)
public class BatchProcessingTest extends TestCase
{
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    @BeforeClass
    public static void before()
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass( Player.class ); //specify annotatioed classes here
        //config.addResource("User.hbm.xml"); //specify mapped resources here
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings( config.getProperties() ).build();
        factory = config.buildSessionFactory( serviceRegistry );
    }

    @Test
    public void NativeSqlTestMethod()
    {
        try (Session session = factory.openSession())

        {
            session.beginTransaction();
            for( int i = 0; i < 1000000; i++ )
            {
                Player player = new Player( "player" + i, 45, SkillLevel.GOOD );
                session.save( player );
                if( i % 50 == 0 )
                { // Same as the JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
            }
            session.getTransaction().commit();

        }
    }

}
