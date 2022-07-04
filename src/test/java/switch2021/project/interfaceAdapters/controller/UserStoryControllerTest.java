package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.UserStoryService;
import switch2021.project.dtoModel.dto.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class UserStoryControllerTest {

    @MockBean
    private UserStoryService service;

    @InjectMocks
    private UserStoryController controller;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAndSaveUSSuccess() {
        //Arrange
        UserStoryDTO dto = mock(UserStoryDTO.class);
        OutputUserStoryDTO outputDto = mock(OutputUserStoryDTO.class);
        when(service.createAndSaveUserStory(dto)).thenReturn(outputDto);
        ResponseEntity<Object> expected = new ResponseEntity<>(outputDto, HttpStatus.CREATED);
        //Act
        ResponseEntity<Object> result = controller.createAndSaveUserStory(dto);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void createAndSaveUSFail() {
        //Arrange
        UserStoryDTO dto = mock(UserStoryDTO.class);
        Exception exception = mock(NullPointerException.class);
        when(service.createAndSaveUserStory(dto)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.createAndSaveUserStory(dto);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void showAllUserStoriesSuccess() {
        //Arrange
        OutputUserStoryDTO dto1 = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO dto2 = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO dto3 = mock(OutputUserStoryDTO.class);
        List<OutputUserStoryDTO> userStoriesDto = new ArrayList<>();
        userStoriesDto.add(dto1);
        userStoriesDto.add(dto2);
        userStoriesDto.add(dto3);
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(userStoriesDto);
        when(service.getAllUserStories()).thenReturn(collection);
        ResponseEntity<Object> expected = new ResponseEntity<>(collection, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.getAllUserStories();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void showAllUserStoriesEmpty() {
        //Arrange
        List<OutputUserStoryDTO> userStoriesDto = new ArrayList<>();
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(userStoriesDto);
        when(service.getAllUserStories()).thenReturn(collection);
        HttpStatus expected = HttpStatus.NOT_FOUND;
        //Act
        ResponseEntity<Object> result = controller.getAllUserStories();
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void showAllUserStoriesFail() {
        //Arrange
        when(service.getAllUserStories()).thenReturn(null);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.getAllUserStories();
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void consultProductBacklogSuccess() throws Exception {
        //Arrange
        String projId = "Project_2022_1";
        OutputUserStoryDTO dto1 = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO dto2 = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO dto3 = mock(OutputUserStoryDTO.class);
        List<OutputUserStoryDTO> userStoriesDto = new ArrayList<>();
        userStoriesDto.add(dto1);
        userStoriesDto.add(dto2);
        userStoriesDto.add(dto3);
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(userStoriesDto);
        when(service.consultProductBacklog(projId)).thenReturn(collection);
        ResponseEntity<Object> expected = new ResponseEntity<>(collection, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.consultProductBacklog(projId);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void consultProductBacklogEmpty() throws Exception {
        //Arrange
        String projId = "Project_2022_1";
        List<OutputUserStoryDTO> userStoriesDto = new ArrayList<>();
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(userStoriesDto);
        when(service.consultProductBacklog(projId)).thenReturn(collection);
        HttpStatus expected = HttpStatus.NOT_FOUND;
        //Act
        ResponseEntity<Object> result = controller.consultProductBacklog(projId);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void consultProductBacklogFail() throws Exception {
        //Arrange
        String projId = "Project_2022_1";
        when(service.consultProductBacklog(projId)).thenReturn(null);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.consultProductBacklog(projId);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void showUserStoryRequestedSuccess() {
        //Arrange
        String id = "Project_2022_1";
        OutputUserStoryDTO dto = mock(OutputUserStoryDTO.class);
        when(service.showAUserStory(id)).thenReturn(dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.getUserStoryRequested(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void showUserStoryRequestedFail() {
        //Arrange
        String id = "Project_2022_1";
        Exception exception = mock(NullPointerException.class);
        when(service.showAUserStory(id)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.getUserStoryRequested(id);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void estimateEffortSuccess() {
        //Arrange
        String id = "Project_2022_1";
        UpdateUserStoryDTO updateDto = mock(UpdateUserStoryDTO.class);
        OutputUserStoryDTO dto = mock(OutputUserStoryDTO.class);
        when(service.updateUSData(id, updateDto)).thenReturn(dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.estimateEffort(id, updateDto);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void estimateEffortFail() {
        //Arrange
        String id = "Project_2022_1";
        UpdateUserStoryDTO updateDto = mock(UpdateUserStoryDTO.class);
        Exception exception = mock(NullPointerException.class);
        when(service.updateUSData(id, updateDto)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.estimateEffort(id, updateDto);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void startUSSuccess() throws Exception {
        //Arrange
        String id = "Project_2022_1";
        OutputUserStoryDTO dto = mock(OutputUserStoryDTO.class);
        when(service.startUserStory(id)).thenReturn(dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.startUserStory(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void startUsFail() throws Exception {
        //Arrange
        String id = "Project_2022_1";
        Exception exception = mock(NullPointerException.class);
        when(service.startUserStory(id)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.startUserStory(id);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void cancelUSSuccess() {
        //Arrange
        String id = "Project_2022_1";
        OutputUserStoryDTO dto = mock(OutputUserStoryDTO.class);
        when(service.cancelUserStory(id)).thenReturn(dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.cancelUserStory(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void cancelUsFail() {
        //Arrange
        String id = "Project_2022_1";
        Exception exception = mock(NullPointerException.class);
        when(service.cancelUserStory(id)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.cancelUserStory(id);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void finishUSSuccess() {
        //Arrange
        String id = "Project_2022_1";
        OutputUserStoryDTO dto = mock(OutputUserStoryDTO.class);
        when(service.finishUserStory(id)).thenReturn(dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.finishUserStory(id);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void finishUsFail() {
        //Arrange
        String id = "Project_2022_1";
        Exception exception = mock(NullPointerException.class);
        when(service.finishUserStory(id)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.finishUserStory(id);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void refineUSSuccess() {
        //Arrange
        String id = "Project_2022_1";
        UserStoryDTO inDto = mock(UserStoryDTO.class);
        OutputUserStoryDTO dto = mock(OutputUserStoryDTO.class);
        OutputUserStoryDTO refinedDto = mock(OutputUserStoryDTO.class);
        List<OutputUserStoryDTO> userStoriesDto = new ArrayList<>();
        userStoriesDto.add(dto);
        userStoriesDto.add(refinedDto);
        CollectionModel<OutputUserStoryDTO> collection = CollectionModel.of(userStoriesDto);
        when(service.refineUserStory(id, inDto)).thenReturn(collection);
        ResponseEntity<Object> expected = new ResponseEntity<>(collection, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.refineUserStory(id, inDto);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void refineUSFail() {
        //Arrange
        String id = "Project_2022_1";
        UserStoryDTO inDto = mock(UserStoryDTO.class);
        Exception exception = mock(NullPointerException.class);
        when(service.refineUserStory(id, inDto)).thenThrow(exception);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Act
        ResponseEntity<Object> result = controller.refineUserStory(id, inDto);
        //Assert
        assertEquals(expected, result.getStatusCode());
    }

    @Test
    public void deleteAUserStorySucess() {
        //Arrange
        String id = "Project_2022_1";
        ResponseMessage responseMessage = mock(ResponseMessage.class);
        ResponseEntity<Object> expected = new ResponseEntity<>(responseMessage, HttpStatus.OK);
        //Act
        ResponseEntity<Object> result = controller.deleteAUserStory(id);
        //Assert
        assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
    }
}


