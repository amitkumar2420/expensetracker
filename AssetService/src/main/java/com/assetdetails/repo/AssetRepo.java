package com.assetdetails.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assetdetails.model.AssetDetails;
@Repository
public interface AssetRepo extends MongoRepository<AssetDetails, Integer>{
	AssetDetails findByAssetName(String assetname);
	String deleteByAssetName(String assetname);
	AssetDetails findByAssetId(int assetId);
}
