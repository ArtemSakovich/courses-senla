package com.company.hoteladministrator.api.mapper;

import com.company.hoteladministrator.model.dto.generic.AbstractDto;
import com.company.hoteladministrator.model.generic.AEntity;

public interface IGenericMapper<T extends AEntity, V extends AbstractDto> {

    T toEntity(V dto);

    V toDto(T entity);
}
