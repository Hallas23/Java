package filer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class PrintWriterTal {
    
    public static void main(String[] args) {
        try {
            String fileName = "C:/MAD/Ud/tal.txt";
            PrintWriter printWriter = new PrintWriter(fileName);
            
            // indlaes antal tal i filen
            Scanner scan = new Scanner(System.in);
            System.out.print("Antal tal der skal skrives i filen: ");
            int antal = scan.nextInt();
            
            // skab tilfaeldige tal generator Random
            Random rnd = new Random();
            
            // generer og skriv de tilfaeldige tal
            for (int n = 1; n <= antal; n++) {
                int tal = rnd.nextInt(10000);
                printWriter.println(tal);
            }
            
            // flush og skriv filen
            printWriter.flush();
            printWriter.close();
            scan.close();
            System.out.println("Filen er lavet");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
