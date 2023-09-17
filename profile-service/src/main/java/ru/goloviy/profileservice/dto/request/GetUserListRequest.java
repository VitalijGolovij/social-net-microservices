package ru.goloviy.profileservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.goloviy.profileservice.dto.UserDto;

import javax.validation.Valid;

@Getter @Setter
public class GetUserListRequest {
    private UserDto userFilter;
    @Valid
    private UserPagination pagination;
}
