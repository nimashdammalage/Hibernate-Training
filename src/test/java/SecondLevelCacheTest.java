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
import pojoclasses.Address;
import pojoclasses.Employee;
import pojoclasses.Player;
import pojoclasses.SkillLevel;

@RunWith(JUnit4.class)
public class SecondLevelCacheTest extends TestCase
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
            Player player = new Player( "playerKasun", 45, SkillLevel.GOOD );
            session.save( player );

            session.load( Player.class, new Integer( 1 ) );
            session.getTransaction().commit();

            session.beginTransaction();
            int cacheSize = CacheManager.ALL_CACHE_MANAGERS.get( 0 ).getCache( "pojoclasses.Player" ).getSize();
            System.out.println( "Seconf cache size: " + cacheSize );
            session.getTransaction().commit();


        }
    }

}
