package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer>{

}
