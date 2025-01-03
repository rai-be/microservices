package br.com.zup.gateway;

import br.com.zup.gateway.controllers.dtos.AddressDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDto;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsumerAddressServiceTest {

    @InjectMocks
    private ConsumerAddressService consumerAddressService;

    @Mock
    private ConsumerClient consumerClient;

    @Mock
    private AddressClient addressClient;

    @Test
    void testRegisterConsumerAddress() {
        ConsumerAddressRegisterDTO inputDto = new ConsumerAddressRegisterDTO();
        inputDto.setName(" Frodo");
        inputDto.setEmail("frodo@45.com.br");
        inputDto.setAge("33");
        AddressDTO address = new ConsumerAddressRegisterDTO.AddressDTO();
        address.setCity("City");
        address.setState("State");
        address.setStreet("Street");
        address.setZipCode("12345");
        inputDto.setAddress((ConsumerAddressRegisterDTO.AddressDTO) address);


        ConsumerResponseDTO mockConsumerResponse = new ConsumerResponseDTO();
        mockConsumerResponse.setId("123");
        mockConsumerResponse.setName("Frodo");
        when(consumerClient.registerConsumerClient(any(ConsumerRegisterDTO.class))).thenReturn(mockConsumerResponse);

        AddressResponseDTO mockAddressResponse = new AddressResponseDTO();
        mockAddressResponse.setId("456");
        mockAddressResponse.setConsumerId("123");
        when(addressClient.registerAddress(any(AddressRegisterDto.class))).thenReturn(mockAddressResponse);

        ConsumerAddressResponseDTO result = consumerAddressService.registerConsumerAddress(inputDto);

        assertEquals("Frodo", result.getConsumerResponseDTO().getName());
        assertEquals("456", result.getAddressResponseDTO().getId());

        verify(consumerClient).registerConsumerClient(any(ConsumerRegisterDTO.class));
        verify(addressClient).registerAddress(any(AddressRegisterDto.class));
    }
}
