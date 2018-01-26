package com.sharim.sharim.converters;

import com.sharim.sharim.dto.LimitationGroupDto;
import com.sharim.sharim.entities.LimitationEntity;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class LimitationListToLimitationEntityGroupDtoConverterTest {

    @Test
    public void shouldSortLimitationsToGroups() {
        LimitationEntity lim1 = new LimitationEntity();
        lim1.setLimId(1);
        lim1.setLimGroup(1);

        LimitationEntity lim2 = new LimitationEntity();
        lim2.setLimId(2);
        lim2.setLimGroup(1);


        LimitationEntity lim3 = new LimitationEntity();
        lim3.setLimId(3);
        lim3.setLimGroup(2);

        LimitationListToLimitationGroupDtoConverter limitationListToLimitationGroupDtoConverter = new LimitationListToLimitationGroupDtoConverter();

        List<LimitationGroupDto> limitationGroupDtoList =
                limitationListToLimitationGroupDtoConverter.convert(Arrays.asList(lim1,lim2,lim3));

        assertTrue(limitationGroupDtoList.size() == 2);
    }

}