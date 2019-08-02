import interseptors.EmployeeInterceptor;
import junit.framework.TestCase;
import org.hibernate.EmptyInterceptor;
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

@RunWith(JUnit4.class)
public class HibernateInterceptorTest extends TestCase
{
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    @BeforeClass
    public static void before()
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass( Employee.class ); //specify annotatioed classes here
        config.addAnnotatedClass( Address.class ); //specify annotatioed classes here
        //config.addResource("User.hbm.xml"); //specify mapped resources here
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings( config.getProperties() ).build();
        factory = config.buildSessionFactory( serviceRegistry );
    }

    @Test
    public void tesingInterceptorMethods()
    {
        try (Session session = factory.withOptions().interceptor( new EmployeeInterceptor() ).openSession())
        {
            session.getTransaction().begin();
            Employee employee = new Employee( "Kamal", "Gunarathne", 22 );
            Address address = new Address( "F6", "NormalStrees", "Mahaoya" );
            employee.setAddress( address );
            session.save( employee ); //returns void
            session.flush();
            session.getTransaction().commit();

        }
    }
}
