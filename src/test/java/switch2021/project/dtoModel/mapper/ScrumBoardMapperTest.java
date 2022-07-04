//package switch2021.project.dtoModel.mapper;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import switch2021.project.dtoModel.dto.old.UserStoryStatusDTO;
//import switch2021.project.dtoModel.mapper.old.ScrumBoardMapper;
//import switch2021.project.model.UserStory.UserStory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ScrumBoardMapperTest {
//
//
//    @Test
//    @DisplayName("Test DTO creation")
//    public void scrumBoardDTOTest() {
//        //Arrange
//        ScrumBoardMapper mapper = new ScrumBoardMapper();
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        String projectID = "Project_2022_1";
//        UserStory userStory = new UserStory(projectID, userStoryId1, "As a PO, i want to test this string", 5, "description", 5);
//
//        //Act
//        UserStoryStatusDTO dto = mapper.toDTO(userStory);
//
//        //Assert
//        assertEquals(dto.getTitle().getTitleUs(), userStory.getTitle().getTitleUs());
//        assertEquals("As a PO, i want to test this string", dto.getTitle().getTitleUs());
//        assertEquals(dto.getDescription().getText(), userStory.getDescription().getText());
//        assertEquals("description", dto.getDescription().getText());
//        assertEquals(dto.getPriority().getPriorityUs(), userStory.getPriority().getPriorityUs());
//        assertEquals(5, dto.getPriority().getPriorityUs());
//        assertEquals(dto.getTimeEstimate().getUsHours(), userStory.getTimeEstimate().getUsHours());
//        assertEquals(5, dto.getPriority().getPriorityUs());
//        assertEquals(dto.getUserStoryId().toString(), userStory.getUserStoryID().toString());
//        assertEquals("Project_2022_1_As a PO, i want to test this string", dto.getUserStoryId().toString());
//        assertEquals(projectID,userStory.getProjectID().getCode());
//    }
//
//    @Test
//    @DisplayName("Test DTOList creation")
//    public void scrumBoardDTOListTest() {
//        //Arrange
//        List<UserStory> list = new ArrayList<>();
//        ScrumBoardMapper mapper = new ScrumBoardMapper();
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        String projectID = "Project_2022_1";
//        UserStory userStory = new UserStory(projectID, userStoryId1, "As a PO, i want to test this string", 5, "description", 5);
//        UserStory userStory2 = new UserStory(projectID, userStoryId1, "As a VO, i want to test this string", 4, "description2", 4);
//        UserStory userStory3 = new UserStory(projectID, userStoryId1, "As a TO, i want to test this string", 3, "description3", 3);
//        list.add(userStory);
//        list.add(userStory2);
//        list.add(userStory3);
//        UserStoryStatusDTO dto = mapper.toDTO(userStory);
//        UserStoryStatusDTO dto2 = mapper.toDTO(userStory2);
//        UserStoryStatusDTO dto3 = mapper.toDTO(userStory3);
//
//
//        //Act
//        List<UserStoryStatusDTO> dtoList = mapper.toDtoList(list);
//
//        //Assert
//        assertEquals(3, dtoList.size());
//        assertEquals(dto.getTitle(), dtoList.get(0).getTitle());
//        assertEquals(dto2.getDescription(), dtoList.get(1).getDescription());
//        assertEquals(dto3.getUserStoryId(), dtoList.get(2).getUserStoryId());
//
//    }
//}
