package ru.innopolis.uni.course3.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ru.innopolis.uni.course3.model.Book;

/**
 *
 */
public class BookMapper {

    private MapperFactory factory;
    private MapperFacade mapperFacade;

    public BookMapper() {
    }

    public BookMapper(MapperFactory factory) {
        factory.classMap(Book.class, ru.innopolis.uni.course3.entity.Book.class)
                .byDefault()
                .register();
        mapperFacade = factory.getMapperFacade();
    }

    public Book map(ru.innopolis.uni.course3.entity.Book bookEntity) {
        return this.mapperFacade.map(bookEntity, Book.class);
    }

    public ru.innopolis.uni.course3.entity.Book map(Book book) {
        return this.mapperFacade.map(book, ru.innopolis.uni.course3.entity.Book.class);
    }

}
