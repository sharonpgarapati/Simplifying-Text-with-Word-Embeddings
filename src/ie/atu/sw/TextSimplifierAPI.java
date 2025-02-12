package ie.atu.sw;

import java.util.*;

public class TextSimplifierAPI {
	private final Map<String, double[]> embeddings;
    private final Map<String, double[]> googleEmbeddings;
    
    /**
     * Constructor to initialize the API with embeddings and filter Google-1000 embeddings
     * 
     * Running Time: O(n), where n is the size of googleWords set.
     * 
     * Rationale: Iterates over `googleWords` once. The `containsKey` operation on `embeddings` is O(1) (hashmap lookup), 
     * and `put` on `googleEmbeddings` is also O(1). Thus, the total complexity is O(n)
     * Get embedding vector for Google-1000 words.
     *
     * @param embeddings
     * @param googleWords
     * 
     * Source: Class Notes & OOSD project code
     */
    
    public TextSimplifierAPI(Map<String, double[]> embeddings, Set<String> googleWords) {
        this.embeddings = embeddings;
        this.googleEmbeddings = new HashMap<>();
        for (String word : googleWords) {
            if (embeddings.containsKey(word)) {
                googleEmbeddings.put(word, embeddings.get(word));
            }
        } 
    }

   /**
    * Method to simplify the input text.
    * 
    * Running Time: O(m * k * d), where:
    * - m is the number of words in `content`
    * - k is the size of google1000
    * - d is the dimensionality of the embeddings.
    * 
    * Rationale: 
    * 1. Splitting the input text into words: O(m)
    * 2. For each word, if it's not in google1000 (O(1) lookup for a set) and present in embeddingsMap (O(1)):
    * - Iterates through all words in google1000 (O(k))
    * - For each word in google1000, calculates cosine similarity (O(d) for d-dimensional vectors)
    * Combining these: O(m) for splitting + O(m * k * d) for word processing.
    * 
    * Source:  Class Notes
    */
    
    public String simplifyText(String content, Map<String, double[]> embeddingsMap, Set<String> google1000) {
    	StringBuilder result = new StringBuilder();
    	
    	/**
    	 * split the input text to words O(n)
    	 */
        String[] words = content.split("\\s+"); 
        
         /**
          * Running Time: O(n)
          */
        for (String word : words) {  
        	
        	/**
        	 * Running Time O(1) for HashSet and HashMap lookups
        	 */
            if (!google1000.contains(word) && embeddingsMap.containsKey(word)) { 
            	
            	/**
            	 * Start with dissimilarity
            	 */
                double startDistance = -10; 
                String replacementWord = word;  
                
                /**
                 * Running Time: O(k)
                 */
                for (String t : google1000) {  
                	
                	
                  /**
                   * compute similarity using Cosine Similarity 	
                   */
             	  double dist = cosineSimilarity(embeddingsMap.get(word), googleEmbeddings.get(t));
             	   if (dist > startDistance) {
             		   replacementWord = t;
             		   startDistance =dist;
             	   } 
             	   
             	   /**
             	    * reset distance
             	    */
             	   dist=-10;
                }
                
                result.append(replacementWord).append(" ");
            } else {
                result.append(word).append(" ");
            }
            
        }
        
        /**
         * end for
         */
         return result.toString().trim();
        
    }

   /**
    * Method to compute cosine similarity.
    * 
    * Running Time: O(d), where d is the dimensionality of the embedding vectors.
    * 
    * Rationale: The method iterates over the embedding vector elements once, 
    * performing constant-time operations (addition and multiplication) for each element. 
    * Thus, the complexity is proportional to the vector size.
    * 
    * @param vec1
    * @param vec2
    * @return
    * 
    * {@link https://stackoverflow.com/questions/1746501/can-someone-give-an-example-of-cosine-similarity-in-a-very-simple-graphical-wa}
    * 
	* Modified to do cosineSimilarity
    * 
    */
    public static double cosineSimilarity(double[] vec1, double[] vec2) {
        if (vec1.length != vec2.length) return 0.0;

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        
        /**
         * Running Time: O(d)
         */
        for (int i = 0; i < vec1.length; i++) { 
            dotProduct += vec1[i] * vec2[i];
            normA += Math.pow(vec1[i], 2);
            normB += Math.pow(vec2[i], 2);
        }
        
        /**
         *  Running Time: O(1)
         */
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)); 
    }


}
