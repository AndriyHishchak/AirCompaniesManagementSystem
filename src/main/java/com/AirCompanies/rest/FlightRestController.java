package com.AirCompanies.rest;


import com.AirCompanies.Dto.FlightDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.*;
import com.AirCompanies.service.Impl.FlightServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights/")
public class FlightRestController {

    private final FlightServiceImpl flightService;


    public FlightRestController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @PostMapping()
    public ResponseEntity<?> registration (@RequestBody Flight flight,
                                           @RequestParam(value = "airCompanyID",required = false) AirCompany airCompany,
                                           @RequestParam(value = "airplaneID",required = false) Airplane airplane){
        FlightDto flightDto = flightService.create(flight,airCompany,airplane);
        return ResponseEntity.created( URI.create("/air-flights/" + flightDto.getId())).build();
    }

    @GetMapping()
    public List<FlightDto> getAll(@RequestParam(value = "status",required = false) Optional<Status> status,
                                  @RequestParam(value = "destinationCountry",required = false) Optional<Country> destinationCountry,
                                  @RequestParam(value = "departureCountry",required = false) Optional<Country> departureCountry,
                                  @RequestParam(value = "nameCompany",required = false) Optional<String> nameCompany){
        return flightService.getAll( status,departureCountry, destinationCountry,nameCompany);
    }

    @GetMapping("{id}")
    public ResponseEntity<FlightDto> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(flightService.findById(id),HttpStatus.OK);
    }


    @PatchMapping("{id}/update/parameters")
    public ResponseEntity<FlightDto> updateCompany(@PathVariable("id")Long id,
                                                    @RequestParam(value = "status",required = false) Optional <Status> status,
                                                    @RequestParam(value = "airCompany",required = false) Optional<AirCompany> airCompany,
                                                    @RequestParam(value = "airplane",required = false) Optional<Airplane> airplane,
                                                    @RequestParam(value = "departureCountry",required = false) Optional<Country> departureCountry,
                                                    @RequestParam(value = "destinationCountry",required = false) Optional<Country> destinationCountry,
                                                    @RequestParam(value = "distance",required = false) Optional<Double> distance,
                                                    @RequestParam(value = "estimatedFlightTime",required = false) Optional<LocalTime> estimatedFlightTime,
                                                    @RequestParam(value = "endedAt",required = false) Optional<LocalDateTime> endedAt,
                                                    @RequestParam(value = "startedAt",required = false) Optional<LocalDateTime> StartedAt,
                                                    @RequestParam(value = "delayStartAt",required = false) Optional<LocalDateTime> delayStartAt){

        return new ResponseEntity<>(flightService.updateParametersFlight(id,
                status,airCompany,airplane,departureCountry,destinationCountry,
                distance,estimatedFlightTime,endedAt,StartedAt,delayStartAt), HttpStatus.OK);
    }

   /* @PutMapping("{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable("id")Long id,
                                                   @RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.update(id,flight), HttpStatus.OK);
    }*/


    @GetMapping("RecentFlights")
    public List<FlightDto> findByRecentFlights() {
        return flightService.findByRecentFlights();
    }

    @GetMapping("completedFlights")
    public List<FlightDto> findByCompletedFlights() {
        return flightService.findByCompletedFlights();
    }

    @PatchMapping("/change/flight/status")
    public List<FlightDto> changeFlightStatus() {
        return flightService.changeFlightStatus();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
            flightService.deleteFlight(id);
    }
    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
            flightService.deleteAllFlight();
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException (NotFoundException exception) {
//                ResponseEntity.ok(exception.getMessage());
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
