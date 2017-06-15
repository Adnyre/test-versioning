package test.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MainDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<MutableEntity> getMutableEntities() {
        TypedQuery<MutableEntity> query = em.createQuery("FROM MutableEntity", MutableEntity.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<ImmutableEntity> getImmutableEntities() {
        TypedQuery<ImmutableEntity> query = em.createQuery("FROM ImmutableEntity", ImmutableEntity.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Entity> getEntities() {
        Query res = em.createQuery("FROM test.model.Entity");
        return (List<Entity>) res.getResultList();
    }

    @Transactional
    public MutableEntity saveMutableEntity(MutableEntity entity) {
        entity.setId(getNextEntityId());
        em.persist(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    public List<Dependency> getDependencies() {
        TypedQuery<Dependency> query = em.createQuery("FROM Dependency", Dependency.class);
        return query.getResultList();
    }

    //for testing
    @Transactional(readOnly = true)
    public List<DependencySnapshot> getDependencySnapshots() {
        TypedQuery<DependencySnapshot> query = em.createQuery("FROM DependencySnapshot", DependencySnapshot.class);
        return query.getResultList();
    }

    //for testing
    @Transactional(readOnly = true)
    public List<Something> getSomething() {
        TypedQuery<Something> query = em.createQuery("FROM Something", Something.class);
        return query.getResultList();
    }

    @Transactional
    public Dependency saveDependency(Dependency dependency) {
        em.persist(dependency);
        return dependency;
    }

    @Transactional
    public ImmutableEntity makeImmutable(int id) {
        MutableEntity mutable = em.find(MutableEntity.class, id);
        ImmutableEntity immutable = convertToImmutable(mutable);
        em.persist(immutable);
        em.remove(mutable);
        return immutable;
    }

    @Transactional
    public MutableEntity makeMutable(int id) {
        ImmutableEntity immutable = em.find(ImmutableEntity.class, id);
        MutableEntity mutable = convertToMutable(immutable);
        em.persist(mutable);
        em.remove(immutable);
        return mutable;
    }

    @Transactional(readOnly = true)
    public int getNextEntityId() {
        return ((BigInteger) em.createNativeQuery("SELECT NEXTVAL('entity_seq')").getSingleResult()).intValue();
    }

    private ImmutableEntity convertToImmutable(MutableEntity mutable) {
        ImmutableEntity immutable = new ImmutableEntity();
        immutable.setId(mutable.getId());
        immutable.setNic(mutable.getNic());
        immutable.setDependency(convertToSnapshot(mutable.getDependency()));
        immutable.setSomething(new ArrayList<>(mutable.getSomething()));
        return immutable;
    }

    private MutableEntity convertToMutable(ImmutableEntity immutable) {
        MutableEntity mutable = new MutableEntity();
        mutable.setId(immutable.getId());
        mutable.setNic(immutable.getNic());
        mutable.setDependency(immutable.getDependency().getDependency());
        mutable.setSomething(new ArrayList<>(immutable.getSomething()));
        return mutable;
    }

    private DependencySnapshot convertToSnapshot(Dependency dependency) {
        DependencySnapshot snapshot = new DependencySnapshot();
        snapshot.setNic(dependency.getNic());
        snapshot.setDependency(dependency);
        return snapshot;
    }

    @Transactional
    public MutableEntity updateMutableEntity(MutableEntity entity) {
        return em.merge(entity);
    }

    @Transactional
    public Dependency updateDependency(Dependency dependency) {
        return em.merge(dependency);
    }
}
