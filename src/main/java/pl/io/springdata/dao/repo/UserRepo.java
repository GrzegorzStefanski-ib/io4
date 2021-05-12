package pl.io.springdata.dao.repo;

import org.springframework.data.repository.CrudRepository;
import pl.io.springdata.dao.entity.UserDTO;

public interface UserRepo extends CrudRepository<UserDTO, Long> {
}

