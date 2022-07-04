package switch2021.project.interfaceAdapters.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IProjectWebRepository;
import switch2021.project.dataModel.REST.assemblers.ProjectDomainDataRestAssembler;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.interfaceAdapters.repositories.REST.ProjectRestRepository;

import javax.net.ssl.SSLException;
import java.util.List;

@Repository
public class ProjectWebRepository implements IProjectWebRepository {

    @Autowired
    ProjectRestRepository projectRestRepository;

    @Autowired
    ProjectDomainDataRestAssembler projectDomainDataRestAssembler;

    @Override
    public List<Project> findAll() throws SSLException {
        return projectDomainDataRestAssembler.toDomain(projectRestRepository.findAll());

    }

}
