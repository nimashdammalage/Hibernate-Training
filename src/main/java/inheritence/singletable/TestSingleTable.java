package inheritence.singletable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class TestSingleTable
{
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public static void main( String[] args )
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass( BankAccount.class ); //specify annotatioed classes here
        config.addAnnotatedClass( BillingDetails.class ); //specify annotatioed classes here
        config.addAnnotatedClass( CreditCard.class ); //specify annotatioed classes here
        //config.addResource("User.hbm.xml"); //specify mapped resources here
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings( config.getProperties() ).build();
        factory = config.buildSessionFactory( serviceRegistry );


        try (Session session = factory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            CreditCard creditcared = new CreditCard( "nimash", "caredNum44", "November" );
            BankAccount bankAccount = new BankAccount( "kalum", "account", "bank sample name" );
            long empId1 = (long) session.save( creditcared );
            long empId2 = (long) session.save( bankAccount );
            transaction.commit();

            //get Polymorphic association. not working because no superclass is not an entity
            Transaction t2 = session.beginTransaction();
            String hql = "SELECT c FROM BillingDetails c";
            Query query = session.createQuery( hql);
            List results = query.list();
            System.out.println(results.toString());
            t2.commit();
        }


    }

}