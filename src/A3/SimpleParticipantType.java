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

    public String getParticipantId() {
        return ParticipantId;
    }

    public void setParticipantId(String participantId) {
        ParticipantId = participantId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPrimaryContactPhone() {
        return PrimaryContactPhone;
    }

    public void setPrimaryContactPhone(String primaryContactPhone) {
        PrimaryContactPhone = primaryContactPhone;
    }

    public String getOfficePhone() {
        return OfficePhone;
    }

    public void setOfficePhone(String officePhone) {
        OfficePhone = officePhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getWebsiteURL() {
        return WebsiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }
}
