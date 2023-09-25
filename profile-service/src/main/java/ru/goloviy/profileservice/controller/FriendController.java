package ru.goloviy.profileservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.response.SuccessFriendActionResponse;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.service.FriendService;
import ru.goloviy.profileservice.service.PrincipalService;
import ru.goloviy.profileservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
    private final UserService userService;
    private final UserConvertor dtoConvertor;
    private final PrincipalService principalService;

    @GetMapping
    public List<UserDto> getFriends(HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        return friendService.getFriends(principalUser);
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<?> inviteToFriend(@PathVariable Long id, HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        User receiverUser = userService.getUserBy(id);
        friendService.inviteToFriend(principalUser, receiverUser);

        SuccessFriendActionResponse response = new SuccessFriendActionResponse(dtoConvertor.toUserDto(receiverUser));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/invite")
    public ResponseEntity<?> cancelInviteFriend(@PathVariable Long id, HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        User receiverUser = userService.getUserBy(id);
        friendService.cancelInviteToFriend(principalUser, receiverUser);

        SuccessFriendActionResponse response = new SuccessFriendActionResponse(dtoConvertor.toUserDto(receiverUser));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<?> acceptToFriend(@PathVariable Long id, HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        User receiverUser = userService.getUserBy(id);
        friendService.acceptToFriend(principalUser, receiverUser);

        SuccessFriendActionResponse response = new SuccessFriendActionResponse(dtoConvertor.toUserDto(receiverUser));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable Long id, HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        User receiverUser = userService.getUserBy(id);
        friendService.deleteFriend(principalUser, receiverUser);

        SuccessFriendActionResponse response = new SuccessFriendActionResponse(dtoConvertor.toUserDto(receiverUser));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/requests")
    public List<UserDto> getFriendRequests(HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        return friendService.getFriendRequests(principalUser);
    }
}
