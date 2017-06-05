package test.model;

import javax.persistence.*;

@javax.persistence.Entity
public class MutableEntity extends Entity {
    @ManyToOne
    @JoinColumn(name = "dependency_id")
    private Dependency dependency;

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }
}
