package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.service.CompanyService;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies/{id}")
    public ResponseEntity<Company> CreateCompany(@Valid @RequestBody Company reqCompany) {
        Company company = this.companyService.handleCreateCompany(reqCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @PutMapping("/companies")
    public ResponseEntity<Company> putMethodName(@Valid @RequestBody Company company) {
        Company updateCompany = this.companyService.updateCompany(company);
        return ResponseEntity.ok().body(updateCompany);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyId(@PathVariable("id") long id) {
        Company company = this.companyService.getCompany(id);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompany() {
        return this.companyService.getAllCompany();
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> Delete(@PathVariable("id") long id) throws IdInvalidException {
        if (id > 1500) {
            throw new IdInvalidException("Ko nhập id quá 1500");
        }
        this.companyService.handleDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
