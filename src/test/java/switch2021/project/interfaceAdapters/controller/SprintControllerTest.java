package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.SprintService;
import switch2021.project.dtoModel.dto.OutputSprintDTO;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SprintControllerTest {

    @MockBean
    SprintService sprintService;

    @InjectMocks
    SprintController underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldShowSprints() {
        //Arrange
        OutputSprintDTO outputSprintDTO = mock(OutputSprintDTO.class);
        when(sprintService.showAllSprints()).thenReturn(CollectionModel.of(List.of(outputSprintDTO)));
        //Act
        ResponseEntity<?> response = underTest.showSprints();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void showAllSprintsFail() {
        //Act
        assertThrows(Exception.class, () -> {
            //Arrange
            Exception exception = mock(Exception.class);
            when(exception.getLocalizedMessage()).thenReturn("test");
            when(sprintService.showAllSprints()).thenThrow(exception);
            //Act
            underTest.showSprints();
        });
    }

    @Test
    void shouldFailtToShowSprints() {
        //Arrange
        when(sprintService.showAllSprints()).thenReturn(CollectionModel.empty());
        //Act
        ResponseEntity<?> response = underTest.showSprints();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);

    }

    @Test
    void shouldFailtToShowSprints2() {
        //Arrange
        when(sprintService.showAllSprints()).thenReturn(null);
        //Act
        ResponseEntity<?> response = underTest.showSprints();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);

    }

}
