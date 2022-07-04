package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.ResourceID;

public interface IResourceFactory {

    Resource createResource(CreateResourceDTO resourceDTO);

    Resource createResourceByAnotherResource(ResourceID id, DefineRoleOfResourceDTO dto);
}
