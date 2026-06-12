package com.school.admin_system.service;
import com.school.admin_system.entity.Asset;
import com.school.admin_system.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Optional<Asset> findById(Long id) {
        return assetRepository.findById(id);
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Optional<Asset> findByAssetCode(String assetCode) {
        return assetRepository.findByAssetCode(assetCode);
    }

    public Asset updateAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
