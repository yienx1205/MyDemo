import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author wangyanbo29
 * @Date 2023/11/16
 * @Description
 */
public class StreamTest {


    @Test
    public void test01() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "123"));
        books.add(new Book(2L, "123"));
        books.add(new Book(1L, "333"));
        books.stream().distinct().collect(Collectors.toList());
        System.out.println(books);

        List<Book> distinctNameBooks3 = books.stream().filter(distinctByKey(o -> o.getName())).collect(Collectors.toList());
        System.out.println(distinctNameBooks3);
        Map<Object, Boolean> map = new HashMap<>();
        List<Book> distinctNameBooks4 = books.stream().filter(i -> map.putIfAbsent(i.getName(), Boolean.TRUE) == null).collect(Collectors.toList());
        System.out.println(distinctNameBooks4);
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Book {

    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }
}