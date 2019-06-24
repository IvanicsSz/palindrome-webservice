package io.falcon.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResponseDTO {

    private Boolean success;
    private String message;

    public static RestResponseDTO buildSuccess() {
        RestResponseDTO dto = new RestResponseDTO();
        dto.setSuccess(true);
        return dto;
    }

    public static RestResponseDTO buildFailed(String msg) {
        RestResponseDTO dto = new RestResponseDTO();
        dto.setSuccess(false);
        dto.setMessage(msg);
        return dto;
    }

}
