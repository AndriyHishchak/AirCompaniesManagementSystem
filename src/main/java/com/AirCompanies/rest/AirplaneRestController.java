package com.AirCompanies.rest;

import com.AirCompanies.Dto.AirplaneDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.service.Impl.AirplaneServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airplane/")
public class AirplaneRestController {

    private final AirplaneServiceImpl airplaneService;


    public AirplaneRestController(AirplaneServiceImpl airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("all")
    public List<AirplaneDto> getAll() {
        return airplaneService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<AirplaneDto> getUserId(@PathVariable(name = "id") Long id) {
        Airplane airplane = airplaneService.findById(id);

        if(airplane == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AirplaneDto result = AirplaneDto.fromAirplane(airplane);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<AirplaneDto> findByName(@PathVariable("name") String name){

        Airplane airplane = airplaneService.findByName(name);
        AirplaneDto result = AirplaneDto.fromAirplane(airplane);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("create")
    public Airplane registration (@RequestBody Airplane airplane,
                                  @RequestParam("airCompanyID") AirCompany airCompany){

        airplane.setFlights(null);
        airplane.setAirCompany(airCompany);
        Airplane createdAirplane = airplaneService.create(airplane);
        //AirCompanyDto result = AirCompanyDto.fromAirCompany(createdAirCompany);
        return createdAirplane;
    }
    @PatchMapping("{id}/update/company")
    public ResponseEntity<AirplaneDto> updateCompany(@PathVariable("id")Long id,
                                                    @RequestParam("airCompanyID") AirCompany airCompany) {
        AirplaneDto result = AirplaneDto.fromAirplane(airplaneService.updateCompany(id,airCompany));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PatchMapping("{id}/update/typeAirplane")
    public ResponseEntity<AirplaneDto> updateTypeAirplane(@PathVariable("id")Long id,
                                                   @RequestParam("typeAirplane") String typeAirplane) {
        Airplane airplaneUpdate = airplaneService.updateTypeAirplane(id, typeAirplane);

        AirplaneDto result = AirplaneDto.fromAirplane(airplaneUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AirplaneDto> update(@PathVariable("id")Long id,
                          @RequestBody Airplane airplanePath) {
        Airplane airplaneUpdate = airplaneService.update(id,airplanePath);
        AirplaneDto result = AirplaneDto.fromAirplane(airplaneUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        try {
            airplaneService.deleteAirplane(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        try {
            airplaneService.deleteAllAirplane();
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
}
