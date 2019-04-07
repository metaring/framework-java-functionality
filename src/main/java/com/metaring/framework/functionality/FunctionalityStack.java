/**
 *    Copyright 2019 MetaRing s.r.l.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.functionality;

import java.util.LinkedList;
import java.util.List;

class FunctionalityStack {
    private LinkedList<Functionality> linkedList = new LinkedList<>();

    public FunctionalityStack() {
    }

    public int getCardinality() {
        return this.linkedList.size();
    }

    public void add(Functionality functionality) {
        this.linkedList.addLast(functionality);
    }

    public Functionality removeLast() {
        return this.linkedList.removeLast();
    }

    public Functionality getFirst() {
        return this.linkedList.getFirst();
    }

    public Functionality getPenultimate() {
        if (this.linkedList.size() == 1) {
            return null;
        }
        return this.linkedList.get(this.linkedList.size() - 2);
    }

    public Functionality getLast() {
        return this.linkedList.getLast();
    }

    public Functionality getAt(int index) {
        return this.linkedList.get(index);
    }

    public Functionality getFirstOfType(String functionalityFullyQualifiedName) {
        try {
            return this.linkedList.stream().filter(f -> f.getInfo().getFunctionalityFullyQualifiedName().equals(functionalityFullyQualifiedName)).findFirst().get();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Functionality getPenultimateOfType(String functionalityFullyQualifiedName) {
        try {
            Functionality[] functionalities = (Functionality[]) this.linkedList.stream().filter(f -> f.getInfo().getFunctionalityFullyQualifiedName().equals(functionalityFullyQualifiedName)).toArray();
            return functionalities[functionalities.length - 2];
        }
        catch (Exception e) {
            return null;
        }
    }

    public Functionality getLastOfType(String functionalityFullyQualifiedName) {
        try {
            Functionality[] functionalities = (Functionality[]) this.linkedList.stream().filter(f -> f.getInfo().getFunctionalityFullyQualifiedName().equals(functionalityFullyQualifiedName)).toArray();
            return functionalities[functionalities.length - 1];
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    public void purge() {
        this.linkedList.clear();
        this.linkedList = null;
    }

    public FunctionalityStackElementSeries toStackElementSeries() {
        return this.getStackElementSeries(this.linkedList);
    }

    public FunctionalityStackElementSeries toStackElementSeriesTillNow(Functionality functionality) {
        return this.getStackElementSeries(this.linkedList.subList(0, this.linkedList.indexOf(functionality) + 1));
    }

    private final FunctionalityStackElementSeries getStackElementSeries(List<Functionality> functionalities) {
        LinkedList<FunctionalityStackElement> stack = new LinkedList<FunctionalityStackElement>();
        for (Functionality functionality : functionalities) {
            stack.addLast(FunctionalityStackElement.create(functionality.getInfo().getFunctionalityFullyQualifiedName(), functionality.getCurrentStep()));
        }
        return FunctionalityStackElementSeries.create(stack.toArray(new FunctionalityStackElement[stack.size()]));
    }
}
