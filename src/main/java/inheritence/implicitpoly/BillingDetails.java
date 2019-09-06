package inheritence.implicitpoly;

import com.sun.istack.NotNull;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BillingDetails
{
    @NotNull
    protected String owner;

    public String getOwner()
    {
        return owner;
    }

    public void setOwner( String owner )
    {
        this.owner = owner;
    }
}
