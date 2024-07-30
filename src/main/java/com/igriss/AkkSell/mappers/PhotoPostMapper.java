package com.igriss.AkkSell.mappers;

import com.igriss.AkkSell.dtos.PhotoPostDTO;
import com.igriss.AkkSell.entities.PhotoPost;
import com.igriss.AkkSell.mappers.mapper_helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserMapperHelper.class)
public interface PhotoPostMapper {

    @Mapping(target = "user", source = "userId")
    PhotoPost convertToEntity(PhotoPostDTO photoPostDTO);

    @Mapping(target = "userId", source = "user.id")
    PhotoPostDTO convertToDTO(PhotoPost photoPost);
}
