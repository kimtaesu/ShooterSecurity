package com.hucet.service.mapper;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by taesu on 2017-01-22.
 */
@Configuration
public class CustomMapper {

    @Bean
    public ModelMapper modelMapperBean() {
        Condition<?, ?> skipId = (context) ->
                !context.getMapping().getLastDestinationProperty().getName()
                        .equalsIgnoreCase("id");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(skipId);
        return mapper;
    }
}
