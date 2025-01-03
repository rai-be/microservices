package br.com.zup.gateway.controllers.dtos;


public class ConsumerAddressRegisterDTO {
    private String name;
    private String age;
    private String email;
    private AddressDTO address;

    public ConsumerAddressRegisterDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public static class AddressDTO extends br.com.zup.gateway.controllers.dtos.AddressDTO {
    }
}
