package io.beyonnex.anagram;

import java.util.Scanner;

public class App {

  private static final Anagram anagram = new Anagram();

  public static void main(String[] args) {
    try (var in = new Scanner(System.in)) {
      int selection = 0;
      while (selection != 4) {
        selection = mainMenu(in);
        switch (selection) {
          case 1 -> checkAnagram(in);
          case 2 -> previousAnagrams(in);
          case 3 -> allAnagrams(in);
          case 4 -> System.out.println("Bye!");
          default -> System.out.println("Invalid selection!");
        }
      }
    }
  }

  private static int mainMenu(Scanner in) {
    System.out.println("#############################################");
    System.out.println("###   Beyonnex Anagram coding challenge   ###");
    System.out.println("#############################################");
    System.out.println("Please select the desired option:");
    System.out.println("1. Check if two strings are anagrams");
    System.out.println("2. List all previously found anagrams of a given string");
    System.out.println("3. List all previously entered anagrams of a given string");
    System.out.println("4. Exit");
    System.out.print("Your selection: ");
    try {
      var selection = in.nextLine();
      return Integer.parseInt(selection);
    } catch (Exception e) {
      return 0;
    }
  }

  private static void checkAnagram(Scanner in) {
    System.out.print("Please enter the first string: ");
    var first = in.nextLine();
    System.out.print("Please enter the second string: ");
    var second = in.nextLine();
    if (anagram.isAnagram(first, second)) {
      System.out.println("The two strings are anagrams");
    } else {
      System.out.println("The two strings are not anagrams");
    }
  }

  private static void previousAnagrams(Scanner in) {
    System.out.print("Please enter the test string: ");
    var test = in.nextLine();
    System.out.println("The previously found (unequal) anagrams of the string are:");
    var previousAnagrams = anagram.previousAnagrams(test);
    if (previousAnagrams == null) {
      System.out.println("No anagrams found");
      return;
    }
    previousAnagrams.forEach(System.out::println);
  }

  private static void allAnagrams(Scanner in) {
    System.out.print("Please enter the test string: ");
    var test = in.nextLine();
    System.out.println("The previously entered (unequal) anagrams of the string are:");
    var allAnagrams = anagram.allAnagrams(test);
    if (allAnagrams.isEmpty()) {
      System.out.println("No anagrams found");
    }
    allAnagrams.forEach(System.out::println);
  }
}
