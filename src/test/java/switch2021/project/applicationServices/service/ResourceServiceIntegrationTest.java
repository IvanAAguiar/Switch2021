package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ResourceServiceIntegrationTest {


    @Autowired
    ResourceService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void DefineProjectRoleSuccess () {
        String resId = "xfz@mymail.com&Project_2022_3&2022-03-05";
        String email = "xfz@mymail.com";
        String role = "ScrumMaster";
        String projid = "Project_2022_3";
        String data = "2022-03-05";
        String data2 = "2023-01-10";
        Double percentage = 0.9;
        Double cost = 20.0;

        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO(role, data,
                data2, cost, percentage);

        OutputResourceDTO expected = new OutputResourceDTO();
        expected.project = projid;
        expected.role = role;
        expected.startDate = data;
        expected.endDate = data2;
        expected.resourceID = resId;
        expected.allocation = percentage;
        expected.cost = cost;
        expected.user = email;

        OutputResourceDTO result = service.defineProjectRole(resId, dto);
        result.removeLinks();

        assertEquals(expected, result);
    }

    @Test
    void DefineProjectRoleResourceNotOnProjectTeam() {
        assertThrows(Exception.class, () -> {
        String resId = "xfz@mymail.com&Project_2022_3&2022-03-06";
        String email = "xfz@mymail.com";
        String role = "ScrumMaster";
        String projid = "Project_2022_3";
        String data = "2022-03-06";
        String data2 = "2023-01-10";
        Double percentage = 0.9;
        Double cost = 20.0;

        DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO(role, data,
                data2, cost, percentage);

        OutputResourceDTO expected = new OutputResourceDTO();
        expected.project = projid;
        expected.role = role;
        expected.startDate = data;
        expected.endDate = data2;
        expected.resourceID = resId;
        expected.allocation = percentage;
        expected.cost = cost;
        expected.user = email;

        OutputResourceDTO result = service.defineProjectRole(resId, dto);
        result.removeLinks();

        assertEquals(expected, result);
        });
    }

    @Test
    void DefineProjectRoleUserNotFound() {
        assertThrows(Exception.class, () -> {
            String resId = "xfzasd@mymail.com&Project_2022_3&2022-03-05";
            String email = "xfzasdasd@mymail.com";
            String role = "ScrumMaster";
            String projid = "Project_2022_3";
            String data = "2022-03-05";
            String data2 = "2023-01-10";
            Double percentage = 0.9;
            Double cost = 20.0;

            DefineRoleOfResourceDTO dto = new DefineRoleOfResourceDTO(role, data,
                    data2, cost, percentage);

            OutputResourceDTO expected = new OutputResourceDTO();
            expected.project = projid;
            expected.role = role;
            expected.startDate = data;
            expected.endDate = data2;
            expected.resourceID = resId;
            expected.allocation = percentage;
            expected.cost = cost;
            expected.user = email;

            OutputResourceDTO result = service.defineProjectRole(resId, dto);
            result.removeLinks();

            assertEquals(expected, result);
        });
    }

    @Test
    void CreateResourceSuccess () {
        String resId = "zmz@mymail.com&Project_2022_3&2022-03-05";
        String email = "zmz@mymail.com";
        String role = "TeamMember";
        String projid = "Project_2022_3";
        String data = "2022-03-05";
        String data2 = "2023-01-10";
        Double percentage = 0.9;
        Double cost = 20.0;

        CreateResourceDTO dto = new CreateResourceDTO(email, projid, role, data,
                data2, cost, percentage);

        OutputResourceDTO expected = new OutputResourceDTO();
        expected.project = projid;
        expected.role = role;
        expected.startDate = data;
        expected.endDate = data2;
        expected.resourceID = resId;
        expected.allocation = percentage;
        expected.cost = cost;
        expected.user = email;

        OutputResourceDTO result = service.createAndSaveResource(dto);
        result.removeLinks();

        assertEquals(expected, result);
        assertEquals(service.showProjectTeam(projid).size(), 9);
    }

    @Test
    void CreateResourceOnlyEndDateInside () {
        assertThrows(Exception.class, () -> {
            String resId = "xfz@mymail.com&Project_2022_3&2022-03-05";
            String email = "xfz@mymail.com";
            String role = "TeamMember";
            String projid = "Project_2022_3";
            String data = "2022-03-03";
            String data2 = "2023-01-09";
            Double percentage = 0.9;
            Double cost = 20.0;

            CreateResourceDTO dto = new CreateResourceDTO(email, projid, role, data,
                    data2, cost, percentage);

            OutputResourceDTO expected = new OutputResourceDTO();
            expected.project = projid;
            expected.role = role;
            expected.startDate = data;
            expected.endDate = data2;
            expected.resourceID = resId;
            expected.allocation = percentage;
            expected.cost = cost;
            expected.user = email;

            OutputResourceDTO result = service.createAndSaveResource(dto);
            result.removeLinks();

            assertEquals(expected, result);
            assertEquals(service.showProjectTeam(projid).size(), 9);
        });
    }

    @Test
    void CreateResourceOnlyStartDateInside () {
        assertThrows(Exception.class, () -> {
            String resId = "xfz@mymail.com&Project_2022_3&2022-03-05";
            String email = "xfz@mymail.com";
            String role = "TeamMember";
            String projid = "Project_2022_3";
            String data = "2022-03-06";
            String data2 = "2023-01-12";
            Double percentage = 0.9;
            Double cost = 20.0;

            CreateResourceDTO dto = new CreateResourceDTO(email, projid, role, data,
                    data2, cost, percentage);

            OutputResourceDTO expected = new OutputResourceDTO();
            expected.project = projid;
            expected.role = role;
            expected.startDate = data;
            expected.endDate = data2;
            expected.resourceID = resId;
            expected.allocation = percentage;
            expected.cost = cost;
            expected.user = email;

            OutputResourceDTO result = service.createAndSaveResource(dto);
            result.removeLinks();

            assertEquals(expected, result);
            assertEquals(service.showProjectTeam(projid).size(), 9);
        });
    }

    @Test
    void CreateResourceAfterDates () {

            String resId = "zbz@mymail.com&Project_2022_3&2022-03-05";
            String email = "zbz@mymail.com";
            String role = "TeamMember";
            String projid = "Project_2022_3";
            String data = "2023-01-17";
            String data2 = "2023-01-19";
            Double percentage = 0.1;
            Double cost = 20.0;

            CreateResourceDTO dto = new CreateResourceDTO(email, projid, role, data,
                    data2, cost, percentage);

            OutputResourceDTO expected = new OutputResourceDTO();
            expected.project = projid;
            expected.role = role;
            expected.startDate = data;
            expected.endDate = data2;
            expected.resourceID = resId;
            expected.allocation = percentage;
            expected.cost = cost;
            expected.user = email;

            OutputResourceDTO result = service.createAndSaveResource(dto);
            result.removeLinks();

            assertEquals(expected, result);
            assertEquals(service.showProjectTeam(projid).size(), 9);

    }
}
