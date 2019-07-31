package pojoclasses;

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

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(JUnit4.class)
public class CrieteriaQueryTest extends TestCase
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
            // Create CriteriaQuery
            CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder().createQuery( Employee.class );

            //using simple criteria object
            Root<Employee> root = criteriaQuery.from( Employee.class );//Set the query Root by calling the from() method on the CriteriaQuery
            criteriaQuery.select( root );//Specify what the type of the query result will be by calling the select() method of the CriteriaQuery object.
            Query<Employee> query = session.createQuery( criteriaQuery );
            List<Employee> resultList = query.getResultList();
            System.out.println(resultList.toString());

            //--------------------------------------------------------------------------------

            CriteriaQuery<Employee> secondCriteriaQuery = session.getCriteriaBuilder().createQuery( Employee.class );
            Root<Employee> secondRoot = secondCriteriaQuery.from( Employee.class );
            secondCriteriaQuery.select( secondRoot ).where( session.getCriteriaBuilder().equal( secondRoot.get( "id" ), 1 ) );
            Query<Employee> query1 = session.createQuery( secondCriteriaQuery );
            Employee singleResult = query1.getSingleResult();
            System.out.println( singleResult );


            // To get records having salary more than 200
            secondCriteriaQuery.select( secondRoot ).where( session.getCriteriaBuilder().greaterThanOrEqualTo( secondRoot.get( "salary" ).as(Integer.class), 200 ) );
            System.out.println( session.createQuery( secondCriteriaQuery ).getSingleResult());

            // To get records having salary less than 200
            secondCriteriaQuery.select( secondRoot ).where( session.getCriteriaBuilder().lessThan( secondRoot.get( "salary" ).as(Integer.class), 20000 ) );
            System.out.println( session.createQuery( secondCriteriaQuery ).getSingleResult());

            // To get records having fistName starting with zara
            secondCriteriaQuery.select( secondRoot ).where( session.getCriteriaBuilder().like( secondRoot.get( "firstName" ).as(String.class), "%mod%" ) );
            System.out.println( session.createQuery( secondCriteriaQuery ).getSingleResult());

            // Case sensitive form of the above restriction.
            secondCriteriaQuery.select( secondRoot ).where( session.getCriteriaBuilder().like( secondRoot.get( "firstName" ).as(String.class), "%mod%" ) );
            System.out.println( session.createQuery( secondCriteriaQuery ).getSingleResult());

//            // Case sensitive form of the above restriction.
//            cr.add(Restrictions.ilike("firstName", "zara%"));
//
//            // To get records having salary in between 1000 and 2000
//            cr.add(Restrictions.between("salary", 1000, 2000));
//
//            // To check if the given property is null
//            cr.add(Restrictions.isNull("salary"));
//
//            // To check if the given property is not null
//            cr.add(Restrictions.isNotNull("salary"));
//
//            // To check if the given property is empty
//            cr.add(Restrictions.isEmpty("salary"));
//
//            // To check if the given property is not empty
//            cr.add(Restrictions.isNotEmpty("salary"));
        }
    }

}
