package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryResult;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserStoryResultTest {

    @DisplayName("Test to compare two lists of user story results")
    @Test
    public void getUserStoryResultDescription() {
        //Act
        List<String> statusList1 = UserStoryResult.getUSResult();
        List<String> statusList2 = List.of(new String[]{"ACCEPTED", "REJECTED", "UNFINISHED"});
        //Assert
        assertEquals(statusList1, statusList2);
    }

    @DisplayName("Test to get a specific value - Accepted")
    @Test
    public void value_Accepted() {
        //Act
        UserStoryResult[] status = UserStoryResult.values();
        UserStoryResult accepted = status[0];
        //Assert
        assertEquals(UserStoryResult.ACCEPTED, accepted);
    }


    @DisplayName("Test to get a specific value - Rejected")
    @Test
    public void value_Rejected() {
        //Act
        UserStoryResult[] status = UserStoryResult.values();
        UserStoryResult rejected = status[1];
        //Assert
        assertEquals(UserStoryResult.REJECTED, rejected);
    }

    @DisplayName("Test to get a specific value - Unfinished")
    @Test
    public void value_Unfinished() {
        //Act
        UserStoryResult[] status = UserStoryResult.values();
        UserStoryResult unfinished = status[2];
        //Assert
        assertEquals(UserStoryResult.UNFINISHED, unfinished);
    }


    @DisplayName("Test to get a specific value - Fail")
    @Test
    public void value_Fail() {
        //Act
        UserStoryResult[] status = UserStoryResult.values();
        UserStoryResult unfinished = status[2];
        //Assert
        assertNotEquals(UserStoryResult.REJECTED, unfinished);
    }


    @DisplayName("Test to valueOf - Accepted")
    @Test
    public void valueOf_Accepted() {
        //Act
        UserStoryResult userStoryResult = UserStoryResult.valueOf("ACCEPTED");
        //Assert
        assertEquals(UserStoryResult.ACCEPTED, userStoryResult);
    }


    @DisplayName("Test to valueOf - Rejected")
    @Test
    public void valueOf_Rejected() {
        //Act
        UserStoryResult userStoryResult = UserStoryResult.valueOf("REJECTED");
        //Assert
        assertEquals(UserStoryResult.REJECTED, userStoryResult);
    }


    @DisplayName("Test to valueOf - Unfinished")
    @Test
    public void valueOf_Unfinished() {
        //Act
        UserStoryResult userStoryResult = UserStoryResult.valueOf("UNFINISHED");
        //Assert
        assertEquals(UserStoryResult.UNFINISHED, userStoryResult);
    }
}
