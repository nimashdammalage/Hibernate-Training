package inheritence.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value = "BA")
public class BankAccount extends BillingDetails
{

    protected String account;

    private String bankName;

    public BankAccount()
    {
    }

    public BankAccount( String owner, String account, String bankName )
    {
        this.owner = owner;
        this.account = account;
        this.bankName = bankName;
    }

    @Override public String toString()
    {
        return "BankAccount{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", bankName='" + bankName + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
