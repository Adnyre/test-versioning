package test.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
public class MutableEntity extends Entity {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "dependency_id")
    private Dependency dependency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mutable_entity_id")
    @JsonManagedReference
    private List<Something> something;


    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public List<Something> getSomething() {
        return something;
    }

    public void setSomething(List<Something> something) {
        this.something = something;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
