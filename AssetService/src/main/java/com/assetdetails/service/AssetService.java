package com.assetdetails.service;

import java.util.List;

import com.assetdetails.exception.AssetAlreadyExistsException;
import com.assetdetails.exception.InvalidAssetException;
import com.assetdetails.model.AssetDetails;

public interface AssetService {

	AssetDetails postAllAssets(AssetDetails assetdetails) throws AssetAlreadyExistsException;

	List<AssetDetails> getAllAssets();

	AssetDetails getAssetByName(String assetname) throws InvalidAssetException;

	String deleteAssetByName(String assetname);

	Long totalAssetsBalance(int account_id);
	
	AssetDetails updateAssetByName(String assetName, long assetBalance) throws InvalidAssetException;

	AssetDetails getAssetById(int assetId) throws InvalidAssetException;

	List<AssetDetails> getAssetByAccount(int account_id);



}
