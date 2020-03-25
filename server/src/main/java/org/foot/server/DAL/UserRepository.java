package org.foot.server.DAL;

import org.foot.server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
