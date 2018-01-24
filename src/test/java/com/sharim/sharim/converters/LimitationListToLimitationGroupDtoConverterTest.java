package com.sharim.sharim.converters;

import com.sharim.sharim.dto.LimitationGroupDto;
import com.sharim.sharim.entities.Limitation;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class LimitationListToLimitationGroupDtoConverterTest {

    @Test
    public void shouldSortLimitationsToGroups() {
        Limitation lim1 = new Limitation();
        lim1.setLimId(1);
        lim1.setLimGroup(1);

        Limitation lim2 = new Limitation();
        lim2.setLimId(2);
        lim2.setLimGroup(1);


        Limitation lim3 = new Limitation();
        lim3.setLimId(3);
        lim3.setLimGroup(2);

        LimitationListToLimitationGroupDtoConverter limitationListToLimitationGroupDtoConverter = new LimitationListToLimitationGroupDtoConverter();

        List<LimitationGroupDto> limitationGroupDtoList =
                limitationListToLimitationGroupDtoConverter.convert(Arrays.asList(lim1,lim2,lim3));

        assertTrue(limitationGroupDtoList.size() == 2);
    }

}