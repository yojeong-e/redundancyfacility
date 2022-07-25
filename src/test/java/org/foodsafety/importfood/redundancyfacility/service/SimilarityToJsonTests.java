package org.foodsafety.importfood.redundancyfacility.service;

import org.foodsafety.importfood.redundancyfacility.Times;
import org.foodsafety.importfood.redundancyfacility.commons.ObjectToJson;
import org.foodsafety.importfood.redundancyfacility.commons.PreprocessType;
import org.foodsafety.importfood.redundancyfacility.constant.CompanyInformation;
import org.foodsafety.importfood.redundancyfacility.entity.CompanySimilarity;
import org.foodsafety.importfood.redundancyfacility.repository.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SimilarityToJsonTests {

    static LocalDateTime currentTime = Times.currentTime();

    @Autowired
    CompanyRepository companyRepository;
    @PersistenceContext
    EntityManager em;
    @Autowired
    private SimilarityService similarityService;

    @Autowired
    private ObjectToJson objectToJson;

    @Test
    @DisplayName("유사도 리스트가 주어졌을 때, Json으로 변환하고, Json String을 반환")
    void givenCompanySimilarityListWhenListToJsonThanReturnJson() {

        String testCompanyCountry = "US";
        String searchWord = "1600 Pennsylvania Avenue, NW Washington, D.C. 20500, U.S.";

        List<CompanySimilarity> companySimilarityList = null;
        companySimilarityList = similarityService.getCosineSimilarityList(testCompanyCountry, searchWord, CompanyInformation.ADDRESS, PreprocessType.NONE);

        String companySimilarityJson = objectToJson.companySimilarityListToJson(companySimilarityList);
        assertThat(companySimilarityJson).isInstanceOf(String.class);
    }
}
