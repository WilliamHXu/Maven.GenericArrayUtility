package com.zipcodewilmington.arrayutility;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E> {

    private E[] array;

    public ArrayUtility(E[] array) {
        this.array = array;
    }

    public E[] removeValue(E valueToRemove) {
        E[] newArray = Arrays.copyOf(array, array.length - getNumberOfOccurrences(array, valueToRemove));

        return Stream.of(array).filter(val -> val != valueToRemove)
                .collect(Collectors.toList()).toArray(newArray);
    }

    public Integer getNumberOfOccurrences(E valueToEvaluate){
        return getNumberOfOccurrences(array, valueToEvaluate);
    }

    public Integer getNumberOfOccurrences(E[] array, E valueToEvaluate) {
        Integer count = 0;
        for (E element : array) {
            if (valueToEvaluate.equals(element)){
                count++;
            }
        }
        return count;
    }

    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        E[] merged = merge(array, arrayToMerge);
        return getNumberOfOccurrences(merged, valueToEvaluate);
    }

    private E[] merge(E[] array, E[] arrayToMerge) {
        E[] newArray = Arrays.copyOf(array, array.length + arrayToMerge.length);
        for (int i = array.length; i < newArray.length; i++) {
            newArray[i] = arrayToMerge[i - array.length];
        }
        return newArray;
    }

    public E getMostCommonFromMerge(E[] arrayToMerge) {
        E[] merged = merge(array, arrayToMerge);
        E most = merged[0];
        Integer count = getNumberOfOccurrences(merged, most);
        for (E entry : merged) {
            Integer entryCount = getNumberOfOccurrences(merged, entry);
            if (entryCount > count){
                most = entry;
                count = entryCount;
            }
        }
        return most;
    }
}
