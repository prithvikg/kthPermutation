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
  
}
