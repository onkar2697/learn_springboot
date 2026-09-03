package org.example.bookmyshow.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.example.bookmyshow.dto.UserDTO;
import org.example.bookmyshow.dto.UserRecordDTO;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.projection.UserProjection;
import org.example.bookmyshow.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService  service){
        this.service = service;
    }

      @GetMapping
      public Page<User> getUsers(Pageable pageable) {
          return service.getAllUsers(pageable);
      }

//    @GetMapping
//    public Page<User> getUsers() {
//        return service.getAllUsers(); // normal getalluser method for sorting using sort.by()
//    }


    @GetMapping("/{id:\\d+}")   //only digits will be routed
    public User getUSerById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @GetMapping("/projection")
    public List<UserProjection> findUserProjection(){return service.findUserProjection();}

    @GetMapping("projection/{id}")
    public UserProjection findUserProjectionById(@PathVariable Long id){return service.findUserProjectionById(id);}

    @GetMapping("projection/constructor")
    public List<UserDTO> findUserDTO(){return service.findUserDTO();}

    @GetMapping("/projection/record")
    public List<UserRecordDTO> findUserRecordDTO(){ return service.findUserRecordDTO();}

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/name/{name}")
    public List<User> findByName(@RequestParam String name){ return service.findByName(name);}
    // request param - when we want to filter/searching/sorting the data

    @PostMapping
    public User saveUser(@Valid @RequestBody User user){
        return service.saveUser(user);
    }  //Validating user to store the values

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@Valid @RequestBody User user){
        return service.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

    //testing
//    @PostMapping("/test")
//    public User createUserWithBooking() {
//        return service.createUserWithBooking();
//    }

//    @PutMapping("/merge/{id}")
//    public User mergeExample(@PathVariable Long id) {
//        return service.updateUserAndBooking(id);
//    }

    @DeleteMapping("/delete/{id}")
    public String delteUserAndBooking(@PathVariable Long id){
        service.deleteUser(id);

        return "user Deleted";
    }

    @GetMapping("/specifications")
    public Page<User> findUsersBySpecification(
            @RequestParam(required = false) String name,
            @RequestParam(required= false) Integer age,
            @RequestParam(required = false) String email,
            Pageable pageable){
        return service.findUsersBySpecification(name,age,email,pageable);
    }

    @GetMapping("/specifications/or")
    public Page<User> findUserBySpecificationOr(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required=false) Integer maxAge,
            Pageable pageable){
        return service.findUserBySpecificationOr(name, minAge, maxAge ,email,pageable);
    }


}
