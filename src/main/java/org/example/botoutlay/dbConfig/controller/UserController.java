package org.example.botoutlay.dbConfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.payload.UserCreatDto;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.example.botoutlay.dbConfig.entityAndService.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("/creat")
    public ResponseData<?> creat(@RequestBody UserCreatDto userCreateDto) {
        return this.userService.creatUser(userCreateDto);
    }

    @PutMapping("update/{userId}")
    public ResponseData<?> update(@PathVariable Long userId, UserCreatDto userCreateDto) {
        return this.userService.updateUser(userId, userCreateDto);
    }

    @GetMapping("/get/{userId}")
    public ResponseData<?> get(@PathVariable Long userId) {
        return this.userService.getUser(userId);
    }

//    @GetMapping("/get-one")
//    public ResponseData<?> get() {
//        return this.userService.getUser(userSession.getUser().getId());
//    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.userService.getAllUsers(page,size);
    }


    @DeleteMapping("delete/{userId}")
    public ResponseData<?> delete(@PathVariable Long userId) {
        return this.userService.deleteUser(userId);
    }

}
