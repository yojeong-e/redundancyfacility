package org.foodsafety.importfood.redundancyfacility.service;

import lombok.RequiredArgsConstructor;
import org.foodsafety.importfood.redundancyfacility.commons.PreprocessType;
import org.foodsafety.importfood.redundancyfacility.commons.TextPreprocessing;
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

    public List<CompanySimilarity> getCosineSimilarityList(String companyCountry, String searchWord, CompanyInformation companyInformation, PreprocessType preprocessType) {

        List<Company> companyList = companyRepository.findByCompanyCountry(companyCountry);

        List<CompanySimilarity> companySimilarityList = new ArrayList<>();
        int id = 1;

        for (Company company : companyList) {
            CompanySimilarity companySimilarity = new CompanySimilarity();
            String targetWord = "";
            if (companyInformation == CompanyInformation.NAME) {
                targetWord = company.getCompanyName();
            } else if (companyInformation == CompanyInformation.ADDRESS) {
                targetWord = company.getCompanyAddress();
            }


            Double cosineSimilarity;
            TextPreprocessing textPreprocessing = new TextPreprocessing();
            if (preprocessType == PreprocessType.REMOVE_PUNCTUATION) {
                searchWord = textPreprocessing.TextPreprocessing(searchWord, PreprocessType.REMOVE_PUNCTUATION);
                targetWord = textPreprocessing.TextPreprocessing(targetWord, PreprocessType.REMOVE_PUNCTUATION);
                cosineSimilarity = cosineSimilaritySearchService.cosineSimilarity(searchWord, targetWord);
            } else if (preprocessType == PreprocessType.REMOVE_WHITESPACE) {

                searchWord = textPreprocessing.TextPreprocessing(searchWord, PreprocessType.REMOVE_WHITESPACE);
                targetWord = textPreprocessing.TextPreprocessing(targetWord, PreprocessType.REMOVE_WHITESPACE);
                cosineSimilarity = cosineSimilaritySearchService.cosineSimilarity(searchWord, targetWord);
            } else {
                cosineSimilarity = cosineSimilaritySearchService.cosineSimilarity(searchWord, targetWord);
            }


            if (cosineSimilarity > 30) {
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
