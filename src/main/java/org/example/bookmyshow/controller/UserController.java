package org.example.bookmyshow.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.example.bookmyshow.constants.AppConstants;
import org.example.bookmyshow.dto.*;
import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.mapper.UserMapper;
import org.example.bookmyshow.projection.UserProjection;
import org.example.bookmyshow.response.ApiResponse;
import org.example.bookmyshow.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    public UserController(UserService  service){

        this.service = service;
        this.userMapper = new UserMapper();
    }

//      @GetMapping
//      public Page<User> getUsers(Pageable pageable) {
//          return service.getAllUsers(pageable);
//      }

    @GetMapping
    public Page<UserResponseDTO> getUsers(Pageable pageable) {
        return service.getAllUsers(pageable)
                .map(userMapper::toUserResponseDTO);
    }

//    @GetMapping
//    public Page<User> getUsers() {
//        return service.getAllUsers(); // normal getalluser method for sorting using sort.by()
//    }


//    @GetMapping("/{id:\\d+}")   //only digits will be routed
//    public User getUSerById(@PathVariable Long id){
//        return service.getUserById(id);
//    }

    @GetMapping("/{id:\\d+}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userMapper.toUserResponseDTO(service.getUserById(id));
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
    //public User saveUser(@Valid @RequestBody User user)
 //   public UserResponseDTO saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
    public ApiResponse<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO response =  service.saveUser(userRequestDTO);

        return new ApiResponse<>(
                true,
                AppConstants.USER_CREATED,
  //               "User created successfully",
                response
        );
    }  //Validating user to store the values

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@Valid @RequestBody User user){
        return service.updateUser(id,user);
    }

    @PreAuthorize("hasRole('ADMIN') or @userService.isOwner(#id, authentication.name)")
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

    @PreAuthorize("hasRole('ADMIN') or @userService.isOwner(#id, authentication.name)")
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
            @RequestParam(required = false) String movieName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required=false) Integer maxAge,
            Pageable pageable){
        return service.findUserBySpecificationOr(name,movieName, minAge, maxAge ,email,pageable);
    }

    @GetMapping("/qbe")
    public Page<User> findUserByExample(
            @RequestParam(required = false) String name,
            Pageable pageable){
        return service.findUsersByExamples(name,pageable);
    }

    @PostMapping("/transactional/test")
    public void transactionTest(@RequestBody User user,
    @RequestParam String movieName){
        Booking booking = new Booking();
        booking.setMovieName(movieName);

        service.createUserAndBooking(user,booking);
    }

    @PostMapping("/transactional/checked-exception")
    public void testCheckedException(
            @RequestBody User user,
            @RequestParam String movieName) throws Exception {

        Booking booking = new Booking();
        booking.setMovieName(movieName);

        service.testCheckedExceptionRollback(user, booking);
    }

    @PutMapping("/optimistic-lock/{id}")
    public void testOptimisticLock(@PathVariable Long id,@RequestParam Integer age){
        service.optimisticClockTest(id,age);
    }

    @PutMapping("/passimisting-lock/{id}")
    public void testPassimistingLock(@PathVariable Long id,@RequestParam Integer age){
        service.passimesticLockTest(id,age);
    }

    @DeleteMapping("soft-delete/{id}")
    public void testSoftDelete(@PathVariable Long id){
        service.softDeleteTest(id);
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This endpoint is public";            //for public we will get 200 ok response without creentials /users will give 401 error
    }

    @GetMapping("/auth-info")
    public String getAuthInfo(Authentication authentication) {
        return "Auth user: "+ authentication.getName();
    }

    @GetMapping("/security-context")
    public String getSecurityContextUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return "Authenticated user: " + authentication.getName();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Welcome Admin";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "Welcome User";
    }

    @GetMapping("/password-check")
    public String passwordCheck(Authentication authentication) {

        return "Authenticated user: " + authentication.getName();
    }

    @GetMapping("/email-availability")
    public EmailAvailabilityResponse checkEmailAvailability(
            @RequestParam String email) {

        boolean available = service.isEmailAvailable(email);

        return new EmailAvailabilityResponse(email, available);
    }
}
