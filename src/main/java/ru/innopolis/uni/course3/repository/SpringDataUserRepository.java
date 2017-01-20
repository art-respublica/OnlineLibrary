package ru.innopolis.uni.course3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.course3.entity.User;

/**
 *
 */
@Repository
public interface SpringDataUserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByEmail(String email);

}
