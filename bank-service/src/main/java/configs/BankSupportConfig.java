package configs;


import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "bank-support")
public class BankSupportConfig {
    private String phone;
    public String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
