package switch2021.project.interfaceAdapters.repositories;


class TaskRepositoryTest {

/*    @Test
    void createTaskUSTest() {

        UserProfile profile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());

        ResourceReeng resource = new ResourceReeng(new ResourceId(user.getUserId(), new ProjectID("Project_2022_1")));


        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);

        TaskContainerID taskContainerID = userStory.getUserStoryID();

        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));

        TaskReeng task = new TaskReeng(taskID);
        task.setName(new Name("Oscar"));
        task.setDescription(new Description("Isto é uma descrição"));
        task.setEffortEstimate(new EffortEstimate(12));
        task.setType(TaskTypeEnum.Design);
        task.setResponsible(resource.getId());
        task.setTaskContainerID(taskContainerID);

        TaskRepository x = new TaskRepository();
        x.save(task);

        assertEquals("Project_2022_1_As a PO, i want to test this string", task.getTaskContainerID().toString());

    }

 */

//    @Test
//    void createTaskSprint() {
//
//        ResourceID resourceIDReeng = new ResourceID(new UserID(new Email("manuelbras@beaver.com")), new ProjectID("Project_2022_1"), LocalDate.of(2022, 1, 1) );
//
//        TaskContainerID taskContainerID = new SprintID("Project_2022_1_Sprint 1");
//
//        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));
//
//        TaskReeng task = new TaskReeng(taskID);
//        task.setDescription(new Description("Isto é uma descrição"));
//        task.setEffortEstimate(new EffortEstimate(12));
//        task.setType(TaskTypeEnum.Design);
//        task.setResponsible(resourceIDReeng);
//
//
//        TaskRepository x = new TaskRepository();
//        x.save(task);
//
//        List<TaskReeng> taskL =  new ArrayList<>();
//        taskL.add(task);
//
//        List<TaskReeng> taskLe =  x.findAll();
//
//        assertEquals("Project_2022_1_Sprint 1", task.getIdTask().getTaskContainerID().toString());
//        assertEquals(taskL, x.getTaskList());
//        assertEquals(taskLe, x.getTaskList());
//
//    }
//    @Test
//    void createTaskSprint() {
//
//        ResourceID resourceIDReeng = new ResourceID(new SystemUserID(new Email("manuelbras@beaver.com")), new ProjectID("Project_2022_1"), LocalDate.of(2022, 1, 1) );
//
//        TaskContainerID taskContainerID = new SprintID("Project_2022_1_Sprint 1");
//
//        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));
//
//        TaskReeng task = new TaskReeng(taskID);
//        task.setDescription(new Description("Isto é uma descrição"));
//        task.setEffortEstimate(new EffortEstimate(12));
//        task.setType(TaskTypeEnum.Design);
//        task.setResponsible(resourceIDReeng);
//
//
//        TaskRepository x = new TaskRepository();
//        x.save(task);
//
//        List<TaskReeng> taskL =  new ArrayList<>();
//        taskL.add(task);
//
//        List<TaskReeng> taskLe =  x.findAll();
//
//        assertEquals("Project_2022_1_Sprint 1", task.getIdTask().getTaskContainerID().toString());
//        assertEquals(taskL, x.getTaskList());
//        assertEquals(taskLe, x.getTaskList());
//
//    }
}