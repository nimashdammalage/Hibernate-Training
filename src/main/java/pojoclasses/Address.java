package pojoclasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NIMASH_TEST_ADDRESS")
public class Address
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "NIMASH_TEST_ADDRESS_SEQ", allocationSize = 1, initialValue = 1)
    private int addressId;

    @Column
    private String firstLane;

    @Column
    private String secondLane;

    @Column
    private String area;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)//mappedBy tells inverse connection
    private Employee employee;

    public Address()
    {
    }

    public Address( String firstLane, String secondLane, String area )
    {
        this.firstLane = firstLane;
        this.secondLane = secondLane;
        this.area = area;
    }

    public int getAddressId()
    {
        return addressId;
    }

    public void setAddressId( int addressId )
    {
        this.addressId = addressId;
    }

    public String getFirstLane()
    {
        return firstLane;
    }

    public void setFirstLane( String firstLane )
    {
        this.firstLane = firstLane;
    }

    public String getSecondLane()
    {
        return secondLane;
    }

    public void setSecondLane( String secondLane )
    {
        this.secondLane = secondLane;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String area )
    {
        this.area = area;
    }
}
