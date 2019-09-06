package inheritence.implicitpoly;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NIM_CREDIT_CARD")
@AttributeOverride(name = "owner", column = @Column(name = "CC_OWNER", nullable = false))
public class Creditcared extends BillingDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_seq")
    @SequenceGenerator(name = "cc_seq", sequenceName = "NIMASH_TEST_CREDIT_CARD_SEQ", allocationSize = 1, initialValue = 1)
    protected long id;

    @Column(nullable = false)
    protected String cardNumber;

    @Column
    protected String expMonth;

    protected Creditcared()
    {
    }

    public Creditcared( String owner, String cardNumber, String expMonth )
    {
        this.owner = owner;
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
    }

    @Override public String toString()
    {
        return "Creditcared{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expMonth='" + expMonth + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
