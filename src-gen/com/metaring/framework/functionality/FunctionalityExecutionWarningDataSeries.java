package com.metaring.framework.functionality;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityExecutionWarningDataSeries extends ArrayList<FunctionalityExecutionWarningData> implements GeneratedCoreType {

    private static final long serialVersionUID = 1L;
    private Enumerable<FunctionalityExecutionWarningData> internalEnumerable;

    private FunctionalityExecutionWarningDataSeries(Iterable<FunctionalityExecutionWarningData> iterable) {
        super();
        this.addAll(StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList()));
    }

    public static FunctionalityExecutionWarningDataSeries create(Iterable<FunctionalityExecutionWarningData> iterable) {
        return new FunctionalityExecutionWarningDataSeries(iterable);
    }

    public static FunctionalityExecutionWarningDataSeries create(FunctionalityExecutionWarningData... functionalityExecutionWarningDatas) {
        return create(functionalityExecutionWarningDatas == null ? new ArrayList<>() : Arrays.asList(functionalityExecutionWarningDatas));
    }

    public FunctionalityExecutionWarningData[] toArray() {
        return this.toArray(new FunctionalityExecutionWarningData[this.size()]);
    }


    public Enumerable<FunctionalityExecutionWarningData> asEnumerable() {
        return internalEnumerable != null ? internalEnumerable : (internalEnumerable = Linq4j.asEnumerable(this));
    }

    public boolean addAll(Enumerable<FunctionalityExecutionWarningData> enumerable) {
        return enumerable == null ? false : this.addAll(enumerable.toList());
    }

    public boolean containsAll(Enumerable<FunctionalityExecutionWarningData> enumerable) {
        return enumerable == null ? false : this.containsAll(enumerable.toList());
    }

    public boolean removeAll(Enumerable<FunctionalityExecutionWarningData> enumerable) {
        return enumerable == null ? false : this.removeAll(enumerable.toList());
    }

    public boolean retainAll(Enumerable<FunctionalityExecutionWarningData> enumerable) {
        return enumerable == null ? false : this.retainAll(enumerable.toList());
    }

    public boolean addAll(FunctionalityExecutionWarningData[] array) {
        return array == null ? false : this.addAll(Arrays.asList(array));
    }

    public boolean containsAll(FunctionalityExecutionWarningData[] array) {
        return array == null ? false : this.containsAll(Arrays.asList(array));
    }

    public boolean removeAll(FunctionalityExecutionWarningData[] array) {
        return array == null ? false : this.removeAll(Arrays.asList(array));
    }

    public boolean retainAll(FunctionalityExecutionWarningData[] array) {
        return array == null ? false : this.retainAll(Arrays.asList(array));
    }

    private void recreateEnumerable() {
        if (internalEnumerable != null) {
            internalEnumerable = Linq4j.asEnumerable(this);
        }
    }

    @Override
    public boolean add(FunctionalityExecutionWarningData e) {
        boolean test = super.add(e);
        recreateEnumerable();
        return test;
    }

    @Override
    public void add(int index, FunctionalityExecutionWarningData element) {
        super.add(index, element);
        recreateEnumerable();
    }

    @Override
    public boolean addAll(Collection<? extends FunctionalityExecutionWarningData> c) {
        boolean test = super.addAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean addAll(int index, Collection<? extends FunctionalityExecutionWarningData> c) {
        boolean test = super.addAll(index, c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean remove(Object o) {
        boolean test = super.remove(o);
        recreateEnumerable();
        return test;
    }

    @Override
    public FunctionalityExecutionWarningData remove(int index) {
        FunctionalityExecutionWarningData test = super.remove(index);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean test = super.removeAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean removeIf(Predicate<? super FunctionalityExecutionWarningData> filter) {
        boolean test = super.removeIf(filter);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean test = super.retainAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public void replaceAll(UnaryOperator<FunctionalityExecutionWarningData> operator) {
        super.replaceAll(operator);
        recreateEnumerable();
    }

    public static FunctionalityExecutionWarningDataSeries fromJson(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = jsonString.trim();
        if(jsonString.isEmpty()) {
            return null;
        }
        if(jsonString.equalsIgnoreCase("null")) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromJson(jsonString);
        List<FunctionalityExecutionWarningData> list = new ArrayList<>();
        for(DataRepresentation data : dataRepresentation) {
            list.add(FunctionalityExecutionWarningData.fromJson(data.asText()));
        }
        return new FunctionalityExecutionWarningDataSeries(list);
    }

    public static FunctionalityExecutionWarningDataSeries fromObject(Object object) {
        if (object == null) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);
        List<FunctionalityExecutionWarningData> list = new ArrayList<>();
        for(DataRepresentation data : dataRepresentation) {
            list.add(FunctionalityExecutionWarningData.fromJson(data.asText()));
        }
        return new FunctionalityExecutionWarningDataSeries(list);
    }

    public DataRepresentation toDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(toJson());
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder("[");
        forEach(it -> sb.append(it.toJson()).append(","));
        return sb.delete(sb.length() - 1, sb.length()).append("]").toString();
    }

    @Override
    public String toString() {
        return toJson();
    }
}