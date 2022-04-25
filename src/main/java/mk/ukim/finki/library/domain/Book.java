package mk.ukim.finki.library.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    int copies;

    @Enumerated(EnumType.STRING)
    BookType category;

    @ManyToOne
    Author author;

    public void takeBook() {
        this.copies = Math.max(this.copies - 1, 0);
    }
}
