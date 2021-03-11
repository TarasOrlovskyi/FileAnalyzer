package orlovskyi;

import java.io.*;

public class FileAnalyzer {
    public static void main(String[] args) {
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        try {
            System.out.println("File -> '" + args[0] + "';\n Word -> '" + args[1] + "'");
            File pathToFile = new File(args[0]);
            if (!pathToFile.exists()){
                throw new FileNotFoundException("File doesn't exist!");
            }

            int count = fileAnalyzer.getAmountOccurrences(pathToFile, args[1]);
            System.out.println(count);

            System.out.println(fileAnalyzer.getSentences(pathToFile, args[1]));
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("There is no word for search!");
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    }

    public int getAmountOccurrences(File file, String word){
        String content = readFile(file);
        int count = 0;
        String[] contentArrByWord = content.split("[-.,!?\\r\\n ]");
        for (String contentWord : contentArrByWord){
            if (contentWord.toLowerCase().contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }

    public String getSentences(File file, String word){
        String contentOfFile = readFile(file);
        String[] contentArray = contentOfFile.split("[!?.\\r\\n]");
        StringBuilder stringBuilder = new StringBuilder();
        for (String sentence : contentArray){
            if (sentence.toLowerCase().contains(word.toLowerCase())){
                stringBuilder.append(sentence + "\n");
            }
        }
        return stringBuilder.toString();
    }

    private String readFile(File pathToFile) {
        byte[] buffer = new byte[(int) pathToFile.length()];
        int count;
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(pathToFile)){
            while ((count=inputStream.read(buffer))!=-1){
                stringBuilder.append(new String(buffer, 0, count));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
