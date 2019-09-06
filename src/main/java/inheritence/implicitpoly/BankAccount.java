package inheritence.implicitpoly;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NIM_BANK_ACC")
public class BankAccount extends BillingDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ba_seq")
    @SequenceGenerator(name = "ba_seq", sequenceName = "NIMASH_TEST_BANK_ACC_SEQ", allocationSize = 1, initialValue = 1)
    protected long id;

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
