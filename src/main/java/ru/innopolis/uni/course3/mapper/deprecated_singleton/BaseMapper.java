package ru.innopolis.uni.course3.mapper.deprecated_singleton;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 *
 */
@Deprecated
public class BaseMapper {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

}
