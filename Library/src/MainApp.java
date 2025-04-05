import java.util.Scanner;
import java.util.List;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        try
        {
            library.loadFromFile("library.dat");
        }   catch (Exception e)
        {
            System.out.println("Nema prethodnih podataka za ucitavanje.");
        }

        boolean running = true;

        while (running)
        {
            System.out.println("\n--- MENI ---");
            System.out.println("1. Dodaj knjigu");
            System.out.println("2. Obrisi knjigu");
            System.out.println("3. Prikazi sve knjige (po autoru)");
            System.out.println("4. Prikazi sve knjige (po godini)");
            System.out.println("5. Pretrazi knjige");
            System.out.println("6. Sacuvaj podatke");
            System.out.println("0. Izlaz");

            System.out.print("Izaberi opciju: ");
            int opcija = scanner.nextInt();
            scanner.nextLine();

            switch (opcija)
            {
                case 1:
                    System.out.print("Naslov: ");
                    String naslov = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Godina izdanja: ");
                    int godina = scanner.nextInt();
                    scanner.nextLine();

                    library.addBook(naslov, autor, godina);
                    System.out.println("Knjiga dodata.");
                    break;

                case 2:
                    System.out.print("Unesite ID knjige za brisanje: ");
                    int idZaBrisanje = scanner.nextInt();
                    scanner.nextLine();
                    boolean obrisano = library.deleteBook(idZaBrisanje);
                    if (obrisano)
                    {
                        System.out.println("Knjiga obrisana.");
                    }   else
                    {
                        System.out.println("Nije pronadjena knjiga sa tim ID-jem.");
                    }
                    break;

                case 3:
                    List<Book> poAutoru = library.getBooksSortedByAuthor();
                    System.out.println("--- Knjige po Autoru ---");
                    for (Book b : poAutoru)
                    {
                        System.out.println(b);
                    }
                    break;

                case 4:
                    List<Book> poGodini = library.getBooksSortedByYear();
                    System.out.println("--- Knjige po GOdini ---");
                    for (Book b : poGodini)
                    {
                        System.out.println(b);
                    }
                    break;

                case 5:
                    System.out.print("Unesite kljucnu rec: ");
                    String keyword = scanner.nextLine();
                    List<Book> rezultat = library.searchBooks(keyword);
                    System.out.println("--- REZULTATI PRETRAGE ---");
                    for (Book b : rezultat)
                    {
                        System.out.println(b);
                    }
                    break;

                case 6:
                    try {
                        library.saveToFile("library.dat");
                        System.out.println("Podaci uspesno sacuvani.");
                    } catch (IOException e) {
                        System.out.println("Greska pri cuvanju podataka: " + e.getMessage());
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Zatvaranje aplikacije...");
                    break;

                default:
                    System.out.println("Nepoznata opcija. Pokusajte ponovo.");
                    break;
            }


        }
        scanner.close();
    }

}
