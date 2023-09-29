package ru.goloviy.profileservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.response.SuccessFriendActionResponse;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.service.FriendService;
import ru.goloviy.profileservice.service.PrincipalService;
import ru.goloviy.profileservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FriendControllerTest {
    @Mock
    private FriendService friendService;
    @Mock
    private UserService userService;
    @Mock
    private UserConvertor userConvertor;
    @Mock
    private PrincipalService principalService;
    @InjectMocks
    private FriendController friendController;
    private HttpServletRequest request;
    private Long id;
    private User user1;
    private User user2;
    private UserDto userDto;
    private ResponseEntity<SuccessFriendActionResponse> expect;
    @BeforeEach
    public void init(){
        request = Mockito.mock(HttpServletRequest.class);
        user1 = new User();
        user1.setId(1L);

        id = 2L;
        user2 = new User();
        user2.setId(id);

        userDto = new UserDto();
        userDto.setId(id);

        SuccessFriendActionResponse response = new SuccessFriendActionResponse(userDto);
        expect = new ResponseEntity<>(response, HttpStatus.OK);

        Mockito.when(principalService.getPrincipalUser(request)).thenReturn(user1);
    }
    @Test
    public void inviteToFriendTest(){
        Mockito.when(userService.getUserBy(id)).thenReturn(user2);
        Mockito.when(userConvertor.toUserDto(user2)).thenReturn(userDto);

        ResponseEntity<SuccessFriendActionResponse> responseEntity = (ResponseEntity<SuccessFriendActionResponse>) friendController.inviteToFriend(id, request);
        SuccessFriendActionResponse responseBody = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(expect.getBody().getUserInfo(),responseBody.getUserInfo());
    }

    @Test
    public void cancelInviteToFriendTest(){
        Mockito.when(userService.getUserBy(id)).thenReturn(user2);
        Mockito.when(userConvertor.toUserDto(user2)).thenReturn(userDto);

        ResponseEntity<SuccessFriendActionResponse> responseEntity = (ResponseEntity<SuccessFriendActionResponse>) friendController.cancelInviteFriend(id, request);
        SuccessFriendActionResponse responseBody = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(expect.getBody().getUserInfo(),responseBody.getUserInfo());
    }
    @Test
    public void acceptToFriendTest(){
        Mockito.when(userService.getUserBy(id)).thenReturn(user2);
        Mockito.when(userConvertor.toUserDto(user2)).thenReturn(userDto);

        ResponseEntity<SuccessFriendActionResponse> responseEntity = (ResponseEntity<SuccessFriendActionResponse>) friendController.acceptToFriend(id, request);
        SuccessFriendActionResponse responseBody = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(expect.getBody().getUserInfo(),responseBody.getUserInfo());
    }

    @Test
    public void deleteFriendTest(){
        Mockito.when(userService.getUserBy(id)).thenReturn(user2);
        Mockito.when(userConvertor.toUserDto(user2)).thenReturn(userDto);

        ResponseEntity<SuccessFriendActionResponse> responseEntity = (ResponseEntity<SuccessFriendActionResponse>) friendController.deleteFriend(id, request);
        SuccessFriendActionResponse responseBody = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(expect.getBody().getUserInfo(),responseBody.getUserInfo());
    }
    @Test
    public void getFriendRequestTest(){
        Mockito.when(friendService.getFriendRequests(user1)).thenReturn(List.of(userDto));

        Assertions.assertEquals(List.of(userDto), friendController.getFriendRequests(request));
        Assertions.assertDoesNotThrow(() -> friendController.getFriendRequests(request));
    }
    @Test
    public void getFriendsTest(){
        Mockito.when(friendService.getFriends(user1)).thenReturn(List.of(userDto));

        Assertions.assertEquals(List.of(userDto), friendController.getFriends(request));
        Assertions.assertDoesNotThrow(() -> friendController.getFriendRequests(request));
    }
}
