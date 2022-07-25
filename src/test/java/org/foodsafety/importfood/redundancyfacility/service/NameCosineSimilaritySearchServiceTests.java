package org.foodsafety.importfood.redundancyfacility.service;

import org.foodsafety.importfood.redundancyfacility.commons.PreprocessType;
import org.foodsafety.importfood.redundancyfacility.commons.TextPreprocessing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameCosineSimilaritySearchServiceTests {

//    @Autowired
//    private CosineSimilaritySearch cosineSimilaritySearch;

    // Todo: enum으로 받아서 하나로 처리하기


//    String textPreprocessRemoveWhitespace(String str) {
//
//        TextPreprocessing textPreprocessing = new TextPreprocessing();
//        str = textPreprocessing.removeWhitespace(str);
//        return str;
//    }
//
//    String textPreprocessRemovePunctuation(String str) {
//
//        TextPreprocessing textPreprocessing = new TextPreprocessing();
//        str = textPreprocessing.removePunctuation(str);
//        return str;
//    }


    @Test
    @DisplayName("같은 기업 이름이 주어졌을 때, 코사인 유사도 시, 100퍼센트 동일")
    void givenSameNameWhenCosineSimilarityThenPercent100() {
        String registerCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";

        String preRegisterCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        CosineSimilaritySearchService similaritySearch = new CosineSimilaritySearchService();
        Double cosineSimilarity = similaritySearch.cosineSimilarity(registerCompanyName, preRegisterCompanyName);

        assertThat(cosineSimilarity).isEqualTo(100.0);


    }

    @Test
    @DisplayName("숫자를 추가한 기업 이름이 주어졌을 때, 코사인 유사도 시, 80퍼센트 이상")
    void givenNumericAddedNameWhenCosineSimilarityThenPercentGreaterThan80() {


        String registerCompanyName = "RADICALLY1 ORGANIC FOOD PRODUCTS, INC.";
        String preRegisterCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        CosineSimilaritySearchService similaritySearch = new CosineSimilaritySearchService();

        Double cosineSimilarity = similaritySearch.cosineSimilarity(registerCompanyName, preRegisterCompanyName);

        assertThat(cosineSimilarity).isGreaterThanOrEqualTo(80);

    }

    @Test
    @DisplayName("공백 추가한 기업 이름이 주어졌을 때, 코사인 유사도 시, 100퍼센트")
    void givenSpaceAddedNameWhenCosineSimilarityThenPercent100() {


        String registerCompanyName = "RADICALLY     ORGANIC FOOD PRODUCTS, INC.";
        String preRegisterCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        CosineSimilaritySearchService similaritySearch = new CosineSimilaritySearchService();

        Double cosineSimilarity = similaritySearch.cosineSimilarity(registerCompanyName, preRegisterCompanyName);

        assertThat(cosineSimilarity).isGreaterThanOrEqualTo(100);

    }

    @Test
    @DisplayName("공백 제거한 기업 이름이 주어졌을 때, 코사인 유사도 시, 50퍼센트 이하")
    void givenRemoveNameWhenCosineSimilarityThenPercentIsLessThan50() {

        String registerCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        String preRegisterCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        CosineSimilaritySearchService similaritySearch = new CosineSimilaritySearchService();

        TextPreprocessing textPreprocessing = new TextPreprocessing();
        registerCompanyName = textPreprocessing.TextPreprocessing(registerCompanyName, PreprocessType.REMOVE_WHITESPACE);


        Double cosineSimilarity = similaritySearch.cosineSimilarity(registerCompanyName, preRegisterCompanyName);

        assertThat(cosineSimilarity).isLessThanOrEqualTo(50);
    }


    @Test
    @DisplayName("특수문자 제거한 기업 이름이 주어졌을 때, 코사인 유사도 시, 80퍼센트 이상")
    void givenRemovePunctuationNameWhenCosineSimilarityThenPercentIsLessThan50() {

        String registerCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        String preRegisterCompanyName = "RADICALLY ORGANIC FOOD PRODUCTS, INC.";
        CosineSimilaritySearchService similaritySearch = new CosineSimilaritySearchService();

        TextPreprocessing textPreprocessing = new TextPreprocessing();
        registerCompanyName = textPreprocessing.TextPreprocessing(registerCompanyName, PreprocessType.REMOVE_PUNCTUATION);

        Double cosineSimilarity = similaritySearch.cosineSimilarity(registerCompanyName, preRegisterCompanyName);

        assertThat(cosineSimilarity).isGreaterThan(80);
    }


}


