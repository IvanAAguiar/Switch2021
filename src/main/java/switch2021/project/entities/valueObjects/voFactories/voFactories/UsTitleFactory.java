package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUsTitleFactory;
import switch2021.project.entities.valueObjects.vos.UsTitle;

@Component
public class UsTitleFactory implements IUsTitleFactory {
    @Override
    public UsTitle create(String usTitle) {
        return new UsTitle(usTitle);
    }
}
