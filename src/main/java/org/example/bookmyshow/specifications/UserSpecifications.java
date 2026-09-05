package org.example.bookmyshow.specifications;

import jakarta.persistence.criteria.Join;
import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> hasName(String name){       //using specification JPA
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"),name));
    }

    public static Specification<User> hasAgeGreaterThanOrEqualTo(Integer age){
        return ((root,query,criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("age"),age));
    }

    public static Specification<User> hasEmail(String email){
        return ((root,query,criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"),email));
    }

    public static Specification<User> hasUserContainingName(String name){
        return ((root,query,criteriaBuilder)->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),"%"+name.toLowerCase()+"%"));   //conatins word/name expression
    }

    public static Specification<User> hasaUserStartWith(String name){
        return ((root,query,criteriaBuilder)->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),name.toLowerCase()+"%"));            //start with expression
    }

    public static Specification<User> hasUserEndsWith(String name){
        return ((root,query,criteriaBuilder)->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+name));           //End with Expression
    }

    public static Specification<User> hasUserAgeInBetween(Integer minAge, Integer maxAge){
        return ((root,query,criteriaBuilder)->
                criteriaBuilder.between(root.get("age"),minAge,maxAge));
    }

    public static Specification<User> hasUserHaveBookins(String movieName){
        return((root,query,criteriaBuilder)->{

            Join<User, Booking> bookingjoin = root.join("bookings");

                return criteriaBuilder.equal(bookingjoin.get("movieName"),movieName);
        });
    }
}