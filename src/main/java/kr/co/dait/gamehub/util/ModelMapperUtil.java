package kr.co.dait.gamehub.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    static final ModelMapper modelMapper = new ModelMapper();

    public static <D> D map(java.lang.Object source, java.lang.Class<D> destinationType) {
      return modelMapper.map(source,destinationType);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
          list.add(modelMapper.map(s, targetClass));
        }
        return list;
    }
    
    
}
