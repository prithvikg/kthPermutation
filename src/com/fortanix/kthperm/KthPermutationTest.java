package com.fortanix.kthperm;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.fortanix.perms.Permutations;


public class KthPermutationTest {
  
  @Test
  public void testBasic(){
    String input = "abc";
    int k = 3;
    String kthPermuation = KthPermutation.getKthPermuation(input, k);
    assertEquals("bac", kthPermuation);
    
  }
  
  @Test
  public void testWithNaiveResult(){
    String input = "dabdbdccdc";
    List<String> result = Permutations.getAllPermuations(input);
    //12600
    int k = 11589;
    String kthPermuation = KthPermutation.getKthPermuation(input, k);
    assertEquals(result.get(k-1), kthPermuation);
  }
  
  @Test
  public void testAllDistinct(){
    String input = "abcdeABCDE";
    List<String> result = Permutations.getAllPermuations(input);
    for(int i=0; i<result.size(); i++){
      int k = i + 1;
      String kthPermuation = KthPermutation.getKthPermuation(input, k);
      assertEquals(result.get(k-1), kthPermuation);
    }
  }
  
  @Test
  public void testAll(){
    String input = "dabdbdccdceee";
    List<String> result = Permutations.getAllPermuations(input);
    for(int i=0; i<result.size(); i++){
      int k = i + 1;
      String kthPermuation = KthPermutation.getKthPermuation(input, k);
      assertEquals(result.get(k-1), kthPermuation);
    }
  }
  
  private long getExpectedSize(String input){
    
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i=0; i<input.length(); i++){
      char charAt = input.charAt(i);
      if(map.containsKey(charAt)){
        Integer count = map.get(charAt);
        count += 1;
        map.put(charAt, count);
      }
      else{
        map.put(charAt, 1);
      }
    }
    
    long num = factorial(input.length());
    long den = 1;
    for(Entry<Character, Integer> entry : map.entrySet()){
     den = den * factorial(entry.getValue());
    }
    
    return num/den;
  }
  
  private long factorial(int n){
    int i;
    long fact=1;  
    for(i=1; i<=n; i++){    
        fact=fact*i;    
    }
    return fact;
  }
}
