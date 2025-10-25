import java.io.*;
import java.util.*;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Search Note");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    searchNote();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // --- Write notes using FileWriter ---
    private static void addNote() {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            System.out.print("Enter note title: ");
            String title = sc.nextLine();
            System.out.println("Enter note content (type 'END' on a new line to finish):");

            StringBuilder content = new StringBuilder();
            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                content.append(line).append("\n");
            }

            fw.write("Title: " + title + "\n");
            fw.write(content.toString());
            fw.write("-----\n"); // separator
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // --- Read all notes using FileReader / BufferedReader ---
    private static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- All Notes ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found yet.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    // --- Search for a note by title ---
    private static void searchNote() {
        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Search Results ---");
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(keyword)) {
                    found = true;
                    System.out.println(line);
                }
            }
            if (!found) System.out.println("No matching notes found.");
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found.");
        } catch (IOException e) {
            System.out.println("Error searching notes: " + e.getMessage());
        }
    }
}
