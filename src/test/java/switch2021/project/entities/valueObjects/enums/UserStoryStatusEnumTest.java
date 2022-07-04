package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryStatusEnum;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserStoryStatusEnumTest {

    @DisplayName("Test to compare two lists os user story status")
    @Test
    public void getUserStoryStatusDescription() {
        //Act
        List<String> statusList1 = UserStoryStatusEnum.getUserStoryStatusEnum();
        List<String> statusList2 = List.of(new String[] {"OPEN", "IN_PROGRESS", "FINISHED", "CANCELED", "REFINED"});
        //Assert
        assertEquals(statusList1, statusList2);
    }


    @DisplayName("Test to get a specific value - Open")
    @Test
    public void value_Open() {
        //Act
        UserStoryStatusEnum[] status = UserStoryStatusEnum.values();
        UserStoryStatusEnum open = status[0];
        //Assert
        assertEquals(UserStoryStatusEnum.OPEN, open);
    }


    @DisplayName("Test to get a specific value - In_Progress")
    @Test
    public void value_In_Progress() {
        //Act
        UserStoryStatusEnum[] status = UserStoryStatusEnum.values();
        UserStoryStatusEnum in_progress = status[1];
        //Assert
        assertEquals(UserStoryStatusEnum.IN_PROGRESS, in_progress);
    }


    @DisplayName("Test to get a specific value - Finished")
    @Test
    public void value_Finished() {
        //Act
        UserStoryStatusEnum[] status = UserStoryStatusEnum.values();
        UserStoryStatusEnum finished = status[2];
        //Assert
        assertEquals(UserStoryStatusEnum.FINISHED, finished);
    }


    @DisplayName("Test to get a specific value - Finished")
    @Test
    public void value_Canceled() {
        //Act
        UserStoryStatusEnum[] status = UserStoryStatusEnum.values();
        UserStoryStatusEnum canceled = status[3];
        //Assert
        assertEquals(UserStoryStatusEnum.CANCELED, canceled);
    }


    @DisplayName("Test to get a specific value - Refined")
    @Test
    public void value_Refined() {
        //Act
        UserStoryStatusEnum[] status = UserStoryStatusEnum.values();
        UserStoryStatusEnum refined = status[4];
        //Assert
        assertEquals(UserStoryStatusEnum.REFINED, refined);
    }


    @DisplayName("Test to get a specific value - Fail")
    @Test
    public void value_Fail() {
        //Act
        UserStoryStatusEnum[] status = UserStoryStatusEnum.values();
        UserStoryStatusEnum open = status[0];
        //Assert
        assertNotEquals(UserStoryStatusEnum.REFINED, open);
    }


    @DisplayName("Test to valueOf - Open")
    @Test
    public void valueOf_Open() {
        //Act
        UserStoryStatusEnum userStoryStatusEnum = UserStoryStatusEnum.valueOf("OPEN");
        //Assert
        assertEquals(UserStoryStatusEnum.OPEN, userStoryStatusEnum);
    }


    @DisplayName("Test to valueOf - In_Progress")
    @Test
    public void valueOf_In_Progress() {
        //Act
        UserStoryStatusEnum userStoryStatusEnum = UserStoryStatusEnum.valueOf("IN_PROGRESS");
        //Assert
        assertEquals(UserStoryStatusEnum.IN_PROGRESS, userStoryStatusEnum);
    }


    @DisplayName("Test to valueOf - Finished")
    @Test
    public void valueOf_Finished() {
        //Act
        UserStoryStatusEnum userStoryStatusEnum = UserStoryStatusEnum.valueOf("FINISHED");
        //Assert
        assertEquals(UserStoryStatusEnum.FINISHED, userStoryStatusEnum);
    }


    @DisplayName("Test to valueOf - Canceled")
    @Test
    public void valueOf_Canceled() {
        //Act
        UserStoryStatusEnum userStoryStatusEnum = UserStoryStatusEnum.valueOf("CANCELED");
        //Assert
        assertEquals(UserStoryStatusEnum.CANCELED, userStoryStatusEnum);
    }


    @DisplayName("Test to valueOf - Refined")
    @Test
    public void valueOf_Refined() {
        //Act
        UserStoryStatusEnum userStoryStatusEnum = UserStoryStatusEnum.valueOf("REFINED");
        //Assert
        assertEquals(UserStoryStatusEnum.REFINED, userStoryStatusEnum);
    }
}
