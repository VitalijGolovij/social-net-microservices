package ru.goloviy.profileservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.request.UserPagination;

@ExtendWith(MockitoExtension.class)
public class PaginationServiceImplTest {
    @Mock
    private UserConvertor userConvertor;
    @InjectMocks
    private PaginationServiceImpl paginationService;

    @Test
    public void nullPagination(){
        Assertions.assertEquals(Pageable.unpaged(), paginationService.getPageable(null));
    }
    @Test
    public void paginationWithoutSort(){
        UserPagination userPagination = new UserPagination();
        userPagination.setPageNumber(0);
        userPagination.setUserCount(10);

        Pageable expect = PageRequest.of(userPagination.getPageNumber(),userPagination.getUserCount(), Sort.unsorted());
        Assertions.assertEquals(expect, paginationService.getPageable(userPagination));
    }
    @Test
    public void paginationWithSort(){
        UserPagination pagination = new UserPagination();
        pagination.setUserCount(10);
        pagination.setPageNumber(0);
        pagination.setFieldSort("username");

        Pageable expect = PageRequest
                .of(pagination.getPageNumber(),pagination.getUserCount(), Sort.by(pagination.getFieldSort()));
        Assertions.assertEquals(expect, paginationService.getPageable(pagination));
    }
    @Test
    public void paginationWithSortAndOrder(){
        UserPagination pagination = new UserPagination();
        pagination.setUserCount(10);
        pagination.setPageNumber(0);
        pagination.setFieldSort("username");
        pagination.setOrderAsc(false);

        Sort expectedSort = Sort.by(pagination.getFieldSort()).descending();
        Pageable expect = PageRequest
                .of(pagination.getPageNumber(),pagination.getUserCount(), expectedSort);
        Assertions.assertEquals(expect, paginationService.getPageable(pagination));
    }
}
