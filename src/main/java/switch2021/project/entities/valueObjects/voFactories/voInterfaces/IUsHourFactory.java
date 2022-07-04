package switch2021.project.entities.valueObjects.voFactories.voInterfaces;


import switch2021.project.entities.valueObjects.vos.UsHour;


public interface IUsHourFactory {
    UsHour create (double timeEstimate);
}
