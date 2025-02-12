# Simplifying-Text-with-Word-Embeddings
The Text Simplifier Application is a Java-based utility designed to simplify text by replacing complex words with simpler alternatives based on word embeddings and a predefined list of simpler words. The application processes text files and outputs a simplified version of the input text.

Features

1. Interactive Menu
o Users can specify file paths for:

1. Word embeddings file
2. Google-1000 words file (list of simpler words)
3. Input text file
4. Output text file

o Option to exit the application gracefully.

2. Text Simplification
 
o Replaces complex words in the input text with the most similar simpler word
from the Google-1000 words list.

o Utilizes cosine similarity to find the best match for each complex word based
on embedding vectors.

3. File Handling
   
o Reads word embeddings and Google-1000 words from user-specified files.

o Processes input text and writes the simplified text to an output file.

4. Embedding Loading
 
o Embedding vectors for words are stored in a HashMap for efficient lookup.

o Handles multi-dimensional embedding vectors parsed from a text file.

5. Google-1000 Words Set
   
o Loads a list of predefined simpler words into a Set for quick lookups.

o Ensures that replacements are limited to this curated list.

6. Cosine Similarity Computation
   
o Calculates the similarity between word vectors to determine the most
appropriate simpler word.

Classes and Responsibilities

1. TextSimplifierApp
   
o Provides the main entry point for the application.

o Manages user interaction and controls program flow.

2. LoadFiles
   
o Handles file loading for word embeddings and Google-1000 words.

o Parses data into HashMap and Set for efficient processing.

3. TextSimplifierAPI
o Contains the core logic for text simplification.

o Implements cosine similarity to measure vector similarity.

Dependencies

 Java Development Kit (JDK) 8 or higher.

 Text files for embeddings, Google-1000 words, input, and output.


How to Run

1. Compile the program:
   
javac ie/atu/sw/*.java

2. Run the program:
   
java ie.atu.sw.TextSimplifierApp

3. Follow the interactive menu to provide:

Menu: (Enter your choice : 1 or 2)
1. To Enter the path and names of these files (a. Embeddings b. Google-1000 words c. Input text d. Output text file)

2. To Exit   

Enter your choice:

Enter the path and anme of the embeddings file:

   
o File paths for embeddings, Google-1000 words, input text, and output text.

Eg: C:\Users\Sharo\Desktop\datastructuresassignment\embeddings.txt

C:\Users\Sharo\Desktop\datastructuresassignment\google-1000.txt

C:\Users\Sharo\Desktop\datastructuresassignment\inputText.txt

C:\Users\Sharo\Desktop\datastructuresassignment\outputText.txt

Limitations

1. Requires embedding vectors and Google-1000 words file in specific formats.
   
2. SimplificaƟon is limited to words present in the embeddings file.
   
3. Assumes all input files are UTF-8 encoded.

   
Future Enhancements

 The program uses only Cosine Similarity computation for finding similar words, and
the results are not perfect. This can be further improved by trying out other methods
for computing similarity.

 Improved UI/UX with graphical or web-based interaction.

 Enhanced error handling for malformed or missing files.
