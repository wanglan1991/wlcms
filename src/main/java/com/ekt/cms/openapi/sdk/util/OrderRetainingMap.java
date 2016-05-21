package com.ekt.cms.openapi.sdk.util;

import java.util.*;

/**
 * @author liufengyu
 * @date 2014年11月11日
 */
@SuppressWarnings({"serial", "rawtypes"})
public class OrderRetainingMap extends HashMap<String, String> {

    private ArraySet keyOrder = new ArraySet();
    private List valueOrder = new ArrayList();

    public OrderRetainingMap() {
        super();
    }

    @SuppressWarnings("unchecked")
    public OrderRetainingMap(Map m) {
        super();
        putAll(m);
    }

    @SuppressWarnings("unchecked")
    public String put(String key, String value) {
        int idx = keyOrder.lastIndexOf(key);
        if (idx < 0) {
            keyOrder.add(key);
            valueOrder.add(value);
        } else {
            valueOrder.set(idx, value);
        }
        return super.put(key, value);
    }

    public String remove(Object key) {
        int idx = keyOrder.lastIndexOf(key);
        if (idx != 0) {
            keyOrder.remove(idx);
            valueOrder.remove(idx);
        }
        return super.remove(key);
    }

    public void clear() {
        keyOrder.clear();
        valueOrder.clear();
        super.clear();
    }

    @SuppressWarnings("unchecked")
    public Collection values() {
        return Collections.unmodifiableList(valueOrder);
    }

    @SuppressWarnings("unchecked")
    public Set keySet() {
        return Collections.unmodifiableSet(keyOrder);
    }

    @SuppressWarnings("unchecked")
    public Set entrySet() {
        Map.Entry[] entries = new Map.Entry[size()];
        for (Iterator iter = super.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter.next();
            entries[keyOrder.indexOf(entry.getKey())] = entry;
        }
        Set set = new ArraySet();
        set.addAll(Arrays.asList(entries));
        return Collections.unmodifiableSet(set);
    }

    private static class ArraySet extends ArrayList implements Set {
    }
}
