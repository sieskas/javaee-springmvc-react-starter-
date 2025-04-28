package com.ia.demo.api.v1.resources;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
}
