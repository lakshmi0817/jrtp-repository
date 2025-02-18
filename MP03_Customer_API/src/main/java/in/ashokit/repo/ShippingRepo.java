package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.ShippingAddr;

public interface ShippingRepo extends JpaRepository<ShippingAddr, Integer>{

}
