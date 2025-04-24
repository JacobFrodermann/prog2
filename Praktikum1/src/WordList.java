import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordList {
    public List<String> possibleWords;
    public int pickedWordIndex;
    public List<Character> guessedChars;
    public ArrayList<Character> alphabetList = new ArrayList<>(Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    ));

    public WordList() {
        possibleWords = new ArrayList<String>();
        guessedChars = new ArrayList<>();
        possibleWords.add("Haus");
        possibleWords.add("Baum");
    }

    public WordList(File file){
        possibleWords = new ArrayList<String>();
        guessedChars = new ArrayList<>();
        if(file.exists() && file.isFile() && file.canRead()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    possibleWords.add(line);
                }
            } catch (IOException e){
                System.out.println(e);
            }
        } else {
            System.err.println("Error: File is not readable");
        }
    }

    public void pickRandomWord() {
        Random random = new Random();
        pickedWordIndex = random.nextInt(possibleWords.size());
    }

    public String getMaskedWord() {
        String word = possibleWords.get(pickedWordIndex);
        StringBuilder maskedWord = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (guessedChars.contains(Character.toLowerCase(c))) {
                maskedWord.append(c);
            } else {
                maskedWord.append('_');
            }
        }

        return maskedWord.toString();
    }


    public String getWord() {
        return possibleWords.get(pickedWordIndex);
    }

    public boolean guessChar(Character c) {
        if(guessedChars.contains(c)) return true;

        if(Character.isUpperCase(c)){
            guessedChars.add(Character.toLowerCase(c));
        } else {
            guessedChars.add(c);
        }
        return possibleWords.get(pickedWordIndex).contains(String.valueOf(c));
    }

    public boolean hadBeenGuessed(){
        for (Character c : getWord().toLowerCase().toCharArray()) {
            if(!guessedChars.contains(c)) return false;
        }
        return true;
    }



}
