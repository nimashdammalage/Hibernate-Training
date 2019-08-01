import junit.framework.TestCase;
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
public class FirstLevelCacheTest extends TestCase
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
    public void NativeSqlTestMethod()
    {
        try (Session session = factory.openSession();
             Session secSession = factory.openSession())
        {
            session.beginTransaction();

            //fetch the employee entity from database first time
            Employee employee = session.load( Employee.class, new Integer( 1 ) );
            System.out.println( employee.getFirstName() );

            //fetch the employee entity again
            employee = session.load( Employee.class, new Integer( 1 ) );
            System.out.println( employee.getFirstName() );

            session.getTransaction().commit();
            //see that at second time , sql query is not sent to DB for data retrial


            //fetch the employee entity using other session
            Employee secEmployee = secSession.load( Employee.class, new Integer( 1 ) );
            System.out.println( secEmployee.getFirstName() );
            //see sql query executes for other session. L1 cache is session specific
        }

        //we can use envict() clear() fro removing cached objects
        //https://howtodoinjava.com/hibernate/understanding-hibernate-first-level-cache-with-example/


    }

}
