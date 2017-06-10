package test.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
public class ImmutableEntity extends Entity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dependency_id")
    private DependencySnapshot dependency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "immutable_entity_id")
    @JsonManagedReference
    private List<Something> something;

    public DependencySnapshot getDependency() {
        return dependency;
    }

    public void setDependency(DependencySnapshot dependency) {
        this.dependency = dependency;
    }

    public List<Something> getSomething() {
        return something;
    }

    public void setSomething(List<Something> something) {
        this.something = something;
    }
}
