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

public class FunctionalityStackElementSeries extends ArrayList<FunctionalityStackElement> implements GeneratedCoreType {

    private static final long serialVersionUID = 1L;
    private Enumerable<FunctionalityStackElement> internalEnumerable;

    private FunctionalityStackElementSeries(Iterable<FunctionalityStackElement> iterable) {
        super();
        this.addAll(StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList()));
    }

    public static FunctionalityStackElementSeries create(Iterable<FunctionalityStackElement> iterable) {
        return new FunctionalityStackElementSeries(iterable);
    }

    public static FunctionalityStackElementSeries create(FunctionalityStackElement... functionalityStackElements) {
        return create(functionalityStackElements == null ? new ArrayList<>() : Arrays.asList(functionalityStackElements));
    }

    public FunctionalityStackElement[] toArray() {
        return this.toArray(new FunctionalityStackElement[this.size()]);
    }


    public Enumerable<FunctionalityStackElement> asEnumerable() {
        return internalEnumerable != null ? internalEnumerable : (internalEnumerable = Linq4j.asEnumerable(this));
    }

    public boolean addAll(Enumerable<FunctionalityStackElement> enumerable) {
        return enumerable == null ? false : this.addAll(enumerable.toList());
    }

    public boolean containsAll(Enumerable<FunctionalityStackElement> enumerable) {
        return enumerable == null ? false : this.containsAll(enumerable.toList());
    }

    public boolean removeAll(Enumerable<FunctionalityStackElement> enumerable) {
        return enumerable == null ? false : this.removeAll(enumerable.toList());
    }

    public boolean retainAll(Enumerable<FunctionalityStackElement> enumerable) {
        return enumerable == null ? false : this.retainAll(enumerable.toList());
    }

    public boolean addAll(FunctionalityStackElement[] array) {
        return array == null ? false : this.addAll(Arrays.asList(array));
    }

    public boolean containsAll(FunctionalityStackElement[] array) {
        return array == null ? false : this.containsAll(Arrays.asList(array));
    }

    public boolean removeAll(FunctionalityStackElement[] array) {
        return array == null ? false : this.removeAll(Arrays.asList(array));
    }

    public boolean retainAll(FunctionalityStackElement[] array) {
        return array == null ? false : this.retainAll(Arrays.asList(array));
    }

    private void recreateEnumerable() {
        if (internalEnumerable != null) {
            internalEnumerable = Linq4j.asEnumerable(this);
        }
    }

    @Override
    public boolean add(FunctionalityStackElement e) {
        boolean test = super.add(e);
        recreateEnumerable();
        return test;
    }

    @Override
    public void add(int index, FunctionalityStackElement element) {
        super.add(index, element);
        recreateEnumerable();
    }

    @Override
    public boolean addAll(Collection<? extends FunctionalityStackElement> c) {
        boolean test = super.addAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean addAll(int index, Collection<? extends FunctionalityStackElement> c) {
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
    public FunctionalityStackElement remove(int index) {
        FunctionalityStackElement test = super.remove(index);
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
    public boolean removeIf(Predicate<? super FunctionalityStackElement> filter) {
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
    public void replaceAll(UnaryOperator<FunctionalityStackElement> operator) {
        super.replaceAll(operator);
        recreateEnumerable();
    }

    public static FunctionalityStackElementSeries fromJson(String jsonString) {
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
        List<FunctionalityStackElement> list = new ArrayList<>();
        for(DataRepresentation data : dataRepresentation) {
            list.add(FunctionalityStackElement.fromJson(data.asText()));
        }
        return new FunctionalityStackElementSeries(list);
    }

    public static FunctionalityStackElementSeries fromObject(Object object) {
        if (object == null) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);
        List<FunctionalityStackElement> list = new ArrayList<>();
        for(DataRepresentation data : dataRepresentation) {
            list.add(FunctionalityStackElement.fromJson(data.asText()));
        }
        return new FunctionalityStackElementSeries(list);
    }

    public DataRepresentation toDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(toJson());
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder("[");
        if(!isEmpty()) {
            forEach(it -> sb.append(it.toJson()).append(","));
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.append("]").toString();
    }

    @Override
    public String toString() {
        return toJson();
    }
}