package booktest;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;

@RunWith(JUnit4.class)
public class UserTest extends TestCase
{
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    @BeforeClass
    public static void before()
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass( User.class ); //specify annotatioed classes here
        config.addAnnotatedClass( Address.class ); //specify annotatioed classes here
        config.addAnnotatedClass( Shipment.class ); //specify annotatioed classes here
        //config.addResource("User.hbm.xml"); //specify mapped resources here
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings( config.getProperties() ).build();
        factory = config.buildSessionFactory( serviceRegistry );
    }

    @Test
    public void testEmployee()
    {
        try (Session session = factory.openSession())
        {
            /*Insert operation*/
            Transaction transaction = session.beginTransaction();
            final Shipment myshipment = new Shipment( 78, "myshipment" );
            session.save( myshipment);
            Address address = new Address( "mystreet", "myCity", new HashSet<Shipment>()
            {{
                add( myshipment );
            }} );

            User myUser = new User( "myUser", address );
            session.save( myUser );

            transaction.commit();
        }
    }
}