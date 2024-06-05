package org.utwente.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<List<T>> splitListIntoChunks(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(list.size(), i + chunkSize)));
        }
        return chunks;
    }
}