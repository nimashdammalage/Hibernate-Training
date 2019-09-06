package booktest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shipmentId;

    private String shipmentName;

    public Shipment( int shipmentId, String shipmentName )
    {
        this.shipmentId = shipmentId;
        this.shipmentName = shipmentName;
    }

    public Shipment()
    {
    }

    public int getShipmentId()
    {
        return shipmentId;
    }

    public void setShipmentId( int shipmentId )
    {
        this.shipmentId = shipmentId;
    }

    public String getShipmentName()
    {
        return shipmentName;
    }

    public void setShipmentName( String shipmentName )
    {
        this.shipmentName = shipmentName;
    }
}
