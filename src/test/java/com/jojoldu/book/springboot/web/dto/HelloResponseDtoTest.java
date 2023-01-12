package com.jojoldu.book.springboot.web.dto;

import junit.framework.TestCase;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseDtoTest extends TestCase {

    @Test
    public void 롬복_기능_테스트() {

        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName().equals(name));
    }

}