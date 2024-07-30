package com.igriss.AkkSell.mappers;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.VideoPost;
import com.igriss.AkkSell.mappers.mapper_helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapperHelper.class})
public interface VideoPostMapper {

    @Mapping(target = "user", source = "userId")
    VideoPost convertToEntity(VideoPostDTO videoPostDTO);

    @Mapping(target = "userId", source = "user.id")
    VideoPostDTO convertToDTO(VideoPost videoPost);
}
