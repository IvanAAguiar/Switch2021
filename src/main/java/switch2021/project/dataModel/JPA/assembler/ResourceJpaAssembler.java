package switch2021.project.dataModel.JPA.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.JPA.ResourceIDJpa;
import switch2021.project.dataModel.JPA.ResourceJpa;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceJpaAssembler {

    public ResourceIDJpa toData(ResourceID id) {
        UserID userId = id.getUser();
        ProjectID projId = id.getProject();
        String startDate = id.getStartDate().toString();

        return new ResourceIDJpa(userId, projId, startDate);
    }

    public ResourceJpa toData(Resource resource) {
        ResourceIDJpa id = toData(resource.getId());
        double percentageOfAllocation = resource.getAllocation().getPercentage();
        double costPerHour = resource.getCost().getCost();
        String projectRole = resource.getRole().toString();
        String endDate = resource.getEndDate().toString();

        return new ResourceJpa(id, endDate, percentageOfAllocation, costPerHour, projectRole);
    }

    public List<Resource> toDomain(List<ResourceJpa> resourceJpas) {
        List<Resource> resources = new ArrayList<>();

        for (ResourceJpa resourceJpa : resourceJpas) {
            Resource res = toDomain(resourceJpa);
            resources.add(res);
        }
        return resources;
    }

    public ResourceID toDomain(ResourceIDJpa idJpa) {
        return new ResourceID(idJpa.getUser(), idJpa.getProject(), LocalDate.parse(idJpa.getStartDate()));
    }

    public Resource toDomain(ResourceJpa resourceJpaSaved) {
        ResourceID id = toDomain(resourceJpaSaved.getId());
        LocalDate endDate = LocalDate.parse(resourceJpaSaved.getEndDate());
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(resourceJpaSaved.getAllocation());
        CostPerHour costPerHour = new CostPerHour(resourceJpaSaved.getCost());
        ProjectRole projectRole = ProjectRole.valueOf(resourceJpaSaved.getRole());

        return new Resource(id, endDate, percentageOfAllocation, costPerHour, projectRole);
    }

}
