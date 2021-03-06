package ru.innopolis.uni.course3.mapper.deprecated_singleton;

import ma.glasnost.orika.MapperFacade;
import ru.innopolis.uni.course3.model.Book;

/**
 *
 */
@Deprecated
public enum BookMapper {

    INSTANCE;

    private final MapperFacade mapperFacade;

    private BookMapper() {
        BaseMapper.MAPPER_FACTORY.classMap(Book.class, ru.innopolis.uni.course3.entity.Book.class)
                .byDefault()
                .register();
        mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
    }

    public Book map(ru.innopolis.uni.course3.entity.Book bookEntity) {
        return this.mapperFacade.map(bookEntity, Book.class);
    }

    public ru.innopolis.uni.course3.entity.Book map(Book book) {
        return this.mapperFacade.map(book, ru.innopolis.uni.course3.entity.Book.class);
    }

}



