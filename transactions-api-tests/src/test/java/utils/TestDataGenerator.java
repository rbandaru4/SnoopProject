package utils;

public class TestDataGenerator {
    public static String getCustomerId(int customerNumber) {
        switch (customerNumber) {
            case 1: return "b3c8f5d2-4a6e-4c0b-9f7d-8f1c2e3a4b5c";
            case 2: return "746c51bc-bdb9-44d2-9a3e-c4715bc91ee4";
            case 3: return "5723a60b-b7f5-4259-b670-43bd3be1cf90";
            case 4: return "13ef28a8-9488-4d19-ba2f-3ff44912c5e8";
            case 5: return "0828a547-f4bf-433a-b3ef-0dc70d6bad8a";
            default: throw new IllegalArgumentException("Invalid customer number: " + customerNumber);
        }
    }
}