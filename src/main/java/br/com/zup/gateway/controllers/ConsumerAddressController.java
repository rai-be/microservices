package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDto;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/information")
public class ConsumerAddressController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ConsumerAddressController.class);

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    AddressClient addressClient;

    @PostMapping("/consumer")
    public ResponseEntity<ConsumerResponseDTO> registerConsumer(@RequestBody ConsumerRegisterDTO registerDTO) {
        logger.info("Starting consumer registration: {}", registerDTO);
        try {
            ConsumerResponseDTO responseDTO = consumerClient.registerConsumerClient(registerDTO);
            logger.info("Consumer registration completed successfully: {}", responseDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("Error when registering consumer");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consumer/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> getConsumer(@PathVariable String consumerId) {
        logger.info("Seeking consumer with ID: {}", consumerId);
        try {
            ConsumerResponseDTO responseDTO = consumerClient.getConsumer(consumerId);
            logger.info("Consumer found: {}", responseDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error when searching for consumer with ID: {}", consumerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/consumer/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@PathVariable String consumerId, @RequestBody ConsumerRegisterDTO registerDTO) {
        logger.info("Updating consumer with ID: {} and data: {}", consumerId, registerDTO);
        try {
            ConsumerResponseDTO responseDTO = consumerClient.updateConsumerClient(consumerId, registerDTO);
            logger.info("Consumer updated successfully: {}", responseDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error updating consumer with ID: {}", consumerId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/consumer/{consumerId}")
    public ResponseEntity<Void> deleteConsumer(@PathVariable String consumerId) {
        logger.info("Deleting consumer with ID: {}", consumerId);
        try {
            consumerClient.deleteConsumer(consumerId);
            logger.info("Consumer with ID: {} successfully deleted", consumerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Error when deleting consumer with ID: {}", consumerId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/address")
    public ResponseEntity<AddressResponseDTO> registerAddress(@RequestBody AddressRegisterDto registerDto) {
        logger.info("Starting address registration: {}", registerDto);
        try {
            AddressResponseDTO responseDTO = addressClient.registerAddress(registerDto);
            logger.info("Address registration completed successfully: {}", responseDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("Error registering address");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> getAddress(@PathVariable String addressId) {
        logger.info("Searching for address with ID: {}", addressId);
        try {
            AddressResponseDTO responseDTO = addressClient.getAddress(addressId);
            logger.info("Address found: {}", responseDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error when searching for address with ID: {}", addressId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable String addressId, @RequestBody AddressRegisterDto registerDto) {
        logger.info("Updating address with ID: {} and data: {}", addressId, registerDto);
        try {
            AddressResponseDTO responseDTO = addressClient.updateAddress(addressId, registerDto);
            logger.info("Address updated successfully: {}", responseDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error updating address with ID: {}", addressId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String addressId) {
        logger.info("Deleting address with ID: {}", addressId);
        try {
            addressClient.deleteAddress(addressId);
            logger.info("Address with ID: {} successfully deleted", addressId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("Error when deleting address with ID: {}", addressId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
