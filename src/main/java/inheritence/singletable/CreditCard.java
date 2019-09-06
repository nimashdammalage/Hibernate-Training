package inheritence.singletable;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value = "CC")
public class CreditCard extends BillingDetails
{

    @NotNull
    protected String cardNumber;

    @Column
    protected String expMonth;

    protected CreditCard()
    {
    }

    public CreditCard( String owner, String cardNumber, String expMonth )
    {
        this.owner = owner;
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
    }

    @Override public String toString()
    {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expMonth='" + expMonth + '\'' +
                ", id=" + id +
                ", owner='" + owner + '\'' +
                '}';
    }
}
