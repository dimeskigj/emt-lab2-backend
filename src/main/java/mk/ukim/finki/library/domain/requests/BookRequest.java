package mk.ukim.finki.library.domain.requests;

import lombok.Data;
import mk.ukim.finki.library.domain.BookType;

@Data
public class BookRequest {
    public String name;
    public int copies;
    public BookType category;
    public Long authorId;
}
