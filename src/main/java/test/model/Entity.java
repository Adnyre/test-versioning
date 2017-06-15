package test.model;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public abstract class Entity {

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

    public abstract int getId();

    public abstract void setId(int id);
}
