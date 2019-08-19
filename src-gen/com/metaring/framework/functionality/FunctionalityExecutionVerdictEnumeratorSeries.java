package com.metaring.framework.functionality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import com.metaring.framework.GeneratedCoreType;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;

public class FunctionalityExecutionVerdictEnumeratorSeries extends ArrayList<FunctionalityExecutionVerdictEnumerator> implements GeneratedCoreType {

    private static final long serialVersionUID = 1L;
    private Enumerable<FunctionalityExecutionVerdictEnumerator> internalEnumerable;

    private FunctionalityExecutionVerdictEnumeratorSeries(Iterable<FunctionalityExecutionVerdictEnumerator> iterable) {
        super();
        this.addAll(StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList()));
    }

    public static FunctionalityExecutionVerdictEnumeratorSeries create(Iterable<FunctionalityExecutionVerdictEnumerator> iterable) {
        return new FunctionalityExecutionVerdictEnumeratorSeries(iterable);
    }

    public static FunctionalityExecutionVerdictEnumeratorSeries create(FunctionalityExecutionVerdictEnumerator... functionalityExecutionVerdicts) {
        return create(functionalityExecutionVerdicts == null ? new ArrayList<>() : Arrays.asList(functionalityExecutionVerdicts));
    }

    public FunctionalityExecutionVerdictEnumerator[] toArray() {
        return this.toArray(new FunctionalityExecutionVerdictEnumerator[this.size()]);
    }

    public Enumerable<FunctionalityExecutionVerdictEnumerator> asEnumerable() {
        return internalEnumerable != null ? internalEnumerable : (internalEnumerable = Linq4j.asEnumerable(this));
    }

    public boolean addAll(Enumerable<FunctionalityExecutionVerdictEnumerator> enumerable) {
        return enumerable == null ? false : this.addAll(enumerable.toList());
    }

    public boolean containsAll(Enumerable<FunctionalityExecutionVerdictEnumerator> enumerable) {
        return enumerable == null ? false : this.containsAll(enumerable.toList());
    }

    public boolean removeAll(Enumerable<FunctionalityExecutionVerdictEnumerator> enumerable) {
        return enumerable == null ? false : this.removeAll(enumerable.toList());
    }

    public boolean retainAll(Enumerable<FunctionalityExecutionVerdictEnumerator> enumerable) {
        return enumerable == null ? false : this.retainAll(enumerable.toList());
    }

    public boolean addAll(FunctionalityExecutionVerdictEnumerator[] array) {
        return array == null ? false : this.addAll(Arrays.asList(array));
    }

    public boolean containsAll(FunctionalityExecutionVerdictEnumerator[] array) {
        return array == null ? false : this.containsAll(Arrays.asList(array));
    }

    public boolean removeAll(FunctionalityExecutionVerdictEnumerator[] array) {
        return array == null ? false : this.removeAll(Arrays.asList(array));
    }

    public boolean retainAll(FunctionalityExecutionVerdictEnumerator[] array) {
        return array == null ? false : this.retainAll(Arrays.asList(array));
    }

    private void recreateEnumerable() {
        if (internalEnumerable != null) {
            internalEnumerable = Linq4j.asEnumerable(this);
        }
    }

    @Override
    public boolean add(FunctionalityExecutionVerdictEnumerator e) {
        boolean test = super.add(e);
        recreateEnumerable();
        return test;
    }

    @Override
    public void add(int index, FunctionalityExecutionVerdictEnumerator element) {
        super.add(index, element);
        recreateEnumerable();
    }

    @Override
    public boolean addAll(Collection<? extends FunctionalityExecutionVerdictEnumerator> c) {
        boolean test = super.addAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean addAll(int index, Collection<? extends FunctionalityExecutionVerdictEnumerator> c) {
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
    public FunctionalityExecutionVerdictEnumerator remove(int index) {
        FunctionalityExecutionVerdictEnumerator test = super.remove(index);
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
    public boolean removeIf(Predicate<? super FunctionalityExecutionVerdictEnumerator> filter) {
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
    public void replaceAll(UnaryOperator<FunctionalityExecutionVerdictEnumerator> operator) {
        super.replaceAll(operator);
        recreateEnumerable();
    }

    public static FunctionalityExecutionVerdictEnumeratorSeries fromJson(String jsonString) {
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
        FunctionalityExecutionVerdictEnumerator[] values = new FunctionalityExecutionVerdictEnumerator[dataRepresentation.length()];
        for(int i = 0; i < values.length; i++) {
            values[i] = dataRepresentation.get(i, FunctionalityExecutionVerdictEnumerator.class);
        }
        return create(values);
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder("[");
        forEach(it -> sb.append(it.toJson()).append(","));
        return sb.delete(sb.length() - 1, sb.length()).append("]").toString();
    }

    public DataRepresentation toDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(toJson());
    }
}