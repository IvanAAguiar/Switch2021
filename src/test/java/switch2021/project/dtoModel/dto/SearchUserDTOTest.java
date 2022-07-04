package switch2021.project.dtoModel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchUserDTOTest {
    @Test
    void getSystemUserID() {
        //Arrange
        String name = "zbz@mymail.com";
        String function = "Project_2022_3";
        String profile = "TeamMember";

        SearchUserDTO dtoAll = new SearchUserDTO(name, function, profile);
        //Act + Assert
        assertEquals(name, dtoAll.getName());
        assertEquals(function, dtoAll.getFunction());
        assertEquals(profile, dtoAll.getProfile());
    }
}
