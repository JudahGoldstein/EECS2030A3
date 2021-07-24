package A3;

public class BusinessType {
    private String Name;
    private String Phone;
    private String Fax;
    private String Email;
    private String WebsiteURL;
    private String LogoURL;
    private String Address;
    private String BusinessAdditionalInformation;

    public BusinessType(String name, String phone, String fax, String email, String websiteURL, String logoURL, String address, String businessAdditionalInformation) {
        Name = name;
        Phone = phone;
        Fax = fax;
        Email = email;
        WebsiteURL = websiteURL;
        LogoURL = logoURL;
        Address = address;
        BusinessAdditionalInformation = businessAdditionalInformation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsiteURL() {
        return WebsiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }

    public String getLogoURL() {
        return LogoURL;
    }

    public void setLogoURL(String logoURL) {
        LogoURL = logoURL;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBusinessAdditionalInformation() {
        return BusinessAdditionalInformation;
    }

    public void setBusinessAdditionalInformation(String businessAdditionalInformation) {
        BusinessAdditionalInformation = businessAdditionalInformation;
    }
}
