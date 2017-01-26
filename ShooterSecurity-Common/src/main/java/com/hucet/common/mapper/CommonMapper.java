package com.hucet.common.mapper;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2017-01-26.
 */
@Component
public class CommonMapper {
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
