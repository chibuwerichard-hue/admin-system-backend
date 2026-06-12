package com.school.admin_system.repository;
import com.school.admin_system.entity.AssetMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetMaintenanceRepository extends JpaRepository<AssetMaintenance, Long> {
    List<AssetMaintenance> findByAssetId(Long assetId);
}
