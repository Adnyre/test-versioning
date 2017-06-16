package test.model;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public abstract class Entity {

    @Id
    private int id;

    private String nic;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public abstract DependencyAbstract getDependency();

    public abstract List<Something> getSomething();

    public abstract void setSomething(List<Something> something);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
