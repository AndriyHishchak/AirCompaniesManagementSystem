package com.AirCompanies.rest;

import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.Dto.FlightDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.Flight;
import com.AirCompanies.service.Impl.AirplaneServiceImpl;
import com.AirCompanies.service.Impl.FlightServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight/")
public class FlightRestController {

    private final FlightServiceImpl flightService;


    public FlightRestController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @GetMapping("all")
    public List<FlightDto> getAll() {
        return flightService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<FlightDto> getById(@PathVariable(name = "id") Long id) {
        Flight flight = flightService.findById(id);

        if(flight == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        FlightDto result = FlightDto.fromFlight(flight);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("findByStatus")
    public List<FlightDto> findByFlightToStatus(@RequestParam(name = "status") String status) {
        return flightService.findByFlightToStatus(status);
    }

    @PostMapping("create")
    public Flight registration (@RequestBody Flight flight,
                                  @RequestParam("airCompanyID") AirCompany airCompany,
                                  @RequestParam("airplaneID") Airplane airplane){
        flight.setAirCompany(airCompany);
        flight.setAirplane(airplane);

        Flight createdFlight = flightService.create(flight);
        //AirCompanyDto result = AirCompanyDto.fromAirCompany(createdAirCompany);
        return createdFlight;
    }
    @PatchMapping("{id}/update/status")
    public ResponseEntity<FlightDto> updateCompany(@PathVariable("id")Long id,
                                                    @RequestParam("status") String status) {
        FlightDto result = FlightDto.fromFlight(flightService.updateStatus(id,status));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PatchMapping("{id}/update/airplane")
    public ResponseEntity<FlightDto> updateCompany(@PathVariable("id")Long id,
                                                   @RequestParam("airplaneID") Airplane airplane) {
        FlightDto result = FlightDto.fromFlight(flightService.updateAirplane(id,airplane));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<FlightDto> update(@PathVariable("id")Long id,
                          @RequestBody Flight flightPath) {
        Flight flightUpdate = flightService.update(id,flightPath);
        FlightDto result = FlightDto.fromFlight(flightUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        try {
            flightService.deleteFlight(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        try {
            flightService.deleteAllFlight();
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
}
