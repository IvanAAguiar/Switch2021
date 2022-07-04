package switch2021.project.interfaceAdapters.repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.dataModel.JPA.SprintJpa;
import switch2021.project.dataModel.JPA.assembler.SprintJpaAssembler;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.persistence.SprintJpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SprintRepository implements ISprintRepo {

    /**
     * Attributes
     **/
    private List<Sprint> sprints;

    @Autowired
    private SprintJpaRepository sprintJpaRepository;
    @Autowired
    private SprintJpaAssembler assembler;

    /**
     * Constructor
     **/
    public SprintRepository() {
        this.sprints = new ArrayList<>();
    }


    public Sprint save(Sprint newSprint) throws Exception {
        SprintJpa sprintJpa = assembler.toData(newSprint);

        if (!sprintJpaRepository.existsById(sprintJpa.getSprintId())) {
            SprintJpa sprintJpaSaved = sprintJpaRepository.save(sprintJpa);
            return assembler.toDomain(sprintJpaSaved);
        }
        throw new Exception("Sprint already exists");
    }

    /**
     * Check If Sprint Already Exists
     */
    public boolean existsBySprintID(String sprintID) {
        List<SprintJpa> sprintJpaList = sprintJpaRepository.findAll();

        for (SprintJpa jpa : sprintJpaList) {
            sprints.add(assembler.toDomain(jpa));
        }

        boolean msg = false;
        for (Sprint sprint : sprints) {
            if (sprint.hasSprintID(sprintID)) {
                msg = true;
            }
        }
        sprints = new ArrayList<>();
        return msg;
    }

    /**
     * Find List of Sprints Method
     **/
    @Override
    public List<Sprint> findAllSprints() {
        List<SprintJpa> sprintJpaList = sprintJpaRepository.findAll();
        List<Sprint> sprints = new ArrayList<>();

        for (SprintJpa sprintJpa : sprintJpaList) {
            sprints.add(assembler.toDomain(sprintJpa));
        }
        return sprints;
    }

    /**
     * Find Sprint By ID Method
     **/
    @Override
    public Optional<Sprint> findBySprintID(SprintID id) {
        Optional<SprintJpa> sprintJpa = sprintJpaRepository.findById(id);
        Optional<Sprint> sprint = Optional.empty();

        if (sprintJpa.isPresent()) {
            sprint = Optional.of(assembler.toDomain(sprintJpa.get()));
        }
        return sprint;
    }

    /**
     * Find all sprints associated to a Project ID Method
     **/
    @Override
    public List<Sprint> findAllByProjectID(ProjectID projectID) {
        List<Sprint> allSprintsInAProject = new ArrayList<>();

        List<SprintJpa> sprintJpaList = sprintJpaRepository.findAll();

        for (SprintJpa jpa : sprintJpaList) {
            sprints.add(assembler.toDomain(jpa));
        }

        for (Sprint x : sprints) {
            if (x.getSprintID().getProjectID().getCode().equalsIgnoreCase(projectID.getCode())) {
                allSprintsInAProject.add(x);
            }
        }
        sprints = new ArrayList<>();
        return allSprintsInAProject;
    }

    @Override
    public boolean existsSprintByID(SprintID id) {
        return sprintJpaRepository.existsById(id);
    }

    /**
     * Find Current Sprint Method
     **/
    public Sprint findCurrentSprint() {
        Sprint sprint = null;

        List<SprintJpa> sprintJpaList = sprintJpaRepository.findAll();

        for (SprintJpa jpa : sprintJpaList) {
            sprints.add(assembler.toDomain(jpa));

        }
        for (Sprint i : sprints) {
            if (i.isCurrentSprint()) {
                sprint = i;
            }
        }

        if (sprint == null) {
            throw new NullPointerException("Current sprint doesn't exist");
        }
        sprints = new ArrayList<>();
        return sprint;
    }

    /**
     * Delete Sprint Method
     **/
    @Override
    public boolean deleteSprint(SprintID sprintID) {
        if (sprintJpaRepository.existsById(sprintID)) {
            sprintJpaRepository.deleteById(sprintID);
            return true;
        }
        return false;
    }


}