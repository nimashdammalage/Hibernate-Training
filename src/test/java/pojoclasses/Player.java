package pojoclasses;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Cacheable
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE)  //https://stackoverflow.com/questions/1837651/hibernate-cache-strategy
@Table(name = "NIMASH_TEST_PLAYER")
public class Player
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_GEN")
    @SequenceGenerator(name = "SEQUENCE_GEN", sequenceName = "NIMASH_TEST_PLAYER_SEQ", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "player_name", length = 100, nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level")
    private SkillLevel skillLevel;

    public Player( String name, int age, SkillLevel skillLevel )
    {
        this.name = name;
        this.age = age;
        this.skillLevel = skillLevel;
    }

    public Player()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge( int age )
    {
        this.age = age;
    }

    public SkillLevel getSkillLevel()
    {
        return skillLevel;
    }

    public void setSkillLevel( SkillLevel skillLevel )
    {
        this.skillLevel = skillLevel;
    }

    @Override public String toString()
    {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", skillLevel=" + skillLevel +
                '}';
    }
}
