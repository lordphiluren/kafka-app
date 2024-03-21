package ru.sushchenko.metricsconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import ru.sushchenko.metricsconsumer.dto.MeterDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public RecordMessageConverter converter() {
        JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("ru.sushchenko.metricsconsumer.dto");
        Map<String, Class<?>> mapping = new HashMap<>();
        mapping.put("meter", MeterDto.class);
        typeMapper.setIdClassMapping(mapping);
        jsonMessageConverter.setTypeMapper(typeMapper);
        return jsonMessageConverter;
    }
}
