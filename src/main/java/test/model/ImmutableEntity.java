package test.model;

import javax.persistence.*;

@javax.persistence.Entity
public class ImmutableEntity extends Entity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dependency_id")
    private DependencySnapshot dependency;

    public DependencySnapshot getDependency() {
        return dependency;
    }

    public void setDependency(DependencySnapshot dependency) {
        this.dependency = dependency;
    }
}
