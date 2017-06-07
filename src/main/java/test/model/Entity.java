package test.model;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "entity_seq")
public abstract class Entity {
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

    public abstract DependencyAbstract getDependency();

    public abstract List<Something> getSomething();

    public abstract void setSomething(List<Something> something);
}
