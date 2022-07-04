package switch2021.project.dtoModel.mapper;

public class ProjectTeamMapperTest {

/*    @Test
    @DisplayName("Project Team Mapper - toDto - Create ResourceDtoSucess")
    public void ProjectTeamMappertoDto() {
        //Arrange
        Company company = new Company();
        ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();

        //create project and save it
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        company.getBusinessSectorStore().createAndAddBusinessSector("it");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Create resource
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        //Create resource
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user3, startDateMj, endDateMj, 100, .5);
        //Create resource
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user5, startDateMf, endDateMf, 100, 1);

        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manuelfernandes);

        //Act
        //create ResourceDto
        DefineRoleOfResourceDTO resourceDTOExp1 = projectTeamMapper.toDto(manuelbras);

        //Asserts
        assertEquals("", resourceDTOExp1.getRole());
        assertEquals("manuelbras", resourceDTOExp1.getUserName());
        assertEquals("2021/11/1", resourceDTOExp1.getStartDate());
        assertEquals("2022/11/15", resourceDTOExp1.getEndDate());
        assertEquals(100, resourceDTOExp1.getCostPerHour());
        assertEquals(.5, resourceDTOExp1.getPercentageOfAllocation());
    }

    @Test
    @DisplayName("Project Team Mapper - toDto - Create List ResourceDtoSucess")
    public void ProjectTeamMappertoDtoList() {
        //Arrange
        Company company = new Company();
        ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();

        //create project and save it
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        company.getBusinessSectorStore().createAndAddBusinessSector("it");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Create resource
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        //Create resource
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user3, startDateMj, endDateMj, 100, .5);
        //Create resource
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user5, startDateMf, endDateMf, 100, 1);

        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manuelfernandes);

        //Resource List
        List<Resource> projTeam = proj1.getProjectTeam().getProjectTeamList();

        //Act
        List<DefineRoleOfResourceDTO> resDtoList = projectTeamMapper.toDto(projTeam);

        //Asserts
        assertEquals("", resDtoList.get(0).getRole());
        assertEquals("manuelbras", resDtoList.get(0).getUserName());
        assertEquals("2021/11/1", resDtoList.get(0).getStartDate());
        assertEquals("2022/11/15", resDtoList.get(0).getEndDate());
        assertEquals(100, resDtoList.get(0).getCostPerHour());
        assertEquals(.5, resDtoList.get(0).getPercentageOfAllocation());
        assertEquals(3,resDtoList.size());

    }
    @Test
    @DisplayName("Project Team Mapper - toDto")
    public void ProjectTeamMappertoDtoRoleNotNull() {
        //Arrange
        Company company = new Company();

        //create project and save it
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        company.getBusinessSectorStore().createAndAddBusinessSector("it");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);

        ProjectTeamMapper projectTeamMapper1 = new ProjectTeamMapper();
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));

        //Act
        DefineRoleOfResourceDTO resourceDto = projectTeamMapper1.toDto(manuelbras);

        //Asserts
        assertNotNull(resourceDto.getRole());
        assertEquals("Team Member", resourceDto.getRole());
        assertEquals("manuelbras", resourceDto.getUserName());
        assertEquals("2021/11/1", resourceDto.getStartDate());
        assertEquals("2022/11/15", resourceDto.getEndDate());
        assertEquals(100, resourceDto.getCostPerHour());
        assertEquals(.5, resourceDto.getPercentageOfAllocation());
    }

 */
}
