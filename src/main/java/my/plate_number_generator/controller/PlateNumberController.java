package my.plate_number_generator.controller;

import lombok.extern.slf4j.Slf4j;

import my.plate_number_generator.dto.PlateNumberDto;
import my.plate_number_generator.entity.PlateNumber;
import my.plate_number_generator.service.PlateNumberService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/number")
public class PlateNumberController {
    private final PlateNumberService plateNumberService;
    private final ModelMapper modelMapper;

    public PlateNumberController(PlateNumberService plateNumberService, ModelMapper modelMapper) {
        this.plateNumberService = plateNumberService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandomPlateNumber() {
        PlateNumber randomPlateNumber = plateNumberService.createRandomPlateNumber();
        log.info("A random license plate was created: {}", randomPlateNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(convertToPlateNumberDto(randomPlateNumber).formatToType1());
    }

    @GetMapping("/next")
    public ResponseEntity<String> getNextPlateNumber() {
        Optional<PlateNumber> lastPlateNumber = plateNumberService.getLastPlateNumber();
        PlateNumber nextPlateNumber = plateNumberService.createNextPlateNumber(lastPlateNumber);
        log.info("A next license plate was created: {}", nextPlateNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(convertToPlateNumberDto(nextPlateNumber).formatToType1());
    }

    private PlateNumberDto convertToPlateNumberDto(PlateNumber plateNumber) {
        return modelMapper.map(plateNumber, PlateNumberDto.class);
    }
}
