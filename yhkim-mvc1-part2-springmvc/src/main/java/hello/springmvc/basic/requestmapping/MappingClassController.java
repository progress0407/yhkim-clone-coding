package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RequestMapping("mapping/users")
@RestController
public class MappingClassController {

    @GetMapping
    public String user() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }

    @GetMapping("{userId}")
    public String user(@PathVariable String userId) {
        return "get user = " + userId;
    }

    @PatchMapping("{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update user = " + userId;
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete user = " + userId;
    }
}
