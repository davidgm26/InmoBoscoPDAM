package com.salesianostriana.pdam.inmoboscoapi.search.spec;

import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class GenericSpecificationBuilder <T>{

    private List<SearchCriteria>params;

    public GenericSpecificationBuilder(List<SearchCriteria> params) {this.params = params;}

    public Specification<T>build(){

        if (params.isEmpty()){
            return null;
        }

        Specification<T> result = new GenericSpecification<T>(params.get(0));

        return result;

    }
}
