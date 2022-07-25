package org.foodsafety.importfood.redundancyfacility.repository;


import org.foodsafety.importfood.redundancyfacility.Times;
import org.foodsafety.importfood.redundancyfacility.commons.PreprocessType;
import org.foodsafety.importfood.redundancyfacility.constant.CompanyInformation;
import org.foodsafety.importfood.redundancyfacility.entity.Company;
import org.foodsafety.importfood.redundancyfacility.entity.CompanySimilarity;
import org.foodsafety.importfood.redundancyfacility.service.SimilarityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CompanyRepositoryTests {

    // Todo : String to LocalDateTime. After solve gson Serialize Errors
    static String currentTime = Times.currentTime().toString();

    @Autowired
    CompanyRepository companyRepository;
    @PersistenceContext
    EntityManager em;
    @Autowired
    private SimilarityService similarityService;

    //    @AfterEach
    public void after() {
        companyRepository.deleteAll();
    }

    private Company createCompany(String testCompanyName) {
        Company Company = new Company();
        Company.setCompanyName(testCompanyName);
        Company.setCompanyAddress("1600 Pennsylvania Avenue, NW Washington, D.C. 20500, U.S.");
        Company.setCompanyCountry("US");
        Company.setCompanyCode("US000000001");
        Company.setUpdateTime(currentTime);
        Company.setRegTime(currentTime);
        return Company;
    }

    @Test
    @DisplayName("회사 정보 생성 후, 데이터베이스 삽입후, 조회가능")
    void givenCompanyNameWhenInsertDatabaseThenSelectCompanyDatabase() {
        String testCompanyName = "Test Company Name Co., Ltd.";
        Company company = createCompany(testCompanyName);
        Company savedCompany = companyRepository.save(company);

        assertThat(savedCompany.getCompanyName()).isEqualTo("Test Company Name Co., Ltd.");
    }


    @Test
    @DisplayName("회사 이름을 사용하여, 데이터베이스 조회시, 회사 정보 조회가능")
    void givenCompanyNameWhenSelectCompanyDatabaseThenReturnCompanyData() {
        String testCompanyName = "Test Company Name Co., Ltd.";
        List<Company> companyList = companyRepository.findByCompanyName(testCompanyName);
        assertThat(companyList.get(0).getCompanyName()).isEqualTo(testCompanyName);
    }


    @Test
    @DisplayName("국가코드를 사용하여, 데이터베이스 조회시, 회사 정보 조회가능")
    void givenCountryCodeWhenSelectCompanyDatabaseThenReturnCompanyData() {
        String testCompanyCountry = "US";
        List<Company> companyList = companyRepository.findByCompanyCountry(testCompanyCountry);
        assertThat(companyList.get(0).getCompanyCountry()).isEqualTo(testCompanyCountry);
    }


    @Test
    @DisplayName("국가코드를 사용하여, 데이터베이스 조회하고, 회사 이름의 유사성을 계산하여, 유사도 반환")
    void givenCountryCodeWhenSelectCompanyDatabaseAndCompanyNameCosineSimilarityThenReturnSimilarityValue() {

        String testCompanyCountry = "CN";
        String searchWord = "Greenholt";

        List<CompanySimilarity> companyList = null;
        companyList = similarityService.getCosineSimilarityList(testCompanyCountry, searchWord, CompanyInformation.NAME, PreprocessType.NONE);

        assertThat(companyList.get(0).getCompanySimilarity()).isGreaterThan(50);
    }

    @Test
    @DisplayName("국가코드를 사용하여, 데이터베이스 조회하고, 회사 주소의 유사성을 계산하여, 유사도 반환")
    void givenCountryCodeWhenSelectCompanyDatabaseAndCompanyAddressCosineSimilarityThenReturnSimilarityValue() {

        String testCompanyCountry = "US";
        String searchWord = "1600 Pennsylvania Avenue, NW Washington, D.C. 20500, U.S.";

        List<CompanySimilarity> companySimilarityList = null;
        companySimilarityList = similarityService.getCosineSimilarityList(testCompanyCountry, searchWord, CompanyInformation.ADDRESS, PreprocessType.NONE);

        assertThat(companySimilarityList.get(0).getCompanySimilarity()).isGreaterThan(50);
    }
}
