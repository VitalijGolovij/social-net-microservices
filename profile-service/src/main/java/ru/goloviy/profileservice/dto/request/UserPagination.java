package ru.goloviy.profileservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserPagination {
    private String fieldSort;
    private Boolean orderAsc = true;
    @NotNull(message = "pageNumber most not be null")
    private Integer pageNumber;
    @NotNull(message = "pageCount most not be null")
    private Integer pageCount;
}
