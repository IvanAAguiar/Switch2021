package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IAttachmentFactory;
import switch2021.project.entities.valueObjects.vos.Attachment;

@Component
public class AttachmentFactory implements IAttachmentFactory {
    @Override
    public Attachment createAttachment(String attachment) {
        return new Attachment(attachment);
    }
}
