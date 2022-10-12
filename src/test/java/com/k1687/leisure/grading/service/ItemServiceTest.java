package com.k1687.leisure.grading.service;

import org.junit.jupiter.api.Test;

public class ItemServiceTest {

    @Test
    public void test(){
        int[] prices = new int[]{7,6,4,3,1};

        int maxprofit = 0;
        for(int i=0, j=1 ; i < prices.length-1 ;i++,j++){
            System.out.println( i + "," + j);
            maxprofit += (prices[j] - prices[i]) > 0 ? prices[j] - prices[i] : 0;
        }
        System.out.println("Total " + maxprofit);
    }

    @Test
    public void rotation(){
        int nums[] = new int[]{1,2,3,4};

        int x = nums[nums.length -1];
        int t =0;
        for(int i=0;i<nums.length;i++){
            t = nums[i];
            nums[i] = x;
            x = t;
        }

        for(int i=0;i<nums.length;i++){
            System.out.print( nums[i] + ",");
        }
    }

    public void rotation2(){
        int nums[] = new int[]{1,2,3,4,5,6};
        int k = 6;

        if(k%nums.length > nums.length / 2){
            k = nums.length - (k%nums.length);
            for(int j=0; j < k; j++){
                int x = nums[0];
                int t =0;
                for(int i=nums.length-1; i>=0; i--){
                    t = nums[i];
                    nums[i] = x;
                    x = t;
                }
            }
        }else{
            for(int j=0; j < (k%nums.length) ; j++){
                int x = nums[nums.length -1];
                int t =0;
                for(int i=0;i<nums.length;i++){
                    t = nums[i];
                    nums[i] = x;
                    x = t;
                }
            }
        }

        for(int i=0;i<nums.length;i++){
            System.out.print( nums[i] + ",");
        }
    }

    @Test
    public void rotationLeft(){
        int nums[] = new int[]{1,2,3,4,5,6};
        int k=3;

        k = k % nums.length;
        int temp = 0;
        int temp2 = 0;
        for(int z=0,i=0;z<nums.length + 1;z++){
            int t = i + k;
            if(t >= nums.length){
                t = t - (nums.length);
            }

            if(z == 0){
                temp = nums[t];
                nums[t] = nums[i];
            }else{
                temp2 = nums[t];
                nums[t] = temp;
                temp = temp2;
            }
            System.out.println( "i:" + i + " t:" + t + " n[t]:" + temp);
            if(t == 0){
                break;
            }
            i = t;
        }
        for(int i=0;i<nums.length;i++){
            System.out.print( nums[i] + ",");
        }

        System.out.println("odd");
        for(int z=0,i=1;z<nums.length + 1;z++){
            int t = i + k;
            if(t >= nums.length){
                t = t - (nums.length);
            }

            if(z == 0){
                temp = nums[t];
                nums[t] = nums[i];
            }else{
                temp2 = nums[t];
                nums[t] = temp;
                temp = temp2;
            }
            System.out.println( "i:" + i + " t:" + t + " n[t]:" + temp);
            if(t == 1){
                break;
            }
            i = t;
        }


        for(int i=0;i<nums.length;i++){
            System.out.print( nums[i] + ",");
        }

    }

    @Test
    public void rotationX(){
        int nums[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int k=2;

        k = k % nums.length;
        int iter = nums.length % 2 == 0 ? nums.length / k : 1;

        for(int y = 0; y < iter ; y++){
            int temp = 0;
            int temp2 = 0;

            for(int z=0,i=y;z<nums.length + 1;z++){
                int t = i + k;
                if(t >= nums.length){
                    t = t - (nums.length);
                }

                if(z == 0){
                    temp = nums[t];
                    nums[t] = nums[i];
                }else{
                    temp2 = nums[t];
                    nums[t] = temp;
                    temp = temp2;
                }
                if(t == y){
                    break;
                }
                i = t;
            }
            System.out.println("");
            System.out.println("-----------------------" + y);
            for(int i=0;i<nums.length;i++){
                System.out.print( nums[i] + ",");
            }
        }
    }

}
