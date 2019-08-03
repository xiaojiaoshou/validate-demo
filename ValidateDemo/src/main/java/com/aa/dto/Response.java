package com.aa.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ghx
 * @descriotion 返回封装
 * @date 2019/7/9
 */
@Data
public class Response<T> implements Serializable {


    private static final long serialVersionUID = 8897590197839872568L;

    public static final String faileCode = "1";
    public static final String successCode = "0";
    private String code;
    private String desc;
    private T data;

    public Response() {
    }

    public Response(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Response(String code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public static Response setFaile(String desc) {
        Response respDTO = new Response();
        respDTO.setCode(faileCode);
        respDTO.setDesc(desc);
        return respDTO;
    }

    public static Response setSuccess() {
        Response respDTO = setSuccess("ok");
        return respDTO;
    }

    public static Response setSuccess(String desc) {
        Response respDTO = new Response();
        respDTO.setCode(successCode);
        respDTO.setDesc(desc);
        return respDTO;
    }
}
