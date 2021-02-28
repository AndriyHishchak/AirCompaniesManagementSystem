package com.AirCompanies.rest;

import com.AirCompanies.Dto.AirCompanyDto;
import com.AirCompanies.exception.NotFoundException;
import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.TypeCompany;
import com.AirCompanies.service.Impl.AirCompanyServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        return new ResponseEntity<>(airCompanyService.findById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> registration(@RequestBody AirCompany airCompany){
        AirCompanyDto company = airCompanyService.create(airCompany);
        return ResponseEntity.created( URI.create("/air-companies/" + company.getId())).build();
    }


    @PatchMapping("{id}/update/parameters")
    public ResponseEntity<AirCompanyDto> updateStatus(@PathVariable("id")Long id,
                                                   @RequestParam(value = "type",required = false) Optional<TypeCompany> typeCompany,
                                                   @RequestParam(value = "name",required = false) Optional<String> name) {
        return new ResponseEntity<>(airCompanyService.updateParametersAirCompany(id, typeCompany,name), HttpStatus.OK);
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<AirCompanyDto> update(@PathVariable("id")Long id, @RequestBody AirCompany airCompanyRefresh) {
        return new ResponseEntity<>(airCompanyService.update(id,airCompanyRefresh), HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
            airCompanyService.deleteAirCompany(id);
    }
    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
            airCompanyService.deleteAllAirCompany();
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException (NotFoundException exception) {
//                ResponseEntity.ok(exception.getMessage());
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
