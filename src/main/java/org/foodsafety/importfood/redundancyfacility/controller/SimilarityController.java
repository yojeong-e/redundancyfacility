package org.foodsafety.importfood.redundancyfacility.controller;

import org.foodsafety.importfood.redundancyfacility.commons.ObjectToJson;
import org.foodsafety.importfood.redundancyfacility.constant.CompanyInformation;
import org.foodsafety.importfood.redundancyfacility.entity.CompanySimilarity;
import org.foodsafety.importfood.redundancyfacility.service.SimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "https://yojeong-e.github.io")
public class SimilarityController {

    @Autowired
    private SimilarityService similarityService;

    @Autowired
    private ObjectToJson objectToJson;


    @GetMapping("/name")
    public String companyNameSimilarity(@RequestParam("country") String country, @RequestParam("name") String name) {

        List<CompanySimilarity> companySimilarityList = null;
        companySimilarityList = similarityService.getCosineSimilarityList(country, name, CompanyInformation.Name);

        String companySimilarityJson = objectToJson.companySimilarityListToJson(companySimilarityList);


        return companySimilarityJson;
    }

    @GetMapping("/address")
    public String companyAddressSimilarity(@RequestParam("country") String country, @RequestParam("address") String address) {

        List<CompanySimilarity> companySimilarityList = null;
        companySimilarityList = similarityService.getCosineSimilarityList(country, address, CompanyInformation.Address);

        String companySimilarityJson = objectToJson.companySimilarityListToJson(companySimilarityList);


        return companySimilarityJson;
    }

}
