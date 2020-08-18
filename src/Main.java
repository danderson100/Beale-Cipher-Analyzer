import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Author: David Anderson
*
* Purpose: This program takes input files as parameters (they must be stored in the program
* directory) to check various potential texts and see if they make legible words based on Beale's
* numeric ciphers. It creates arrays of each of the ciphers, and then creates another array containing
* the first letter of every word in the submitted text. The text must be properly formatted and have
* spaces between each word.
*
* The main PROBLEM currently is that Beale seems to have used older versions of texts, so this program
* does not work properly with the Declaration of Independence because if the file has even one extra word
* it throws off the entire index and shifts everything.
*
* The other problem is that it could all be a hoax. I just made this for fun after reading about the Beale ciphers.
*
* Good luck to anyone who wants to use it!
 */

public class Main {

    public static void main(String[] args) {

        createLetterArray();
        createCypherArray();
        getLetters(createLetterArray(), createCypherArray());


    }

    /*
    * Purpose: this method uses the previously created cipher array as
    * the index and letter array, and outputs the combinations of letters
    * found at each index based on the cipher
    *
    * @param letterArray, is the List of first letters of each word of
    * a potential cipher text submitted by the user.
    *
    * @param index, is the Beale cipher numbers that are being used as indexes
    * so it corresponds to the word at the proper position
     */
    private static void getLetters(List<String> letterArray, List<Integer> index) {

        for (Integer integer : index) {
            if (integer != 0) {
                System.out.print(letterArray.get(integer -1  ));
            } else {
                System.out.print("_");
            }

        }


        //the first number is the equal to the first letter of the word at that index
    }
    /*
     * Purpose: this method finds the Beale cipher(s) from the directory
     * and converts them into a List of Integers with no commas or spaces. This makes
     * it easy to use the numbers as indexes.
     *
     * @return numbers, is the List<Integer> of numbers in the given Beale cipher.
     */
    private static List<Integer> createCypherArray() {
        List<Integer> numbers = new ArrayList<>();
        try {
            //put in the proper file name and make sure it's in the program directory
            File cypher2 = new File("sampletext.txt");
            Scanner scanner = new Scanner(cypher2);

            while (scanner.hasNext()) {
                String line = scanner.nextLine().replace(",", " ");

                String[] split = line.split("\\s+");

                for (String item : split) {
                    if (item.length() < 5) {
                        int num = Integer.parseInt(item);
                        numbers.add(num);

                    }


                }

            }
            scanner.close();

        } catch (FileNotFoundException e ) {
            System.out.println("Error: " + e);
        }

        return numbers;

    }
    /*
     * Purpose: this method takes whatever text the user wants to test
     * as a potential cipher, extracts the first letter of each word, and returns
     * the array of letters.
     *
     * @return letterArray, is the array of first letters of each word.
     */
    private static List<String> createLetterArray() {
        List<String> keyWords = new ArrayList<>();
        List<String> letterArray = new ArrayList<>();

        try {
            //put in a raw text file of the text you wish to check
            File declaration = new File("ciphertext.txt");
            Scanner scanner = new Scanner(declaration);

            while (scanner.hasNext()) {
                String word = scanner.next();
                keyWords.add(word);
                letterArray.add(word.substring(0, 1));
            }

            scanner.close();
        } catch (FileNotFoundException e ) {
            System.out.println("Error: " + e);
        }

        return letterArray;

    }
}
