package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pojoclasses.Employee;

public class HibernateTutorialTest
{
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public static void main( String[] args )
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass( Employee.class ); //specify annotatioed classes here
        //config.addResource("User.hbm.xml"); //specify mapped resources here
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings( config.getProperties() ).build();
        factory = config.buildSessionFactory( serviceRegistry );


        try (Session session = factory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee( "hemal", "nilanga", 4500 );
            int empId = (int) session.save( employee );
            transaction.commit();
        }


    }

}