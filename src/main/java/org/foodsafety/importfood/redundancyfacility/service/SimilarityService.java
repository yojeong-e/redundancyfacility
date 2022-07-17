package org.foodsafety.importfood.redundancyfacility.service;

import lombok.RequiredArgsConstructor;
import org.foodsafety.importfood.redundancyfacility.constant.CompanyInformation;
import org.foodsafety.importfood.redundancyfacility.entity.Company;
import org.foodsafety.importfood.redundancyfacility.entity.CompanySimilarity;
import org.foodsafety.importfood.redundancyfacility.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SimilarityService {


    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final CosineSimilaritySearchService cosineSimilaritySearchService;

    public List<CompanySimilarity> getCosineSimilarityList(String companyCountry, String searchWord, CompanyInformation companyInformation) {

        List<Company> companyList = companyRepository.findByCompanyCountry(companyCountry);

        List<CompanySimilarity> companySimilarityList = new ArrayList<>();
        int id = 1;

        for (Company company : companyList) {
            CompanySimilarity companySimilarity = new CompanySimilarity();
            String targetWord = "";
            if (companyInformation == CompanyInformation.Name) {
                targetWord = company.getCompanyName();
            } else if (companyInformation == CompanyInformation.Address) {
                targetWord = company.getCompanyAddress();
            }
            Double cosineSimilarity = cosineSimilaritySearchService.cosineSimilarity(searchWord, targetWord);


            if (cosineSimilarity > 40) {
                companySimilarity.setId(id);
                companySimilarity.setCompany(company);
                companySimilarity.setCompanySimilarity(cosineSimilarity);

                companySimilarityList.add(companySimilarity);
                id++;
            }

        }
        return companySimilarityList;
    }


}
