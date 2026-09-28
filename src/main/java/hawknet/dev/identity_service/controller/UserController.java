package hawknet.dev.identity_service.controller;

import hawknet.dev.identity_service.dto.request.ApiResponse;
import hawknet.dev.identity_service.dto.request.UserCreationRequest;
import hawknet.dev.identity_service.dto.request.UserUpdateRequest;
import hawknet.dev.identity_service.entity.User;
import hawknet.dev.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    User getUserById(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUserById(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "User has been deleted";
    }
}
