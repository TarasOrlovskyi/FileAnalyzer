package orlovskyi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTest {

    private File file;
    private final FileAnalyzer fileAnalyzer = new FileAnalyzer();

    @BeforeEach
    void before(){
        String contentString = "Perseverance it's best way to successful. So, show the result, otherwise lose! Comprehension come with hard work! You can if you think that you can! Can you?";
        byte[] byteContent = contentString.getBytes();
        file = new File("FileAnalyzerTest.txt");
        try (OutputStream outputStream = new FileOutputStream(file)){
            outputStream.write(byteContent);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void getAmountOccurrences() {
        assertEquals(4, fileAnalyzer.getAmountOccurrences(file, "YoU"));
    }

    @Test
    void getSentences() {
        assertEquals(" You can if you think that you can\n Can you\n", fileAnalyzer.getSentences(file, "yOU"));
    }

    @AfterEach
    void after(){
        file.delete();
    }
}