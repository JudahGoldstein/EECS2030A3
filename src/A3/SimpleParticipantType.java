package A3;

import java.util.UUID;

public class SimpleParticipantType {
    private String ParticipantId;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String Role;
    private String PrimaryContactPhone;
    private String OfficePhone;
    private String Email;
    private String Fax;
    private String WebsiteURL;

    public SimpleParticipantType(String participantId, String firstName, String middleName, String lastName, String role, String primaryContactPhone, String officePhone, String email, String fax, String websiteURL) {
        ParticipantId = participantId;
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        Role = role;
        PrimaryContactPhone = primaryContactPhone;
        OfficePhone = officePhone;
        Email = email;
        Fax = fax;
        WebsiteURL = websiteURL;
    }

    /**
     * Get the ID of the participant
     * @return the ID of the participant
     */
    public String getParticipantId() {
        return ParticipantId;
    }

    /**
     * Set the ID of the participant
     * @param participantId the ID of the participant
     */
    public void setParticipantId(String participantId) {
        ParticipantId = participantId;
    }

    /**
     * Get the first name of the participant
     * @return the first name of the participant
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Set the first name of the participant
     * @param firstName the first name of the participant
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     * Get the middle name of the participant
     * @return
     */
    public String getMiddleName() {
        return MiddleName;
    }

    /**
     * Set the middle name of the participant
     * @param middleName the middle name of the participant
     */
    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    /**
     * Get the last name of the participant
     * @return
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Set the last name of the participant
     * @param lastName the last name of the participant
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * Get the role of the participant
     * @return the role of the participant
     */
    public String getRole() {
        return Role;
    }

    /**
     * Set the role of the participant
     * @param role the role of the participant
     */
    public void setRole(String role) {
        Role = role;
    }

    /**
     * Get the main phone number of the participant
     * @return the main phone number of the participant
     */
    public String getPrimaryContactPhone() {
        return PrimaryContactPhone;
    }

    /**
     * Set the main phone number of the participant
     * @param primaryContactPhone the main phone number of the participant
     */
    public void setPrimaryContactPhone(String primaryContactPhone) {
        PrimaryContactPhone = primaryContactPhone;
    }

    /**
     * Get the office phone number of the participant
     * @return the office phone number of the participant
     */
    public String getOfficePhone() {
        return OfficePhone;
    }

    /**
     * Set the office phone number of the participant
     * @param officePhone the office phone number of the participant
     */
    public void setOfficePhone(String officePhone) {
        OfficePhone = officePhone;
    }

    /**
     * Get the email address of the participant
     * @return the email address of the participant
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set the email address of the participant
     * @param email the email address of the participant
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Get the fax number of the participant
     * @return the fax number of the participant
     */
    public String getFax() {
        return Fax;
    }

    /**
     * Set the fax number of the participant
     * @param fax the fax number of the participant
     */
    public void setFax(String fax) {
        Fax = fax;
    }

    /**
     * Get the website of the participant
     * @return the website of the participant
     */
    public String getWebsiteURL() {
        return WebsiteURL;
    }

    /**
     * Set the website of the participant
     * @param websiteURL the website of the participant
     */
    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }
}
