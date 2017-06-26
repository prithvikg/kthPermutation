package com.question.perms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutations {
  
  /**
   * @param input The input string with lowercase characters
   * @return a list of string that has all permutations of the input string
   *         sorted lexicographically
   */
  public static List<String> getAllPermuations(String input) {

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
    List<String> result = new ArrayList<String>();
    getAllPermutationsHelper(chars, map, new StringBuffer(), result);
    return result;
  }
  
  /**
   * 
   * @param chars a list of characters that are sorted
   * @param map a mapping from character to character count
   * @param buf a buffer to hold the string
   * @return
   */
  private static void getAllPermutationsHelper(List<Character> chars,
      Map<Character, CharCount> map, StringBuffer buf, List<String> result) {

    if (chars.size() == 0) {
      result.add(buf.toString());
      return;
    }

    for (int i = 0; i < chars.size(); i++) {
      Character curChar = chars.get(i);
      CharCount charCount = map.get(curChar);
      charCount.decrementCount();
      if (charCount.count == 0) {
        map.remove(curChar);
        chars.remove(i);
      }
      buf.append(curChar);
      getAllPermutationsHelper(chars, map, buf, result);
      buf.deleteCharAt(buf.length() - 1);
      charCount.incrementCount();
      if (charCount.count == 1) {
        map.put(curChar, charCount);
        chars.add(i, curChar);
      }
    }
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
    String input = "abcdeABCDE";
    List<String> allPermuations = getAllPermuations(input);
    for(String s : allPermuations)
      System.out.println(s);
  }

}
