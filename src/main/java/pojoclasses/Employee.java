package pojoclasses;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NIMASH_TEST_EMPLOYEE")
public class Employee
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_GEN")
    @SequenceGenerator(name = "SEQUENCE_GEN", sequenceName = "NIMASH_TEST_EMPLOYEE_SEQ", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "first_name", length = 20, nullable = true, unique = false)
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = true, unique = false)
    private String lastName;

    @Column(name = "salary", nullable = true)
    private int salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Address address;

    public Employee()
    {
    }

    public Employee( String firstName, String lastName, int salary )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public int getSalary()
    {
        return salary;
    }

    public void setSalary( int salary )
    {
        this.salary = salary;
    }

    public void setAddress( Address address )
    {
        this.address = address;
    }

    public Address getAddress()
    {
        return address;
    }
}
