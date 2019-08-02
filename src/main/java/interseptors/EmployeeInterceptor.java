package interseptors;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

public class EmployeeInterceptor extends EmptyInterceptor
{
    //link https://www.baeldung.com/hibernate-interceptor
    //https://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/Interceptor.html
    //https://javabeat.net/interceptors-in-hibernate-orm-framework-an-introduction/

    @Override public boolean onSave( Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types )
    {
        System.out.println( "Interceptor called on Save of Employee" );
        return super.onSave( entity, id, state, propertyNames, types );

    }
}
