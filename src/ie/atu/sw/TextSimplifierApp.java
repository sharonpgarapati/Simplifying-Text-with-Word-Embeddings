package ie.atu.sw;

import java.io.*;
import java.util.*;


/**
 * 
 * @author Sharon Garapati
 * The main class for the Text Simplifier application.
 * 
 */
public class TextSimplifierApp {
	
   /**
    * private static String embeddingFile,googleWordsFile,inputFilePath,outputFilePath;
	* Entry point of the program.
	* The main method
	* @param args for main method.
	*/
    public static void main(String[] args) {
    	
        /**
    	 *Running Time  O(1): Instantiating the application and calling the menu method is a constant-time operation.
    	 */
    	TextSimplifierApp app = new TextSimplifierApp();
        app.startMenu();                     
    } 
    
    /**
     *  The main menu logic for user interaction.
     *  Source from Class Notes.
     */
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        String embeddingFile,googleWordsFile,inputFilePath,outputFilePath;
               
        while (running) {
        	
        	/**
        	 *  Running Time O(1): Printing the menu and capturing user input is a constant-time operation.
        	 */
            System.out.println("\nMenu: (Enter your choice : 1 or 2");
            System.out.println("1. To Enter the path and names of these files "
            		+ "( a.Embeddings "
            		+ "b.Google-1000 words "
            		+ "c.Input Text "
            		+ "d.Output Text File ) ");
            System.out.println("2. To Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            /**
             *  Consume newline
             */
            scanner.nextLine();
            switch (choice) {  
            
            	/**
            	 * Running Time O(1): Capturing file paths is a constant-time operation (independent of file size).
            	 */
                case 1:
                    System.out.print("Enter the path and name of the embeddings file: \n ");
                      embeddingFile = scanner.nextLine();
                     System.out.print("Enter the path and name of the Google-1000 file: \n ");
                      googleWordsFile = scanner.nextLine();
                     System.out.print("Enter the path and name of the Input Text file: \n ");
                      inputFilePath = scanner.nextLine();
                     System.out.print("Enter the path and name of the Output file: \n ");
                      outputFilePath = scanner.nextLine();
                     try {
                     	
                     	 /**
                     	  *  Running Time O(n): Loading embeddings involves reading a file and parsing it into a HashMap
                     	  */
                         Map<String, double[]> embeddingsMap = LoadFiles.loadEmbeddings(embeddingFile);
                              	
                                         
                         /**
                          * Running Time O(n): Loading Google-1000 words involves reading a file into a set.
                          */
                         Set<String> google1000 = LoadFiles.loadGoogleWords(googleWordsFile);
                         
                         
                         /**
                          *  Running Time O(n): Reading the input text file involves reading all lines from the file.
                          */
                         String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(inputFilePath)));
                         
                         /**
                          *  Running Time O(1): Instantiating the TextSimplifierAPI
                          */
                         TextSimplifierAPI simplifier = new TextSimplifierAPI(embeddingsMap, google1000);
                         
                         /**
                          *  Running Time O(1): Printing input text is independent of its content.
                          */
                         System.out.println("\nInput Text is: \n"+content );

                        /**
                         * Running Time O(L * N): Simplifying the text involves iterating over the text and matching against embeddings.
                         * L = Number of words in the input text, N = Number of embeddings. 
                         */
                         String simplifiedContent = simplifier.simplifyText(content,embeddingsMap, google1000);
                         
                        /**
                         * Running Time O(L): Writing the simplified content to the output file. 
                         */
                        java.nio.file.Files.write(java.nio.file.Paths.get(outputFilePath), simplifiedContent.toString().trim().getBytes());
                        
                         /**
                          * Running Time  O(1): Printing simplified content to the console.
                          */
                        System.out.println("\nSimplified version is: \n"+ simplifiedContent.toString());
                        
                        System.out.println("\n Simplified version is saved to: " + outputFilePath);

                     } catch (IOException e) {
                    	 
                    	/**
                    	 *  Running Time O(1): Printing error messages is a constant-time operation
                    	 */
                         System.err.println("Error: " + e.getMessage());
                     }

                    break;
                case 2:
                	
                	 /**
                	  *  Running Time O(1): Exiting the loop is a constant-time operation.
                	  */
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                default:
                	 /**
                	  *  Running Time O(1): Handling invalid input is a constant-time operation.
                	  */
                    System.out.println("Invalid choice. Please try again.");
            }
        }     
        /**
         *  Running Time O(1): Closing the scanner is a constant-time operation.
         */
        scanner.close();       
      
    }

}
