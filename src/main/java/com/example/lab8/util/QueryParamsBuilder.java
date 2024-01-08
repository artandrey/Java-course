package com.example.lab8.util;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class QueryParam {
    private String key;
    private String value;

    @Override
    public String toString() {
        return value != null ? key + "=" + value : key;
    }
}

public class QueryParamsBuilder {
    private List<QueryParam> queryParams = new LinkedList<>();

    public QueryParamsBuilder add(String key, String value) {
        queryParams.add(new QueryParam(key, value));
        return this;
    }

    public QueryParamsBuilder add(String key) {
        return add(key, null);
    }

    public QueryParamsBuilder add(String key, double value) {
        return add(key, String.valueOf(value));
    }

    public String build() {
        return queryParams.stream().map(param -> param.toString()).collect(Collectors.joining("&"));
    }
}
