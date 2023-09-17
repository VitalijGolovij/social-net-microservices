package ru.goloviy.profileservice.service;

import org.springframework.data.domain.Pageable;
import ru.goloviy.profileservice.dto.request.UserPagination;

public interface PaginationService {
    Pageable getPageable(UserPagination pagination);
}
