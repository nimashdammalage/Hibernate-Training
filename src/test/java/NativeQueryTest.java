import junit.framework.TestCase;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pojoclasses.Address;
import pojoclasses.Employee;

import java.util.List;


@RunWith(JUnit4.class)
public class NativeQueryTest extends TestCase
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
        //this describes Hibernate Query language
        try (Session session = factory.openSession())
        {
            //Scalar Queries
            String sql = "SELECT first_name, salary FROM nimash_test_employee";
            NativeQuery query = session.createSQLQuery( sql );
            query.setResultTransformer( Criteria.ALIAS_TO_ENTITY_MAP );
            List results = query.list();
            System.out.println( results );

            //Entity Queries
            sql = "SELECT * FROM nimash_test_employee";
            query = session.createSQLQuery(sql);
            query.addEntity(Employee.class);
            results = query.list();
            System.out.println( results );

            //Named SQL queries
            sql = "SELECT * FROM nimash_test_employee WHERE id = :employee_id";
            query = session.createSQLQuery(sql);
            query.addEntity(Employee.class);
            query.setParameter("employee_id", 1);
            results = query.list();
            System.out.println( results );

        }
    }

}
