package com.AirCompanies.rest;

import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.TypeAirplane;
import com.AirCompanies.service.Impl.AirplaneServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("airplanes/")
public class AirplaneRestController {

    private final AirplaneServiceImpl airplaneService;


    public AirplaneRestController(AirplaneServiceImpl airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping()
    public List<AirplaneDto> getAll(@RequestParam(value = "name",required = false) Optional<String> name,
                                    @RequestParam(value = "uuid",required = false) Optional<String> uuid) {
        return airplaneService.getAll( name,uuid );
    }

    @GetMapping("{id}")
    public ResponseEntity<AirplaneDto> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(airplaneService.findById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> registration (@RequestBody Airplane airplane,
                                           @RequestParam(value = "airCompanyID") Optional<AirCompany> airCompany){
        AirplaneDto createdAirplane = airplaneService.create(airplane,airCompany);
        return ResponseEntity.created( URI.create("/airplanes/" + createdAirplane.getId())).build();

    }
    @PatchMapping("{id}/update/parameters")
    public ResponseEntity<AirplaneDto> updateCompany(@PathVariable("id")Long id,
                                                     @RequestParam(value = "airCompanyID",required = false) Optional<AirCompany> airCompany,
                                                     @RequestParam(value = "typeAirplane",required = false) Optional<TypeAirplane> typeAirplane,
                                                     @RequestParam(value = "name",required = false) Optional<String> name,
                                                     @RequestParam(value = "numberOfFlights",required = false) Optional<Integer> numberOfFlights,
                                                     @RequestParam(value = "flightDistance",required = false) Optional<Double> flightDistance,
                                                     @RequestParam(value = "fuelCapacity",required = false) Optional<Double> fuelCapacity) {
        return new ResponseEntity<>(airplaneService.updateParameterAirplane(id,airCompany,typeAirplane,name,numberOfFlights,flightDistance,fuelCapacity), HttpStatus.OK);
    }
    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<AirplaneDto> updateTypeAirplane(@PathVariable("id")Long id,
                                                          @RequestBody Airplane airplaneRefresh) {
        return new ResponseEntity<>(airplaneService.update(id, airplaneRefresh), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
            airplaneService.deleteAirplane(id);
    }

    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
            airplaneService.deleteAllAirplane();
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException (NotFoundException exception) {
//                ResponseEntity.ok(exception.getMessage());
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
