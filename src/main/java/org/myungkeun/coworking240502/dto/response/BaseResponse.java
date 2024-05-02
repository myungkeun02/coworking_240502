package org.myungkeun.coworking240502.dto.response;

import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder

public class BaseResponse<T> {
    private long statusCode;
    private T data;
}
