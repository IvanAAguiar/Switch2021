package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;


public interface IProjectFactory {

    Project createProject(ProjectDTO projectDTO);
}
