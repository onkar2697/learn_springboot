package org.example.bookmyshow.specifications;

import org.example.bookmyshow.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> hasName(String name){       //using specification JPA
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"),name));
    }
}
