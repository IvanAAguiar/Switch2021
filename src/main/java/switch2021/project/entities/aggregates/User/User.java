package switch2021.project.entities.aggregates.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.utils.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@AllArgsConstructor
public class User implements Entity<User> {

    /**
     * Attributes of systemUser´s class
     */
    private final UserID userId;
    private Name userName;
    private Photo photo;
    private Description encryptedPassword;
    private Function function;
    private boolean isActive;
    private List<UserProfileID> assignedIdProfiles;
    private List<Request> requestedProfiles;


    /**
     * Constructor
     */
    public User(UserID userId, Name userName, Photo photo, Password password,
                Password passwordConfirmation,
                Function function, UserProfileID visitorID) {
        this.userId = userId;
        this.userName = userName;
        this.photo = photo;
        this.function = function;
        this.isActive = false;
        this.assignedIdProfiles = new ArrayList<>();
        this.requestedProfiles = new ArrayList<>();
        assignValidatedPassword(password, passwordConfirmation);
        assignProfileVisitor(visitorID);
    }

    public User(UserID userId, Name userName, Photo photo, Description encryptedPassword,
                Function function, boolean isActive, List<UserProfileID> assignedIdProfiles) {
        this.userId = userId;
        this.userName = userName;
        this.photo = photo;
        this.encryptedPassword = encryptedPassword;
        this.function = function;
        this.isActive = isActive;
        this.assignedIdProfiles = Collections.unmodifiableList(assignedIdProfiles);
        this.requestedProfiles = new ArrayList<>();
    }


    /**
     * Getting Methods (outside lombok)
     */
    public List<UserProfileID> getAssignedProfiles() {
        return new ArrayList<>(assignedIdProfiles);
    }

    public List<Request> getRequestedProfiles() {
        return new ArrayList<>(requestedProfiles);
    }


    /**
     * Assigns
     */
    private void assignName(Name userName) {
        this.userName = userName;
    }

    private void assignPhoto(Photo photo) {
        this.photo = photo;
    }

    private void assignFunction(Function function) {
        this.function = function;
    }

    private void assignProfileVisitor(UserProfileID profileId) {
        if (profileId.getUserProfileName().getText().trim().equalsIgnoreCase("visitor")) {
            this.assignedIdProfiles.add(profileId);
        } else {
            throw new IllegalArgumentException("at registration visitor profile must be assigned!");
        }
    }


    /**
     * Password validate if passwords are equal and encrypt and assign it to the System User
     */
    private void assignValidatedPassword(Password newPassword, Password newPasswordConfirmation) {
        if (newPassword.equals(newPasswordConfirmation)) {
            this.encryptedPassword = encryptPassword(newPassword);
        } else {
            throw new IllegalArgumentException("passwords not match");
        }
    }


    /**
     * Password encryption
     */
    private Description encryptPassword(Password password) {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < password.getPwd().length(); i++) {
            codigoASCII = password.getPwd().charAt(i) + 99;
            stringBuilder.append((char) codigoASCII);
        }
        return new Description(stringBuilder.toString());
    }

    private Description decryptPassword() {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.encryptedPassword.getText().length(); i++) {
            codigoASCII = this.encryptedPassword.getText().charAt(i) - 99;
            stringBuilder.append((char) codigoASCII);
        }
        return new Description(stringBuilder.toString());
    }


    /**
     * Method to update old password with the new password
     */
    public void updatePassword(String oldPassword, String newPassword) {
        if (validateOldPassword(oldPassword)) {
            this.encryptedPassword = encryptPassword(new Password(newPassword));
        } else {
            throw new IllegalArgumentException("The password is incorrect!");
        }
    }


    /**
     * Method to validate the old password from the UI with thew old password from the System User
     */
    private boolean validateOldPassword(String oldPassword) {
        Description password = new Description(oldPassword);
        return decryptPassword().equals(password);
    }


    /**
     * Request Creation Method
     */
    public boolean createProfileRequest(UserProfileID profileId) {
        Request newRequest = new Request(profileId);
        return this.requestedProfiles.add(newRequest);
    }


    /**
     * Method to edit Personal Data
     */
    public void editPersonalData(String userName, String function, String photo) {
        if (userName != null) {
            assignName(new Name(userName));
        }
        if (function != null) {
            assignFunction(new Function(function));
        }
        if (photo != null) {
            assignPhoto(new Photo(photo));
        }
    }


    /**
     * Activation Status Methods
     */
    public void activateStatus() {
        if (!this.isActive) {
            this.isActive = true;
        }
    }

    public void inactivateStatus() {
        if (this.isActive) {
            isActive = false;
        }
    }


    /**
     * Update user profiles methods
     */
    public void toAssignProfile(UserProfileID profileID) {

            this.assignedIdProfiles.add(profileID);

    }

    public void removeProfile(UserProfileID profileID) {
        this.assignedIdProfiles.remove(profileID);
    }

    public void clearProfiles(){
        this.assignedIdProfiles = new ArrayList<>();
    }


    /**
     * Validation methods
     */
    public boolean isYourEmail(String email) {
        int idxString = this.getUserId().getEmail().getEmailText().indexOf(email.toLowerCase());
        return idxString != -1;
    }

    public boolean hasName(String name) {
        return this.userName.getText().equalsIgnoreCase(name);
    }

    public boolean hasFunction(String function) {
        return this.function.getText().equalsIgnoreCase(function);
    }

    public boolean hasProfile(UserProfileID profileId) {
        for (UserProfileID inCheck : assignedIdProfiles) {
            if (inCheck.equals(profileId)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(Password password) {
        return this.encryptedPassword.equals(encryptPassword(password));
    }


    /**
     * Override Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;
        return sameIdentityAs(that);
    }

    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userId.sameValueAs(other.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
