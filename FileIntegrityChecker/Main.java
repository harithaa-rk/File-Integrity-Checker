import model.FileHash;
import service.FileIntegrityService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, FileHash> hashStorage = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== File Integrity Checker ===");
            System.out.println("1. Generate File Hash");
            System.out.println("2. Verify File Integrity");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter file path: ");
                    String path = sc.nextLine();
                    File file = new File(path);
                    if (file.exists()) {
                        String hash = FileIntegrityService.generateSHA256(file);
                        hashStorage.put(file.getName(), new FileHash(file.getName(), hash));
                        System.out.println("Hash generated and stored for file: " + file.getName());
                        System.out.println("SHA-256: " + hash);
                    } else {
                        System.out.println("File does not exist.");
                    }
                    break;

                case 2:
                    System.out.print("Enter file path to verify: ");
                    String verifyPath = sc.nextLine();
                    File verifyFile = new File(verifyPath);
                    if (verifyFile.exists()) {
                        String currentHash = FileIntegrityService.generateSHA256(verifyFile);
                        FileHash stored = hashStorage.get(verifyFile.getName());

                        if (stored == null) {
                            System.out.println("No stored hash found for this file.");
                        } else if (stored.getHash().equals(currentHash)) {
                            System.out.println("✅ File is intact (Hash matched)");
                        } else {
                            System.out.println("❌ File has been modified! (Hash mismatch)");
                        }
                    } else {
                        System.out.println("File does not exist.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}