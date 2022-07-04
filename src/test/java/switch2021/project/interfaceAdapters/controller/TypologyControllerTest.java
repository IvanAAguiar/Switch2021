package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.dtoModel.dto.TypologyDTO;
import switch2021.project.applicationServices.service.TypologyService;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TypologyControllerTest {

    @InjectMocks
    TypologyController controller;
    @MockBean
    TypologyService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createNewTypologyControllerTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO inputDto = mock(TypologyDTO.class);
        TypologyDTO outputDto = mock(TypologyDTO.class);
        //Act
        when(inputDto.getDescription()).thenReturn("Test");
        when(outputDto.getDescription()).thenReturn("Test");
        when(service.createAndSaveTypology(inputDto)).thenReturn(outputDto);
        ResponseEntity<?> response = controller.createTypology(inputDto);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void createNewTypologyControllerNullDescriptionTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn(null);
        ResponseEntity<?> response = controller.createTypology(dto);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void createNewTypologyControllerEmptyDescriptionTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn("");
        ResponseEntity<?> response = controller.createTypology(dto);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void createNewTypologyControllerDuplicate() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn("Test");
        when(service.createAndSaveTypology(dto)).thenThrow(IllegalArgumentException.class);
        ResponseEntity<?> response = controller.createTypology(dto);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void getTypologyById() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(service.findTypologyRequested("test")).thenReturn(dto);
        ResponseEntity<?> response = controller.findTypologyRequested("test");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getTypologyById_TypologyDoesNotExist() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //Act
        when(service.findTypologyRequested("test")).thenThrow(IllegalArgumentException.class);
        ResponseEntity<?> response = controller.findTypologyRequested("test");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void getTypologyById_NullDto() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn(null);
        ResponseEntity<?> response = controller.findTypologyRequested("");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void getTypologyById_EmptyDto() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn("");
        ResponseEntity<?> response = controller.findTypologyRequested("");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void getTypologyById_EmptyId() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String id = "";
        //Act
        ResponseEntity<?> response = controller.findTypologyRequested(id);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void getTypologyById_nullId() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String id = null;
        //Act
        ResponseEntity<?> response = controller.findTypologyRequested(id);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void findAllTypologies() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        TypologyDTO dto1 = mock((TypologyDTO.class));
        TypologyDTO dto2 = mock((TypologyDTO.class));
        //Act
        when(service.findAllTypologies()).thenReturn(CollectionModel.of(List.of(new TypologyDTO[]{dto, dto1, dto2})));
        ResponseEntity<?> response = controller.findTypologyList();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void findAllTypologiesFail() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //Act
        when(service.findAllTypologies()).thenThrow(NullPointerException.class);
        ResponseEntity<Object> response = controller.findTypologyList();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }


    @Test
    public void deleteTypology_TypologyDoesNotExist() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyController controller1 = new TypologyController();
        //Act
        //At this point have no typologies saved in the repository!
        ResponseEntity<?> response = controller1.deleteTypology("test");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void deleteTypology_EmptyDto() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn("");
        ResponseEntity<?> response = controller.deleteTypology("");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void deleteTypology_NullDto() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(dto.getDescription()).thenReturn(null);
        ResponseEntity<?> response = controller.deleteTypology("");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }
    @Test
    public void deleteTypologyFail_EmptyId() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String id = "";
        //Act
        ResponseEntity<?> response = controller.deleteTypology(id);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

    @Test
    public void deleteTypologyFail_nullId() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String id = null;
        //Act
        ResponseEntity<?> response = controller.deleteTypology(id);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(406);
    }

}
