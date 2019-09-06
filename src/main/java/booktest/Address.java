package booktest;

import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class Address
{
    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_USER_ID" )
    Set<Shipment> shipments = new HashSet<>();

    public Address()
    {
    }

    public Address( String street, String city, Set<Shipment> shipments )
    {
        this.street = street;
        this.city = city;
        this.shipments = shipments;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet( String street )
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public Set<Shipment> getShipments()
    {
        return shipments;
    }

    public void setShipments( Set<Shipment> shipments )
    {
        this.shipments = shipments;
    }
}
