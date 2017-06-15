package test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@javax.persistence.Entity
public class DependencySnapshot extends DependencyAbstract {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dependency_id")
    @JsonIgnore
    private Dependency dependency;

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
