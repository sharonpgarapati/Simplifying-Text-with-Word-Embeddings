package ie.atu.sw;

import java.io.*;
import java.util.*;


public class LoadFiles {
	
	
    /**
     * Method to load embedding file into a HashMap with words as keys and their corresponding vectors as values.
     * 
     * Time Complexity: O(n * m)
     * - n: Number of lines in the file.
     * - m: Average length of the vector (number of dimensions).
     * 
     * Rationale:
     * - Reading each line from the file takes O(n) time.
     * - Splitting each line into parts using `split` takes O(m) time on average, where m is the number of dimensions in the vector.
     * - Parsing the numeric values into a double array takes O(m).
     * - Storing the word and vector in the HashMap takes O(1) for each insertion.
     * - Overall, the nested operations result in O(n * m) time complexity.
     * 
     * @param embeddingsFile - THe file containing word embeddings.
	 * @throws <IOException> - If an Input/Output error occurs while reading the file
	 * Initialize <Bufferedreader> to read from the specified embeddings file
     * 
     * {@link https://www.youtube.com/watch?v=lHFlAYaNfdo}  AND
     * {@link https://www.youtube.com/watch?v=M8xFzcWiORE}
	 *  AND CLASS NOTES.
	 * 
	 *  Modified to do the following code.
     */
    public static Map<String, double[]> loadEmbeddings(String embeddingFile) throws IOException {
        Map<String, double[]> embeddings = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(embeddingFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                
             /**
              * Get the word and remove comma;
              */
                String word = parts[0].replace(",", ""); 
                
                double[] vector = Arrays.stream(parts, 1, parts.length)
                		
                						/**
                						 * Remove commas
                						 */
                						.map(s -> s.replace(",", "")) 
                                        .mapToDouble(Double::parseDouble).toArray();
                
                embeddings.put(word, vector);                      
            }
        }
        return embeddings;
    }
    
    /**
     * Method to load the Google-1000 words into a HashSet.
     * 
     * Time Complexity: O(n)
     * - n: Number of lines in the file.
     * 
     * Rationale:
     * - Reading each line from the file takes O(n).
     * - Trimming the line to remove extra whitespace is O(1) for each line.
     * - Adding each word to the HashSet takes O(1) per insertion on average.
     * - Overall, the complexity is O(n) because each line is processed independently in linear time.
     * 
     * {@link https://www.youtube.com/watch?v=lHFlAYaNfdo}  AND
     * {@link   https://www.youtube.com/watch?v=M8xFzcWiORE}
	 *  AND CLASS NOTES. 
	 * 
	 *  Modified to do the following code
     */
    public static Set<String> loadGoogleWords(String googleWordsFile) throws IOException {
        Set<String> googleWords = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(googleWordsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                googleWords.add(line.trim());
            }
        }
        return googleWords;
    }

}
