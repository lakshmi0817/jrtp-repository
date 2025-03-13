package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Order_items;

public interface OrderItemsRepo extends JpaRepository<Order_items, Integer>{

}
