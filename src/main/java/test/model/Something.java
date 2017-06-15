package test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

@javax.persistence.Entity
public class Something {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String nic;

    @ManyToOne
    @JoinColumn(name = "mutable_entity_id")
    @JsonBackReference
    private MutableEntity mutableEntity;

    @ManyToOne
    @JoinColumn(name = "immutable_entity_id")
    @JsonBackReference
    private ImmutableEntity immutableEntity;

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

    public MutableEntity getMutableEntity() {
        return mutableEntity;
    }

    public void setMutableEntity(MutableEntity mutableEntity) {
        this.mutableEntity = mutableEntity;
    }

    public ImmutableEntity getImmutableEntity() {
        return immutableEntity;
    }

    public void setImmutableEntity(ImmutableEntity immutableEntity) {
        this.immutableEntity = immutableEntity;
    }
}
