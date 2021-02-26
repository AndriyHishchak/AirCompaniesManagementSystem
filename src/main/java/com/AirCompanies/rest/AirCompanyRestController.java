package com.AirCompanies.rest;

import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.service.Impl.AirCompanyServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("airCompany/")
public class AirCompanyRestController {

    private final AirCompanyServiceImpl airCompanyService;

    public AirCompanyRestController(AirCompanyServiceImpl airCompanyService) {
        this.airCompanyService = airCompanyService;
    }
    @GetMapping("all")
    public List<AirCompanyDto> getAll() {
        return airCompanyService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<AirCompanyDto> getUserId(@PathVariable(name = "id") Long id) {
        AirCompany airCompany = airCompanyService.findById(id);

        if(airCompany == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AirCompanyDto result = AirCompanyDto.fromAirCompany(airCompany);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<AirCompanyDto> findByName(@PathVariable("name") String name){

        AirCompany airCompany = airCompanyService.findByName(name);
        AirCompanyDto result = AirCompanyDto.fromAirCompany(airCompany);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("create")
    public AirCompany registration(@RequestBody AirCompany airCompany){
        airCompany.setAirplanes(null);
        airCompany.setFlights(null);
        AirCompany createdAirCompany = airCompanyService.create(airCompany);
        //AirCompanyDto result = AirCompanyDto.fromAirCompany(createdAirCompany);
        return createdAirCompany;
    }

    @PatchMapping("{id}/update/typeCompany")
    public ResponseEntity<AirCompanyDto> updateStatus(@PathVariable("id")Long id,
                                                   @RequestParam("typeCompany") String typeCompany) {
        AirCompany airCompanyUpdate = airCompanyService.updateTypeCompany(id, typeCompany);

        AirCompanyDto result = AirCompanyDto.fromAirCompany(airCompanyUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AirCompanyDto> patchBook(@PathVariable("id")Long id,
                          @RequestBody AirCompany airCompanyPath) {
        AirCompany airCompanyUpdate = airCompanyService.update(id,airCompanyPath);
        AirCompanyDto result = AirCompanyDto.fromAirCompany(airCompanyUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        try {
            airCompanyService.deleteAirCompany(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        try {
            airCompanyService.deleteAllAirCompany();
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
}
