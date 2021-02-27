package com.AirCompanies.rest;

import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.service.Impl.AirCompanyServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("air-companies/")
public class AirCompanyRestController {

    private final AirCompanyServiceImpl airCompanyService;

    public AirCompanyRestController(AirCompanyServiceImpl airCompanyService) {
        this.airCompanyService = airCompanyService;
    }
    @GetMapping()
    public List<AirCompanyDto> getAll(@RequestParam(value = "name",required = false) Optional <String> name ) {
        return airCompanyService.getAll( name );
    }

    @GetMapping("{id}")
    public ResponseEntity<AirCompanyDto> getById(@PathVariable(name = "id") Long id) {
        AirCompanyDto airCompany = airCompanyService.findById(id);
        return new ResponseEntity<>(airCompany,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> registration(@RequestBody AirCompany airCompany){
        AirCompanyDto company = airCompanyService.create(airCompany);
        return ResponseEntity.created( URI.create("/air-companies/" + company.getId())).build();
    }

    //тут подумай як обєднати методи за допомогою  @RequestParam(value = "type",required = false
    @PatchMapping("{id}/update/type")
    public ResponseEntity<AirCompanyDto> updateStatus(@PathVariable("id")Long id,
                                                   @RequestParam(value = "type",required = false) String typeCompany) {
        return new ResponseEntity<>(airCompanyService.updateTypeCompany(id, typeCompany), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AirCompanyDto> update(@PathVariable("id")Long id, @RequestBody AirCompany airCompanyPath) {
        return new ResponseEntity<>(airCompanyService.update(id,airCompanyPath), HttpStatus.OK);
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

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException (NotFoundException exception) {
//                ResponseEntity.ok(exception.getMessage());
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
