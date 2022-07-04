package switch2021.project.interfaceAdapters.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.dataModel.JPA.ProjectJpa;
import switch2021.project.dataModel.JPA.assembler.ProjectJpaAssembler;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.persistence.ProjectJpaRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository implements IProjectRepo {

    @Autowired
    ProjectJpaRepository projectJpaRepository;
    @Autowired
    ProjectJpaAssembler projectJpaAssembler;

    @Override
    public Optional<Project> findById(ProjectID id) {

        Optional<ProjectJpa> opProjJpa = projectJpaRepository.findById(id);

        if (opProjJpa.isPresent()) {
            ProjectJpa projJpa = opProjJpa.get();

            Project proj = projectJpaAssembler.toDomain(projJpa);
            return Optional.of(proj);
        } else
            return Optional.empty();
    }


    @Override
    public List<Project> findAll() {
        List<ProjectJpa> projectJpaList = projectJpaRepository.findAll();

        List<Project> projectList = new ArrayList<>();

        for (ProjectJpa projectJpa : projectJpaList) {
            Project project = projectJpaAssembler.toDomain(projectJpa);
            projectList.add(project);
        }

        return projectList;
    }


    @Override
    public boolean delete(ProjectID id) {

        if (projectJpaRepository.existsById(id)) {
            projectJpaRepository.deleteById(id);
            return true;
        }

        return false;
    }


    @Override
    public Project save(Project newProject) {
        ProjectJpa projectJpa = projectJpaAssembler.toJpaData(newProject);

        ProjectJpa savedProj = projectJpaRepository.save(projectJpa);
        return projectJpaAssembler.toDomain(savedProj);

    }


    @Override
    public boolean existsById(ProjectID id) {
        return projectJpaRepository.existsById(id);
    }

}


