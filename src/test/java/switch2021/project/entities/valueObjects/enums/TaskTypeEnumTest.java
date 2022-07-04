package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeEnumTest {
    @Test
    void values() {
        TaskTypeEnum[] valoresType = TaskTypeEnum.values();
        TaskTypeEnum x = valoresType[1];

        assertEquals(TaskTypeEnum.DOCUMENTATION, x);
        assertNotEquals(TaskTypeEnum.DESIGN, x);
        assertEquals("DOCUMENTATION", x.toString());
    }

    @Test
    void valueOf() {
        TaskTypeEnum x = TaskTypeEnum.valueOf("DOCUMENTATION");

        assertEquals(TaskTypeEnum.DOCUMENTATION, x);
    }
}