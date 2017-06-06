package test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class DependencySnapshot extends DependencyAbstract {
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
}
