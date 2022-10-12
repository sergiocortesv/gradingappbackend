package com.k1687.leisure.grading.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class BubbleSortTesting {

    @Test
    public void bubbleSortTest(){
        int[] input = new int[]{3,8,1,5,2};
        int[] sortedArr = bubbleSort(Arrays.copyOf(input,5));
        Arrays.sort(input);
        for(int i=0; i<sortedArr.length; i++){
            System.out.println(sortedArr[i]);
        }

        for(int i=0; i<input.length;i++){
            assertEquals(input[i], sortedArr[i]);
        }

    }

    public int[] bubbleSort(int [] arr){
        for(int i=1;i< arr.length; i++){
            for(int j=0 ; j<arr.length-i; j++){
                if(arr[j] > arr[j+1]){
                    int a = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = a;
                }
            }
        }
        return arr;
    }

}
