package net.larsbehnke.petclinicplus.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.Owner;
import net.larsbehnke.petclinicplus.model.Pet;
import net.larsbehnke.petclinicplus.model.PetType;
import net.larsbehnke.petclinicplus.model.Specialty;
import net.larsbehnke.petclinicplus.model.Vet;
import net.larsbehnke.petclinicplus.model.Visit;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the Clinic interface using EntityManager.
 * <p>
 * The mappings are defined in "orm.xml" located in the META-INF dir.
 * @author Mike Keith
 * @author Rod Johnson
 * @author Lars Behnke
 */
@Repository
@Transactional
public class EntityManagerClinic implements Clinic {

    @PersistenceContext
    private EntityManager em;

    public void clearPetTypes() throws DataAccessException {
        Query query = em.createQuery("DELETE PetType typ");
        query.executeUpdate();
    }

    public void clearSpecialties() throws DataAccessException {
        Query query = em.createQuery("DELETE Specialty spec");
        query.executeUpdate();
    }

    public void clearVets() throws DataAccessException {
        Query query = em.createQuery("DELETE Vet vet");
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public Long countOwners(String lastName) throws DataAccessException {
        Query query = em
                .createQuery("SELECT count(owner) FROM Owner owner WHERE owner.userData.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return (Long) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Owner> findOwners(String lastName) throws DataAccessException {
        Query query = em
                .createQuery("SELECT owner FROM Owner owner WHERE owner.userData.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PetType> getPetTypes() throws DataAccessException {
        return em.createQuery("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Specialty> getSpecialties() throws DataAccessException {
        return em.createQuery("SELECT s FROM Specialty s").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Vet> getVets() throws DataAccessException {
        return em.createQuery("SELECT vet FROM Vet vet").getResultList();
    }

    public Owner loadOwner(int id) throws DataAccessException {
        return em.find(Owner.class, id);
    }

    public Pet loadPet(int id) throws DataAccessException {
        return em.find(Pet.class, id);
    }

    public Vet loadVet(int id) throws DataAccessException {
        return em.find(Vet.class, id);
    }

    @SuppressWarnings("unchecked")
    public Vet loadVetByLoginName(String loginName) throws DataAccessException {
        Query query = em
                .createQuery("SELECT vet FROM Vet vet WHERE vet.userData.username = :loginName");
        query.setParameter("loginName", loginName);
        List<Vet> list = query.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Owner storeOwner(Owner owner) throws DataAccessException {
        return em.merge(owner);
    }

    public Pet storePet(Pet pet) throws DataAccessException {
        return em.merge(pet);
    }

    public PetType storePetType(PetType petType) throws DataAccessException {
        return em.merge(petType);
    }

    public Specialty storeSpecialty(Specialty specialtiy) throws DataAccessException {
        return em.merge(specialtiy);
    }

    public Vet storeVet(Vet vet) throws DataAccessException {
        return em.merge(vet);
    }

    public Visit storeVisit(Visit visit) throws DataAccessException {
        return em.merge(visit);
    }

}
