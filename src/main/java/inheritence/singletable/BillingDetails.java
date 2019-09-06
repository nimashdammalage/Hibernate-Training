package inheritence.singletable;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DECRI_COLUMN")
public abstract class BillingDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ba_seq")
    @SequenceGenerator(name = "ba_seq", sequenceName = "NIMASH_TEST_BANK_ACC_SEQ", allocationSize = 1, initialValue = 1)
    protected long id;

    @NotNull
    @Column
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
