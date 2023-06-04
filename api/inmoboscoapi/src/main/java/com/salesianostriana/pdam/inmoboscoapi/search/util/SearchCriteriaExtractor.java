package com.salesianostriana.pdam.inmoboscoapi.search.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface SearchCriteriaExtractor {

    static List<SearchCriteria> extractSearchCriteriaList(String search){

        List<SearchCriteria> params = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)([\\D\\S-_]+?),"); // \\[w-]+? nos permite que los valores tengan guiones medios, para las fechas
        if(!search.endsWith(",")){
            search = search + ",";
        }
        Matcher matcher = pattern.matcher(search);

        while (matcher.find()){
            String key = matcher.group(1);
            String operator = matcher.group(2);
            String value = matcher.group(3);

            params.add(new SearchCriteria(key,operator,value));
        }
    return params;
    }
}
