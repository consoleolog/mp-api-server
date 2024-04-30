package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Long> {

}
