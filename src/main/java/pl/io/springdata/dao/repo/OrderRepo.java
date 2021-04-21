package pl.io.springdata.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.io.springdata.dao.entity.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
