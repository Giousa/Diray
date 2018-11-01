package com.zmm.diary.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmm.diary.enums.ResultEnum;
import lombok.Data;

@Data
public class ResultVO implements Serializable{

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public ResultVO() {

    }

    public ResultVO(Object data) {
        this.code = 200;
        this.message = "成功";
        this.data = data;
    }

    public ResultVO(Integer status, String msg, Object data) {
        this.code = status;
        this.message = msg;
        this.data = data;
    }


    public static ResultVO ok() {
        return new ResultVO(null);
    }

    public static ResultVO ok(Object data) {
        return new ResultVO(data);
    }

    public static ResultVO build(Integer status, String msg) {
        return new ResultVO(status, msg, null);
    }

    public static ResultVO build(Integer status, String msg, Object data) {
        return new ResultVO(status, msg, data);
    }


    public static ResultVO error(ResultEnum resultEnum){

        return new ResultVO(resultEnum.getCode(),resultEnum.getMessage(),null);
    }


    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static ResultVO formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultVO.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static ResultVO format(String json) {
        try {
            return MAPPER.readValue(json, ResultVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResultVO formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

	@Override
	public String toString() {
		return "ResultVO [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
    
    

}
