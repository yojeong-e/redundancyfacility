package org.foodsafety.importfood.redundancyfacility.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.similarity.CosineDistance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CosineSimilaritySearchService {


    public Double cosineSimilarity(String str1, String str2) {

        double cosineDistance = new CosineDistance().apply(str1, str2);
        double cosineSimilarityPercentage = Math.round((1 - cosineDistance) * 100);
        return cosineSimilarityPercentage;
    }


}
