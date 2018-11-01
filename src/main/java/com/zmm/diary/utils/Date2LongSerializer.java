package com.zmm.diary.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/10/18
 * Email:65489469@qq.com
 */
public class Date2LongSerializer extends JsonSerializer<Date> {


    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        long l = date.getTime() / 1000;

        jsonGenerator.writeNumber(l);
    }
}
