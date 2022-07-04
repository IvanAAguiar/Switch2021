package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.TaskEffortDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;

@Component
public class EffortFactory implements IEffortFactory {
    @Autowired
    private IEffortHoursFactory hoursFactory;
    @Autowired
    private IEffortMinutesFactory minutesFactory;
    @Autowired
    private IEffortDateFactory dateFactory;
    @Autowired
    private IDescriptionFactory descriptionFactory;
    @Autowired
    private IAttachmentFactory attachmentFactory;

    @Override
    public TaskEffort createTaskEffort(TaskEffortDTO taskEffortDTO) {
        Hours hours = hoursFactory.createEffortHours(taskEffortDTO.effortHours);
        Minutes minutes = minutesFactory.createEffortMinutes(taskEffortDTO.effortMinutes);
        Date date = dateFactory.createEffortDate(taskEffortDTO.effortDate);
        Description comment = descriptionFactory.createDescription(taskEffortDTO.comment);
        Attachment attachment = attachmentFactory.createAttachment(taskEffortDTO.attachment);
        return new TaskEffort(hours, minutes, date, comment, attachment);
    }
}
