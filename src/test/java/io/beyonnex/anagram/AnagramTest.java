package io.beyonnex.anagram;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnagramTest {

  private Anagram anagram;

  @BeforeEach
  void setUp() {
    anagram = new Anagram();
  }

  @Test
  void testIsAnagram_givenAnagrams_shouldReturnTrue() {
    assertThat(anagram.isAnagram("New York Times", "monkeys write")).isTrue();
    assertThat(anagram.isAnagram("Church of Scientology", "rich chosen goofy cult")).isTrue();
    assertThat(anagram.isAnagram("McDonald's restaurants", "Uncle Sam's standard rot")).isTrue();
  }

  @Test
  void testIsAnagram_givenNonAnagrams_shouldReturnFalse() {
    assertThat(anagram.isAnagram("amin", "aman")).isFalse();
    assertThat(anagram.isAnagram("mani", "mann")).isFalse();
    assertThat(anagram.isAnagram("nima ", "nimaa")).isFalse();
  }

  @Test
  void testPreviousAnagrams_givenThreeAnagrams_shouldReturnPreviouslyFoundAnagrams() {
    assertThat(anagram.isAnagram("amin", "nima")).isTrue();
    assertThat(anagram.isAnagram("amin", "ali")).isFalse();
    assertThat(anagram.isAnagram("amin", "mina")).isTrue();
    assertThat(anagram.previousAnagrams("nima")).containsExactlyInAnyOrder("amin", "mina");
    assertThat(anagram.previousAnagrams("amin")).containsExactlyInAnyOrder("nima", "mina");
  }

  @Test
  void testPreviousAnagrams_givenFiveAnagrams_shouldOnlyReturnPreviouslyFoundAnagrams() {
    assertThat(anagram.isAnagram("amin", "nima")).isTrue();
    assertThat(anagram.isAnagram("mani", "nami")).isTrue();
    assertThat(anagram.isAnagram("amin", "ali")).isFalse();
    assertThat(anagram.isAnagram("amin", "mina")).isTrue();
    assertThat(anagram.previousAnagrams("nima")).containsExactlyInAnyOrder("amin", "mina");
    assertThat(anagram.previousAnagrams("amin")).containsExactlyInAnyOrder("nima", "mina");
    assertThat(anagram.previousAnagrams("amin")).containsExactlyInAnyOrder("nima", "mina");
    assertThat(anagram.previousAnagrams("mani")).containsExactlyInAnyOrder("nami");
    assertThat(anagram.previousAnagrams("nami")).containsExactlyInAnyOrder("mani");
  }

  @Test
  void testAllAnagrams_givenFiveAnagrams_shouldReturnPreviouslyEnteredAnagrams() {
    assertThat(anagram.isAnagram("amin", "nima")).isTrue();
    assertThat(anagram.isAnagram("mani", "nami")).isTrue();
    assertThat(anagram.isAnagram("amin", "ali")).isFalse();
    assertThat(anagram.isAnagram("amin", "mina")).isTrue();
    assertThat(anagram.allAnagrams("anim"))
        .containsExactlyInAnyOrder("amin", "mina", "mani", "nami", "nima");
  }
}
