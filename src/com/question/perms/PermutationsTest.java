package com.question.perms;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class PermutationsTest {
  
  
  @Test
  public void testBasic(){
    String input = "abc";
    List<String> result = Permutations.getAllPermuations(input);
    assertEquals(6, result.size());
    assertEquals("[abc, acb, bac, bca, cab, cba]", result.toString());
  }
  
  @Test
  public void testRepeat(){
    String input = "mom";
    List<String> result = Permutations.getAllPermuations(input);
    assertEquals(3, result.size());
    assertEquals("[mmo, mom, omm]", result.toString());
  }
  
  @Test
  public void testRepeatWithMath(){
    String input = "dabdbdccdc";
    List<String> result = Permutations.getAllPermuations(input);
    long expectedSize = getExpectedSize(input);
    assertEquals(expectedSize, result.size());
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
