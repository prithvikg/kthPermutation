package com.fortanix.kthperm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KthPermutation {
  
  /**
   * This method finds the kth perumutation using combinatorics
   * @param input the input string
   * @param k the integer k for the kth permutation
   * @return the kth permutation
   */
  public static String getKthPermuation(String input, long k){
    
    //Boiler plate code to create a list of unique characters from string
    //and a map maintaining the character count for the string
    List<Character> chars = new ArrayList<Character>();
    Map<Character, CharCount> map = new HashMap<Character, CharCount>();
    for (int i = 0; i < input.length(); i++) {
      char charAt = input.charAt(i);
      if (map.containsKey(charAt)) {
        map.get(charAt).incrementCount();
      } else {
        CharCount cc = new CharCount(charAt, 1);
        chars.add(charAt);
        map.put(charAt, cc);
      }
    }
    Collections.sort(chars);
    return getPermutation(k-1, input.length(), map, chars);
  }
  
  //Helper method, uses combinatorics to determine the first character before recursively calling itself
  private static String getPermutation(long k, int strlen, Map<Character, CharCount> map, List<Character> chars) {
    if (map.isEmpty()) {
        return "";
    }
    long n = getExpectedSize(strlen, map);
    
    if (k > n) return ""; // invalid. Should never reach here.
    long f = 0;
    
    
    for (int i = 0; i < chars.size(); i++) {
      Character curChar = chars.get(i);
      CharCount charCount = map.get(curChar);
      charCount.decrementCount();
      strlen--;
      if (charCount.count == 0) {
        map.remove(curChar);
        chars.remove(i);
      }
      
      long temp = getExpectedSize(strlen, map);
      f += temp;
      if(f <= k){
        charCount.incrementCount();
        strlen++;
        if (charCount.count == 1) {
          map.put(curChar, charCount);
          chars.add(i, curChar);
        }
        continue;
      }
      else{
        f = f - temp;
        return curChar + getPermutation(k - f, strlen, map, chars);
      }
    }
    
    //SHould not reach here unless k is too large
    return "";
  }
  
  private static long getExpectedSize(int n, Map<Character, CharCount> map){
    long num = factorial(n);
    long den = 1;
    for(Entry<Character, CharCount> entry : map.entrySet()){
     den = den * factorial(entry.getValue().count);
    }
    return num/den;
  }
  
  private static long factorial(int n){
    int i;
    long fact=1;  
    for(i=1; i<=n; i++){    
        fact=fact*i;    
    }
    return fact;
  }
  
  /**
   * Simple wrapper class to hold a Character and a count
   * @author prithvi
   *
   */
  private static class CharCount {
    Character c;
    int count;

    public CharCount(Character c, int count) {
      this.c = c;
      this.count = count;
    }

    public void incrementCount() {
      this.count += 1;
    }

    public void decrementCount() {
      this.count -= 1;
    }
  }
  
  public static void main(String[] args) {
    String input = "dabdbdccdc";
    String result = getKthPermuation(input, 161);
    System.out.println(result);
  }
}
