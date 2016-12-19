package com.gloresoft.service;

import com.gloresoft.entity.Conversion;
import com.gloresoft.model.ConversionDTO;

import java.util.List;

public interface ConversionService {

    List<Conversion> getConversions(Long Id);

    void convert(ConversionDTO conversionDTO, Long Id);

}
