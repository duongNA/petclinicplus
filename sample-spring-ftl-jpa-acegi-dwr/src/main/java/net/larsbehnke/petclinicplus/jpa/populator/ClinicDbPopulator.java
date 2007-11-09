package net.larsbehnke.petclinicplus.jpa.populator;

import java.util.List;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.PetType;
import net.larsbehnke.petclinicplus.model.Specialty;
import net.larsbehnke.petclinicplus.model.User;
import net.larsbehnke.petclinicplus.model.Vet;
import net.larsbehnke.petclinicplus.util.namedvocabulary.NVEntry;

/**
 * Helper class for populating database with initial data.
 * @author Lars Behnke
 */
public class ClinicDbPopulator extends AbstractDbPopulator {

    private Clinic clinic;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    protected void doInsert() {
        List<?> list = clinic.getSpecialties();
        if (list.size() == 0) {
            populatePetTypes();
        }
        list = clinic.getSpecialties();
        if (list.size() == 0) {
            populateSpecialties();
        }
        list = clinic.getVets();
        if (list.size() == 0) {
            populateVets();
        }

    }

    protected void doClear() {
        clinic.clearVets();
        clinic.clearSpecialties();
        clinic.clearPetTypes();
    }

    /**
     * Populates specialties table. Note that IDs are generated by database.
     */
    private void populateSpecialties() {
        List<NVEntry> list = getNamedVocabularyManager().getList("specialties");
        for (NVEntry entry : list) {
            Specialty specialty = new Specialty();
            specialty.setName(entry.getValue());
            clinic.storeSpecialty(specialty);
        }
    }

    /**
     * Populates types table. Note that IDs are generated by database.
     */
    private void populatePetTypes() {
        List<NVEntry> list = getNamedVocabularyManager().getList("pettypes");
        for (NVEntry entry : list) {
            PetType petType = new PetType();
            petType.setName(entry.getValue());
            clinic.storePetType(petType);
        }
    }

    /**
     * Just for providing a quick start... Not recommended in a real
     * application.
     */
    private void populateVets() {
        Specialty[] specs = clinic.getSpecialties().toArray(new Specialty[0]);
        User user;

        user = (User) getUserDetailsService().loadUserByUsername("dalton");
        Vet vet;
        vet = new Vet();
        vet.setUserData(user);
        vet.addSpecialty(specs[0]);
        vet = clinic.storeVet(vet);

        user = (User) getUserDetailsService().loadUserByUsername("scott");
        vet = new Vet();
        vet.setUserData(user);
        vet.addSpecialty(specs[0]);
        vet = clinic.storeVet(vet);
        clinic.storeVet(vet);

    }

}
