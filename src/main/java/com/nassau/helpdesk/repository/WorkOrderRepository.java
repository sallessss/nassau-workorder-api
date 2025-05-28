package com.nassau.helpdesk.repository;

import com.nassau.helpdesk.model.WorkOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderModel, Long> {
}
