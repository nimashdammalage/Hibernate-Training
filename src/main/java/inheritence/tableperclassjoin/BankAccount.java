package inheritence.tableperclassjoin;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "NIM_BANK_ACC")
@PrimaryKeyJoinColumn(name = "ba_join_col")
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
