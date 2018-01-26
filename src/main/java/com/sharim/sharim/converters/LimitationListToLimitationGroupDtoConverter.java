package com.sharim.sharim.converters;

import com.sharim.sharim.dto.LimitationDto;
import com.sharim.sharim.dto.LimitationGroupDto;
import com.sharim.sharim.entities.LimitationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class LimitationListToLimitationGroupDtoConverter implements Converter<List<LimitationEntity>, List<LimitationGroupDto>> {


    @Override
    public List<LimitationGroupDto> convert(List<LimitationEntity> source) {

        List<LimitationGroupDto> limitationGroupDtoList = new ArrayList<>();

        Map<Integer, List<LimitationEntity>> limitationGroup =
                source.stream().collect(groupingBy(LimitationEntity::getLimGroup));


        limitationGroup.forEach((groupId,list) -> {

            List<LimitationDto> limitationDtoList = list.stream().map(e -> convert(e)).collect(Collectors.toList());
            limitationGroupDtoList.add(LimitationGroupDto.builder()
                    .limGroupId(groupId)
                    .limitationList(limitationDtoList)
                    .build());

        });

        return limitationGroupDtoList;

    }

    private LimitationDto convert(LimitationEntity limitation) {
        return LimitationDto.builder()
                .limId(limitation.getLimId())
                .startDate(limitation.getStartDate())
                .endDate(limitation.getEndDate())
                .build();
    }
}
