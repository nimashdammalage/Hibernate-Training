import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pojoclasses.Address;
import pojoclasses.Employee;

import java.util.List;

@RunWith(JUnit4.class)
public class HQLTest extends TestCase
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
    public void HDLTestMethod()
    {
        //this describes Hibernate Query language
        try (Session session = factory.openSession())
        {
            String hql = "FROM Employee";
            Query query = session.createQuery( hql );
            List results = query.list();
            System.out.println( "From clause results: " + results.toString() );

            hql = "FROM Employee AS E";
            query = session.createQuery(hql);
            results = query.list();
            System.out.println( "As clause results: " + results.toString() );

            hql = "SELECT E.firstName FROM Employee E WHERE E.id = 10";
            query = session.createQuery(hql);
            results = query.list();
            System.out.println( "Select clause results: " + results.toString() );

            hql = "SELECT E.firstName FROM Employee E WHERE E.id > 10 GROUP BY E.firstName";
            query = session.createQuery(hql);
            results = query.list();
            System.out.println( "Select clause results: " + results.toString() );

            //Using named parameters
            hql = "FROM Employee E WHERE E.id = :employee_id";
            query = session.createQuery(hql);
            query.setParameter("employee_id",10);
            results = query.list();
            System.out.println( "Select clause results: " + results.toString() );

            hql = "UPDATE Employee set salary = :salary WHERE id = :employee_id";
            query = session.createQuery(hql);
            query.setParameter("salary", 1000);
            query.setParameter("employee_id", 1);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            hql = "INSERT INTO Employee(firstName, lastName, salary) SELECT firstName, lastName, salary FROM old_employee";
            query = session.createQuery(hql);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            //aggregate methods avg() count() max() min() sum()
            hql = "SELECT sum(E.id) FROM Employee E WHERE E.id = 10";
            query = session.createQuery(hql);
            results = query.list();
            System.out.println( "Select clause results: " + results.toString() );

            hql = "FROM Employee";
            query = session.createQuery(hql);
            query.setFirstResult(1);
            query.setMaxResults(10);
            results = query.list();

            //https://docs.jboss.org/hibernate/core/3.3/reference/en/html/queryhql.html#queryhql-casesensitivity
        }
    }
}


