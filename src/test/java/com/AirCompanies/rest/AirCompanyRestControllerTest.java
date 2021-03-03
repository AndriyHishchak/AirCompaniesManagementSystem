package com.AirCompanies.rest;

import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.TypeCompany;
import com.AirCompanies.repository.AirCompanyRepository;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AirCompanyRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AirCompanyRepository repository;

    @AfterEach
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    @Ignore
    public void airCompanyShouldBeCreated() throws Exception {
        /*language=JSON*/
        var createJson = "{\n" +
                "    \"name\": \"TEST NAME\",\n" +
                "    \"typeCompany\": \"BUSINESS_AVIATION\",\n" +
                "    \"airplanes\": [],\n" +
                "    \"flights\": []\n" +
                "}";


        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/air-companies")
                .contentType("application/json")
                .content(createJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("/air-companies")))
                .andReturn().getResponse();

        Long id = Long.parseLong(Objects.requireNonNull(response.getHeader("location")).replace("/air-companies/", ""));
        assertThat("Air Company not found", repository.findById(id).isPresent());
    }

    @Test
    public void shouldFindAirCompanyById() throws Exception {
        var company = new AirCompany();
        company.setName("TEST COMPANY");
        company.setId(10L);
        company.setTypeCompany(TypeCompany.BUSINESS_AVIATION);
        company.setAirplanes( new ArrayList<>() );
        company.setAirplanes( new ArrayList<>() );
        Long id = repository.save(new AirCompany()).getId();

        mockMvc.perform( MockMvcRequestBuilders.get("/air-companies/{id}", id ) )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.jsonPath("name", Matchers.is("TEST COMPANY") ) )
                .andExpect( MockMvcResultMatchers.jsonPath("typeCompany", Matchers.is("BUSINESS_AVIATION") ) );
    }
}