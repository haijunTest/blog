package com.example.api.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * @author haijun
 * @class ${classname}
 * @date 2018/6/20, 10:59
 */
@Component
public class MapperFacadeFactory implements FactoryBean<MapperFacade>{

    @Override
    public MapperFacade getObject() throws Exception {
        DefaultMapperFactory build = new DefaultMapperFactory.Builder().build();
        build.getConverterFactory().registerConverter(new CustomDateLocalDateBiConverter());
        build.getConverterFactory().registerConverter(new CustomDateLocalDateTimeBiConverter());
        return build.getMapperFacade();
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static class CustomDateLocalDateBiConverter extends BidirectionalConverter<Date, LocalDate> {
        @Override
        public LocalDate convertTo(Date date, Type<LocalDate> type, MappingContext mappingContext) {
            if (Objects.isNull(date)) {
                return null;
            }

            return LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()));
        }

        @Override
        public Date convertFrom(LocalDate localDate, Type<Date> type, MappingContext mappingContext) {
            if (Objects.isNull(localDate)) {
                return null;
            }

            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class CustomDateLocalDateTimeBiConverter extends BidirectionalConverter<Date, LocalDateTime> {
        @Override
        public LocalDateTime convertTo(Date date, Type<LocalDateTime> type, MappingContext mappingContext) {
            if (Objects.isNull(date)) {
                return null;
            }

            return LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
        }

        @Override
        public Date convertFrom(LocalDateTime localDateTime, Type<Date> type, MappingContext mappingContext) {
            if (Objects.isNull(localDateTime)) {
                return null;
            }

            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
    }
}
