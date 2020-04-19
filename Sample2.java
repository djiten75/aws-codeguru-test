package com.amazon.aws.codegurusample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Sample2 {
    String username="testuser";
    String password="test123";
    public static void main(String[] args) {
        System.out.println("Hello world");
        Sample2 sample = new Sample2();
        sample.testPassword();
        String vulnerableRandomString = sample.testRandomGenerator();
        System.out.println("Vulnerable random string:"+vulnerableRandomString);
        try {
            sample.leakingResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String testRandomGenerator() {
        Random r = new Random();
        String vulnerableRandomString =  Long.toHexString(r.nextLong());
        System.out.println("Vulnerable random string:"+vulnerableRandomString);
        return  vulnerableRandomString;
    }

    private void leakingResource() throws IOException {
        Process p = Runtime.getRuntime().exec("date");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
    }
    private void testPassword() {
        System.out.println("Username is:"+username);
        System.out.println("Password is:"+password);

    }

}
