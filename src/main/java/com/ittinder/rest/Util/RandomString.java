package com.ittinder.rest.Util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {
  public static String generate () {//Definitely not copied from stackoverflow
    final int length = 32;
    final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();

    Random random = new SecureRandom();
    StringBuilder password = new StringBuilder();

    for (int i = 0; i < length; i++) {
      password.append(allAllowed[random.nextInt(allAllowed.length)]);
    }

    return password.toString();
  }
}
