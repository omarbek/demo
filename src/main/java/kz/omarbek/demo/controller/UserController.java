package kz.omarbek.demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kz.omarbek.demo.service.IUserService;
import kz.omarbek.demo.shared.dto.UserDto;
import kz.omarbek.demo.ui.model.request.UserDetailsRequestModel;
import kz.omarbek.demo.ui.model.response.UserJson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "The Get User Details Web Service Endpoint",
            notes = "${userController.getUser.apiOperation.notes}")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authorization",
                    value = "${authorizationHeader.description}",
                    paramType = "header")
    })
    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    @PostMapping
    public UserJson createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserJson userJson = new UserJson();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, userJson);

        return userJson;
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

}
