package io.beyonnex.anagram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Anagram {

  private final Set<String> dictionary;

  private final Map<String, Set<String>> anagramMap;

  public Anagram() {
    dictionary = new HashSet<>();
    anagramMap = new HashMap<>();
  }

  // According to wikipedia, the two Strings "New York Times" and "monkeys write" are anagrams.
  // So, I will ignore the spaces and the case of the letters.
  public boolean isAnagram(String first, String second) {

    // Step 0: Adding the two Strings to the dictionary
    dictionary.add(first);
    dictionary.add(second);

    // Step 1: Checking if the anagramMap already contains the two Strings
    if (anagramMap.containsKey(first) && anagramMap.get(first).contains(second)) {
      return true;
    }

    // Step 2: Creating new Strings without spaces and in lower case
    var firstString = first.toLowerCase().replaceAll("\\s", "");
    var secondString = second.toLowerCase().replaceAll("\\s", "");

    // Step 3: Checking if the two Strings have the same length
    if (firstString.length() != secondString.length()) {
      return false;
    }

    // Step 4: Breaking the firstString into characters and counting the number of each character
    var letterCount = new HashMap<Character, Integer>();
    var firstCharArray = firstString.toCharArray();
    for (char ch : firstCharArray) {
      letterCount.put(ch, letterCount.getOrDefault(ch, 0) + 1);
    }

    // Step 5: Checking if the secondString has the same characters as the firstString
    var secondCharArray = secondString.toCharArray();
    for (char ch : secondCharArray) {
      int chCount = letterCount.getOrDefault(ch, 0);
      if (chCount == 0) {
        return false;
      }
      letterCount.put(ch, chCount - 1);
    }

    // Step 6: Adding the two Strings to the anagramMap as they are anagrams
    addAnagramsToAnagramMap(first, second);
    addAnagramsToAnagramMap(second, first);

    // Step 7: Returning true as the two Strings are anagrams
    return true;
  }

  public Set<String> previousAnagrams(String test) {

    // Step 0: Adding the test String to the dictionary
    dictionary.add(test);

    // Step 1: Checking if the anagramMap contains the test String
    if (!anagramMap.containsKey(test)) {
      return new HashSet<>();
    }

    // Step 2: Adding the test String to the anagrams Set
    var anagrams = new HashSet<String>();
    anagrams.add(test);

    // Step 3: Adding the test String to the unsettledWords Set
    var unsettledWords = new HashSet<>();
    unsettledWords.add(test);

    // Step 4: Looping through the unsettledWords Set
    while (!unsettledWords.isEmpty()) {
      var currentWord = unsettledWords.iterator().next();

      // Step 4.1: Adding the anagrams of the currentWord to the anagrams Set
      anagramMap
          .get(currentWord)
          .forEach(
              word -> {
                if (!anagrams.contains(word)) {
                  anagrams.add(word);
                  unsettledWords.add(word);
                }
              });

      // Step 4.2: Removing the currentWord from the unsettledWords Set
      unsettledWords.remove(currentWord);
    }

    // Step 5: Removing the test String from the anagrams Set
    anagrams.remove(test);

    // Step 6: Returning the anagrams Set
    return anagrams;
  }

  public Set<String> allAnagrams(String test) {

    // Step 0: Adding the test String to the dictionary
    dictionary.add(test);

    // Step 1: Declaring the anagrams Set
    var anagrams = new HashSet<String>();

    // Step 2: Looping through the dictionary Set to find the anagrams of the test String
    dictionary.forEach(
        word -> {

          // Step 2.1: Checking if the word is an anagram of the test String
          if (isAnagram(test, word)) {

            // Step 2.1.1: Adding the word to the anagrams Set
            anagrams.add(word);

            // Step 2.1.2: Adding the two words to the anagramMap as they are anagrams
            addAnagramsToAnagramMap(test, word);
            addAnagramsToAnagramMap(word, test);
          }
        });

    // Step 3: Removing the test String from the anagrams Set
    anagrams.remove(test);

    // Step 4: Returning the anagrams Set
    return anagrams;
  }

  private void addAnagramsToAnagramMap(String first, String second) {
    if (anagramMap.containsKey(first)) {
      anagramMap.get(first).add(second);
    } else {
      var anagrams = new HashSet<String>();
      anagrams.add(second);
      anagramMap.put(first, anagrams);
    }
  }
}
