package switch2021.project.dataModel.JPA.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.JPA.UserJpa;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserJpaAssembler {

    public UserJpa toData(User user) {

        UserID userId = user.getUserId();
        String userName = user.getUserName().getText();
        String photo = user.getPhoto().getExtension();
        String function = user.getFunction().getText();
        String password = user.getEncryptedPassword().getText();
        String isActive = user.isActive() ? "True" : "False";
        List<UserProfileID> userProfileIDList = user.getAssignedProfiles();
        List<Request> requestList = user.getRequestedProfiles();
 /*
        for (Request request : requestList) {
            String requestDate = null;
            if (!(request.getRequestDate() == null)) {
                requestDate = request.getRequestDate().toString();
            }
            RequestJpa requestJpa = new RequestJpa(request.getProfileIdRequested(), requestDate);
            userJpa.getRequests().add(requestJpa);
        */
        return new UserJpa(userId, userName, function, photo, password, isActive, userProfileIDList,requestList);
    }

    public List<UserJpa> toData(List<User> users) {
        List<UserJpa> userJpas = new ArrayList<>();

        for (User user : users) {
            userJpas.add(toData(user));
        }
        return userJpas;
    }

    public User toDomain (UserJpa userJpa) {

        UserID userID = userJpa.getEmail();
        Name userName = new Name(userJpa.getUserName());
        Photo photo = new Photo(userJpa.getPhoto());
        Function function = new Function(userJpa.getFunction());
        Description password = new Description(userJpa.getPassword());
        boolean isActive = userJpa.getIsActive().contains("True");
        List<UserProfileID> userProfileIDList = userJpa.getAssignedIDProfiles();
        List<Request> requests = userJpa.getRequests();

        /*List<RequestJpa> requestJpaList = userJpa.getRequests();
        for (RequestJpa requestJpa : requestJpaList) {
            LocalDate requestDate = null;
            if (!(requestJpa.getRequestDate() == null)) {
                requestDate = LocalDate.parse(requestJpa.getRequestDate());
            }
            Request request = new Request(requestDate, requestJpa.getProfileIdRequested());
            user.getRequestedProfiles().add(request);
        }

         */
        return new User(userID, userName, photo, password, function, isActive, userProfileIDList, requests);
    }

    public List<User> toDomain(List<UserJpa> userJpas) {
        List<User> users = new ArrayList<>();

        for (UserJpa userJpa : userJpas) {
            users.add(toDomain(userJpa));
        }
        return users;
    }
}
