import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.util.Scanner;

public class ControllerTest {

    @BeforeAll
    public static void setup() {
        // Delete the generated file if it exists
        try {
            Files.deleteIfExists(Paths.get("repository/output_1.pim"));
            Files.deleteIfExists(Paths.get("repository/output_2.pim"));
            Files.deleteIfExists(Paths.get("repository/output_3.pim"));
        } catch (IOException e) {}
    }

    @Test
    public void testMakeDir() {
        // Set up initial state
        final String directoryName = "repository";
        File directory = new File(directoryName);

        // Clean up any existing directory
        if (directory.exists()) {
            // Skip the test if the directory already exists
            return;
        }

        // Call the method under test
        Controller.makeDir();

        // Verify that the directory was created
        assertTrue(directory.exists());
        assertTrue(directory.isDirectory());
    }

    // Test Cases
    @Test
    public void testInputCase1() throws IOException {

        // Read the input set from the file
        List<String> inputSetLines = Files.readAllLines(Paths.get("test/test_case/case_1.txt"));

        // Join the lines with a newline character to simulate multiple prompt inputs
        String inputSet = String.join("\n", inputSetLines);

        // Create a Scanner with the input set
        Scanner scanner = new Scanner(inputSet);

        // Run the mainInterface method
        Controller.mainInterface(scanner);

        // Read the generated file and the expected output file
        List<String> generatedFileLines = Files.readAllLines(Paths.get("repository/output_1.pim"));
        List<String> expectedOutputLines = Files.readAllLines(Paths.get("repository/test_case_1.pim"));

        // Assert that the generated file matches the expected output file
        assertEquals(expectedOutputLines, generatedFileLines);
    }

    @Test
    public void testInputCase2() throws IOException {

        // Read the input set from the file
        List<String> inputSetLines = Files.readAllLines(Paths.get("test/test_case/case_2.txt"));

        // Join the lines with a newline character to simulate multiple prompt inputs
        String inputSet = String.join("\n", inputSetLines);

        // Create a Scanner with the input set
        Scanner scanner = new Scanner(inputSet);

        // Run the mainInterface method
        Controller.mainInterface(scanner);

        // Read the generated file and the expected output file
        List<String> generatedFileLines = Files.readAllLines(Paths.get("repository/output_2.pim"));
        List<String> expectedOutputLines = Files.readAllLines(Paths.get("repository/test_case_2.pim"));

        // Assert that the generated file matches the expected output file
        assertEquals(expectedOutputLines, generatedFileLines);
    }

    @Test
    public void testInputCase3() throws IOException {

        // Read the input set from the file
        List<String> inputSetLines = Files.readAllLines(Paths.get("test/test_case/case_3.txt"));

        // Join the lines with a newline character to simulate multiple prompt inputs
        String inputSet = String.join("\n", inputSetLines);

        // Create a Scanner with the input set
        Scanner scanner = new Scanner(inputSet);

        // Run the mainInterface method
        Controller.mainInterface(scanner);

        // Read the generated file and the expected output file
        List<String> generatedFileLines = Files.readAllLines(Paths.get("repository/output_3.pim"));
        List<String> expectedOutputLines = Files.readAllLines(Paths.get("repository/test_case_3.pim"));

        // Assert that the generated file matches the expected output file
        assertEquals(expectedOutputLines, generatedFileLines);
    }

    @AfterAll
    public static void cleanup() {
        // Delete the generated file if it exists
        try {
            Files.deleteIfExists(Paths.get("repository/output_1.pim"));
            Files.deleteIfExists(Paths.get("repository/output_2.pim"));
            Files.deleteIfExists(Paths.get("repository/output_3.pim"));
        } catch (IOException e) {}
    }
}
