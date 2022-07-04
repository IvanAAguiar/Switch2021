package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateDataDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String userName = "TeamMember";
        String function = "TeamMember";
        String photo = "TeamMember";
        String oldPassword = "TeamMember";
        String newPassword = "TeamMember";

        UpdateDataDTO dtoAll = new UpdateDataDTO(userName,function,photo,oldPassword,newPassword);
        //Act + Assert
        assertEquals(userName, dtoAll.getUserName());
        assertEquals(function, dtoAll.getFunction());
        assertEquals(photo, dtoAll.getPhoto());
        assertEquals(oldPassword, dtoAll.getOldPassword());
        assertEquals(newPassword, dtoAll.getNewPassword());
    }
}
