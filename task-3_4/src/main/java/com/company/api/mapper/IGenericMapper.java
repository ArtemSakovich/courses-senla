package com.company.api.mapper;

import com.company.dto.AbstractDto;
import com.company.model.AEntity;

public interface IGenericMapper<T extends AEntity, V extends AbstractDto> {

    T toEntity(V dto);

    V toDto(T entity);
}
