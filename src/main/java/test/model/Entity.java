package test.model;

import javax.persistence.*;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "entity_seq")
public class Entity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "entity_seq")
    private int id;

    private String nic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}