package com.example.lab8.analysis;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Builder;

@Builder
public class Analysis<O, G, R> {
    private Function<O, G> groupBy;
    private Function<Collection<O>, R> process;
    private Comparator<R> sort;
    private Integer limitation;

    public Map<G, R> analyze(Collection<O> weatherDataset) {
        Map<G, List<O>> groupedResult = weatherDataset.stream().collect(Collectors.groupingBy(groupBy));
        Stream<Entry<G, R>> filteredResult = applyLimitation(applyComparator(
                groupedResult.entrySet().stream().map((groupEntry) -> Map.entry(groupEntry.getKey(), applyFilter(groupEntry.getValue())))));

        return filteredResult.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    private R applyFilter(Collection<O> weatherData) {
        return process.apply(weatherData);
    }

    private Stream<Entry<G, R>> applyComparator(Stream<Entry<G, R>> datasetStream) {
        if (sort != null) {
            return datasetStream.sorted((entryA, entryB) -> sort.compare(entryA.getValue(), entryB.getValue()));
        }
        return datasetStream;
    }

    private Stream<Entry<G, R>> applyLimitation(Stream<Entry<G, R>> datasetStream) {
        if (limitation != null) {
            return datasetStream.limit(limitation);
        }
        return datasetStream;
    }

}
