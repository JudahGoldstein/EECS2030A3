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

    /**
     * Get the name of the business
     * @return the name of the business
     */
    public String getName() {
        return Name;
    }

    /**
     * Set the name of the business
     * @param name the name of the business
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Get the phone number of the business
     * @return the phone number of the business
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * Set the phone number of the business
     * @param phone the phone number of the business
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     * Get the fax number of the business
     * @return the fax number of the business
     */
    public String getFax() {
        return Fax;
    }

    /**
     * Set the fax number of the business
     * @param fax the fax number of the business
     */
    public void setFax(String fax) {
        Fax = fax;
    }

    /**
     * Get the email address of the business
     * @return the email address of the business
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set the email address of the business
     * @param email the email address of the business
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Get the website of the business
     * @return the website of the business
     */
    public String getWebsiteURL() {
        return WebsiteURL;
    }

    /**
     * Set the website of the business
     * @param websiteURL the website of the business
     */
    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }

    /**
     * Get the URL to the logo of the business
     * @return
     */
    public String getLogoURL() {
        return LogoURL;
    }

    /**
     * Set the URL to the logo of the business
     * @param logoURL the URL to the logo of the business
     */
    public void setLogoURL(String logoURL) {
        LogoURL = logoURL;
    }

    /**
     * Get the address of the business
     * @return the address of the business
     */
    public String getAddress() {
        return Address;
    }

    /**
     * Set the address of the business
     * @param address the address of the business
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     * Get any additional info about the business stored in a String
     * @return additional info about the business
     */
    public String getBusinessAdditionalInformation() {
        return BusinessAdditionalInformation;
    }

    /**
     * Set additional info about the business
     * @param businessAdditionalInformation additional info about the business in a String
     */
    public void setBusinessAdditionalInformation(String businessAdditionalInformation) {
        BusinessAdditionalInformation = businessAdditionalInformation;
    }
}
