package ru.innopolis.uni.course3.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ru.innopolis.uni.course3.model.User;

/**
 *
 */
public class UserMapper {

    private MapperFactory factory;
    private MapperFacade mapperFacade;

    public UserMapper() {
    }

    public UserMapper(MapperFactory factory) {
        factory.classMap(User.class, ru.innopolis.uni.course3.entity.User.class)
                .byDefault()
                .register();
        mapperFacade = factory.getMapperFacade();
    }

    public User map(ru.innopolis.uni.course3.entity.User userEntity) {
        return this.mapperFacade.map(userEntity, User.class);
    }

    public ru.innopolis.uni.course3.entity.User map(User user) {
        return this.mapperFacade.map(user, ru.innopolis.uni.course3.entity.User.class);
    }

}
