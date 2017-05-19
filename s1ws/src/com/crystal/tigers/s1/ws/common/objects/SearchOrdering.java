package com.crystal.tigers.s1.ws.common.objects;

import java.util.*;

/**
 * Created by sonyynoss on 5/19/17.
 */
public class SearchOrdering {
    public static final int SEARCH_ORDER_ASCENDANT = 1;

    public static final int SEARCH_ORDER_DESCENDANT = -1;

    protected List<AbstractMap.SimpleEntry<String, Integer>> searchOrdersRules;

    public List<AbstractMap.SimpleEntry<String, Integer>> getSearchOrdersRules() {
        return searchOrdersRules;
    }

    protected SearchOrdering() {
        this.searchOrdersRules = new ArrayList<>();
    }

    public static SearchOrdering newInstance() {
        SearchOrdering searchOrdering = new SearchOrdering();
        return searchOrdering;
    }

    public void addRule(String field, int order) {
        AbstractMap.SimpleEntry<String, Integer> rule = new AbstractMap.SimpleEntry<String, Integer>(field,order);
        searchOrdersRules.add(rule);
    }
}
