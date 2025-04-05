import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private int nextId;

    public Library()
    {
        books = new ArrayList<>();
        nextId = 1;
    }

    public void addBook (String title, String author, int year)
    {
        Book newBook = new Book(nextId++, title, author, year);
        books.add(newBook);
    }

    public boolean deleteBook (int id)
    {
        return books.removeIf(book -> book.getId() == id);
    }

    public List<Book> getBooksSortedByAuthor()
    {
        return books.stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByYear()
    {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getYear))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooks(String keyword)
    {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                || book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void saveToFile(String filename) throws IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(books);
        out.writeInt(nextId);
        out.close();
    }

    public void loadFromFile(String filename) throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        books = (List<Book>) in.readObject();
        nextId = in.readInt();
        in.close();
    }


}
