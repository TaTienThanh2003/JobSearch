package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company handleCreateCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public void handleDelete(long id) {
        this.companyRepository.deleteById(id);
    }

    public Company getCompany(long id) {
        Optional<Company> company = this.companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        }
        return null;
    }

    public List<Company> getAllCompany() {
        return this.companyRepository.findAll();
    }

    public Company updateCompany(Company putCompany) {
        Optional<Company> companyOptional = this.companyRepository.findById(putCompany.getId());
        if (companyOptional.isPresent()) {
            Company currentCompany = companyOptional.get();
            currentCompany.setAddress(putCompany.getAddress());
            currentCompany.setDescription(putCompany.getDescription());
            currentCompany.setLogo(putCompany.getLogo());
            currentCompany.setName(putCompany.getName());
            return this.companyRepository.save(currentCompany);
        }
        return null;
    }
}
