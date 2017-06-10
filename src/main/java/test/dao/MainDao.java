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
        TypedQuery<Entity> res = em.createQuery("FROM Entity", Entity.class);
        return res.getResultList();
    }

    public MutableEntity saveMutableEntity(MutableEntity entity) {
        em.persist(entity);
        return entity;
    }

    public List<Dependency> getDependencies() {
        TypedQuery<Dependency> query = em.createQuery("FROM Dependency", Dependency.class);
        return query.getResultList();
    }

    //for testing
    public List<DependencySnapshot> getDependencySnapshots() {
        TypedQuery<DependencySnapshot> query = em.createQuery("FROM DependencySnapshot", DependencySnapshot.class);
        return query.getResultList();
    }

    //for testing
    public List<Something> getSomething() {
        TypedQuery<Something> query = em.createQuery("FROM Something", Something.class);
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

    public MutableEntity makeMutable(int id) {
        ImmutableEntity immutable = em.find(ImmutableEntity.class, id);
        MutableEntity mutable = convertToMutable(immutable);
        em.persist(mutable);
        em.remove(immutable);
        return mutable;
    }

    private ImmutableEntity convertToImmutable(MutableEntity mutable) {
        ImmutableEntity immutable = new ImmutableEntity();
        immutable.setNic(mutable.getNic());
        immutable.setDependency(convertToSnapshot(mutable.getDependency()));
        immutable.setSomething(new ArrayList<>(mutable.getSomething()));
        return immutable;
    }

    private MutableEntity convertToMutable(ImmutableEntity immutable) {
        MutableEntity mutable = new MutableEntity();
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

    public MutableEntity updateMutableEntity(MutableEntity entity) {
        return em.merge(entity);
    }
}
