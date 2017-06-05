package test.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MainDao {

    @PersistenceContext
    private EntityManager em;

    public List<MutableEntity> getMutableEntities() {
        TypedQuery<MutableEntity> query = em.createQuery("FROM MutableEntity", MutableEntity.class);
        return query.getResultList();
    }

    public List<ImmutableEntity> getImmutableEntities() {
        TypedQuery<ImmutableEntity> query = em.createQuery("FROM ImmutableEntity", ImmutableEntity.class);
        return query.getResultList();
    }

    public List<Entity> getEntities() {
        List<Entity> res = new ArrayList<>();
        res.addAll(getMutableEntities());
        res.addAll(getImmutableEntities());
        return res;
    }

    public MutableEntity saveMutableEntity(MutableEntity entity) {
        em.persist(entity);
        return entity;
    }

    public List<Dependency> getDependencies() {
        TypedQuery<Dependency> query = em.createQuery("FROM Dependency", Dependency.class);
        return query.getResultList();
    }

    public Dependency saveDependency(Dependency dependency) {
        em.persist(dependency);
        return dependency;
    }

    public ImmutableEntity makeImmutable(int id) {
        MutableEntity mutable = em.find(MutableEntity.class, id);
        ImmutableEntity immutable = convertToImmutable(mutable);
        em.persist(immutable);
        em.remove(mutable);
        return immutable;
    }

    private ImmutableEntity convertToImmutable(MutableEntity mutable) {
        ImmutableEntity immutable = new ImmutableEntity();
        immutable.setNic(mutable.getNic());
        immutable.setDependency(convertToSnapshot(mutable.getDependency()));
        return immutable;
    }

    private DependencySnapshot convertToSnapshot(Dependency dependency) {
        DependencySnapshot snapshot = new DependencySnapshot();
        snapshot.setNic(dependency.getNic());
        return snapshot;
    }

}
