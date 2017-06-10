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
    @JoinFormula(value = "CASE WHEN mutable_entity_id IS NOT NULL THEN mutable_entity_id " +
    "WHEN immutable_entity_id IS NOT NULL THEN immutable_entity_id END", referencedColumnName = "id")
    @JsonBackReference
    private Entity entity;

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

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
