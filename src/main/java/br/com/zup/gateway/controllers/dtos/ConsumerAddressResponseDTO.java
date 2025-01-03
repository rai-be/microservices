package br.com.zup.gateway.controllers.dtos;

import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;

public class ConsumerAddressResponseDTO {
    private String id;
    private String name;
    private String age;
    private String email;
    private AddressDTO address;

    private ConsumerResponseDTO consumerResponseDTO;
    private AddressResponseDTO addressResponseDTO;

    public ConsumerAddressResponseDTO(ConsumerResponseDTO consumerResponseDTO, AddressResponseDTO addressResponseDTO) {
        this.id = consumerResponseDTO.getId();
        this.name = consumerResponseDTO.getName();
        this.age = consumerResponseDTO.getAge();
        this.email = consumerResponseDTO.getEmail();
        this.address = new AddressDTO(addressResponseDTO);

        this.consumerResponseDTO = consumerResponseDTO;
        this.addressResponseDTO = addressResponseDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ConsumerResponseDTO getConsumerResponseDTO() {
        return consumerResponseDTO;
    }

    public AddressResponseDTO getAddressResponseDTO() {
        return addressResponseDTO;
    }
}