package ru.goloviy.profileservice.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.dto.request.UserPagination;
import ru.goloviy.profileservice.service.PaginationService;

@Component
public class PaginationServiceImpl implements PaginationService {
    @Override
    public Pageable getPageable(UserPagination pagination) {
        if (pagination == null){
            return Pageable.unpaged();
        }
        Sort sort = getSort(pagination);

        return PageRequest.of(pagination.getPageNumber(),
                pagination.getPageCount(),
                sort);
    }

    private Sort getSort(UserPagination pagination){
        Sort sort = Sort.unsorted();
        sort = sortField(sort, pagination.getFieldSort());
        sort = sortOrder(sort, pagination.getOrderAsc());
        return sort;
    }

    private Sort sortField(Sort sort, String field){
        if (field == null){
            return sort;
        }
        return sort.and(Sort.by(field));
    }

    private Sort sortOrder(Sort sort, Boolean orderAsc){
        if (orderAsc == null || orderAsc){
            return sort;
        }
        return sort.descending();
    }
}
