import java.security.SecureRandom;

class PasswordGenerator {

    // Define the character sets for the password
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    // Combine all character sets
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    // Use SecureRandom for better security
    private static final SecureRandom random = new SecureRandom();

    // Method to generate a strong random password
    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure at least one character from each character set
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the remaining password length with random characters from all character sets
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Shuffle the characters in the password for randomness
        return shuffleString(password.toString());
    }

    // Method to shuffle the password (Optional but adds extra randomness)
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        int passwordLength = 12;  // Set the desired length for your password
        String strongPassword = generatePassword(passwordLength);
        System.out.println("Generated Strong Password: " + strongPassword);
    }
}
