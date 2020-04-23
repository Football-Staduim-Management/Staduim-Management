package org.foot.server.DAL;

import org.foot.server.model.Stadium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface StadiumRepository extends CrudRepository<Stadium, Long> {
    Stadium findByName(String name);
}
