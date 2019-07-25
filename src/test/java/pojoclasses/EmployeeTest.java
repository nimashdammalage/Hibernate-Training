package pojoclasses;

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

@RunWith(JUnit4.class)
public class EmployeeTest extends TestCase
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
    public void testEmployee()
    {
        try (Session session = factory.openSession())
        {
            /*Insert operation*/
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee( "hemal", "nilanga", 4500 );
            Address address = new Address( "firstLane", "secondLne", "Sri lanka" );
            employee.setAddress( address );
            /*
            *First difference between save and persist is there return type.
            * Similar to save method persist also INSERT records into database but return type of persist is void while return type of save is Serializable object.
            *
            * persist() method doesn't guarantee that the identifier value will be assigned to the persistent instance immediately, the assignment might happen at flush time
            *
            * persist() method guarantees that it will not execute an INSERT statement if it is called outside of transaction boundaries.
            * save() method does not guarantee the same, it returns an identifier, and if an INSERT has to be executed to get the identifier (e.g. "identity" generator),
            * this INSERT happens immediately, no matter if you are inside or outside of a transaction.
            *
            * */
            int empId = (int) session.save( employee );
            session.persist( employee ); //returns void
            session.flush();
            transaction.commit();

            /*Update operation*/
            transaction = session.beginTransaction();
            employee.setFirstName( "modifiedFstName" );
            address.setFirstLane( "ModifiedFirstLane" );
            empId = (int) session.save( employee );
            transaction.commit();

            /*Delete operation*/
            transaction = session.beginTransaction();
            session.delete( employee );
            transaction.commit();
        }
    }
}