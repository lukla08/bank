package pl.training.bank.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.cglib.core.ClassEmitter;

import java.util.List;
import java.util.stream.Collectors;

public class CustomObjectMapper {

    public <ObjectToMap, DestinationType> DestinationType map(ObjectToMap object, Class<DestinationType> typeClass) {
        return new ModelMapper().map(object, typeClass);
    }

    public <Element, DestinationType> List<DestinationType> map(List<Element> objectsToMap, Class<DestinationType> typeClass) {
        ModelMapper modelMapper = new ModelMapper();
        return objectsToMap.stream()
                .map(element -> modelMapper.map(element, typeClass))
                .collect(Collectors.toList());
    }

}
