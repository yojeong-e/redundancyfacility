package org.foodsafety.importfood.redundancyfacility.repository;

import org.foodsafety.importfood.redundancyfacility.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository  extends JpaRepository<Company, Long>{

    List<Company> findByCompanyName(String companyName);

    List<Company> findByCompanyCountry(String companyCountry);
}
