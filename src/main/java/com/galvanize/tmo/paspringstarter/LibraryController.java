package com.galvanize.tmo.paspringstarter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.*;

@RestController
public class LibraryController {

    ArrayList<Books> bookList = new ArrayList<Books>();

    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Books addBooks(@RequestBody Books book) {
		book.setId(bookList.size()+1);
		bookList.add(book);
        return book;
    }

    @GetMapping("/api/books")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<Books>> readBooks() {
        Collections.sort(bookList, new Comparator<Books>() {
            @Override
            public int compare(Books o1, Books o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        Map<String, List<Books>> books = new HashMap<String, List<Books>>();
        books.put("books", bookList);
        return books;
    }

    @DeleteMapping("/api/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBooks() {
        bookList.clear();
    }
}
