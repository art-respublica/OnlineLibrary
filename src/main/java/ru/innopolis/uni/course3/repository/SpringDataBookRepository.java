package ru.innopolis.uni.course3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.entity.Book;

/**
 *
 */
@Repository
public interface SpringDataBookRepository extends PagingAndSortingRepository<Book, Integer> {

}
