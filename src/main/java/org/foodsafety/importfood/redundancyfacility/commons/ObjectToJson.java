package org.foodsafety.importfood.redundancyfacility.commons;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.foodsafety.importfood.redundancyfacility.entity.CompanySimilarity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ObjectToJson {

    public String companySimilarityListToJson(List<CompanySimilarity> companySimilarityList) {

        Gson gson = new Gson();
        String companySimilarityJson = gson.toJson(companySimilarityList);

        return companySimilarityJson;
    }
}
