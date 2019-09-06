package inheritence.tableperclassjoin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "NIM_CREDIT_CARD")
@PrimaryKeyJoinColumn(name = "cc_join_col")
public class CreditCard extends BillingDetails
{

    @Column(nullable = false)
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
