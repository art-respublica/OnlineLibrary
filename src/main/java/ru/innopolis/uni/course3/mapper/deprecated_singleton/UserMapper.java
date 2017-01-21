package ru.innopolis.uni.course3.mapper.deprecated_singleton;

import ma.glasnost.orika.MapperFacade;
import ru.innopolis.uni.course3.model.User;

/**
 *
 */
@Deprecated
public enum UserMapper {

    INSTANCE;

    private final MapperFacade mapperFacade;

    private UserMapper() {
        BaseMapper.MAPPER_FACTORY.classMap(User.class, ru.innopolis.uni.course3.entity.User.class)
                .byDefault()
                .register();
        mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
    }

    public User map(ru.innopolis.uni.course3.entity.User userEntity) {
        return this.mapperFacade.map(userEntity, User.class);
    }

    public ru.innopolis.uni.course3.entity.User map(User user) {
        return this.mapperFacade.map(user, ru.innopolis.uni.course3.entity.User.class);
    }

}



