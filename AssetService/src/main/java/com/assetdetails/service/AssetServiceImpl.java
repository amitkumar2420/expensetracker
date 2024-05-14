package com.assetdetails.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetdetails.exception.AssetAlreadyExistsException;
import com.assetdetails.exception.InvalidAssetException;
import com.assetdetails.model.AssetDetails;
import com.assetdetails.repo.AssetRepo;


@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	AssetRepo assetRepo;

	@Override
	public AssetDetails postAllAssets(AssetDetails assetdetails) throws AssetAlreadyExistsException {
		// TODO Auto-generated method stub
		AssetDetails ass=assetRepo.findByAssetName(assetdetails.getAssetName());
		
		if(ass!=null) {
			throw new AssetAlreadyExistsException();
		}
		return assetRepo.save(assetdetails);
	}

	@Override
	public List<AssetDetails> getAllAssets() {
		// TODO Auto-generated method stub
		return assetRepo.findAll();
	}

	@Override
	public AssetDetails getAssetByName(String assetname) throws InvalidAssetException {
		// TODO Auto-generated method stub
		AssetDetails asset =  assetRepo.findByAssetName(assetname);
		if(asset==null) {
			throw new InvalidAssetException();
		}
		return asset;
	}

	@Override
	public String deleteAssetByName(String assetname) {
		// TODO Auto-generated method stub
		String s = assetRepo.deleteByAssetName(assetname);
		if (s.equals("1")) {
			return "deleted";
		} else {
			return "Not Found";
		}
	}

	@Override
	public Long totalAssetsBalance(int account_id) {
		// TODO Auto-generated method stub
		List<AssetDetails> list = assetRepo.findAll();
		List<AssetDetails> list1 = new ArrayList<>();

		Long sum = 0L;
		for (AssetDetails ad : list) {
			if (ad.getAccount_id() == account_id) {
				list1.add(ad);
			}
			

		}
		for (AssetDetails assetd : list1) {
			sum = sum + assetd.getAssetBalance();
		}
		return sum;
	}
	
	@Override

	public AssetDetails updateAssetByName(String assetName, long assetBalance)throws InvalidAssetException {

		// TODO Auto-generated method stub

		AssetDetails ad=assetRepo.findByAssetName(assetName);

		if (ad== null) {

			throw new InvalidAssetException();

		}

		ad.setAssetBalance(assetBalance);

		return assetRepo.save(ad);

	}

	@Override
	public AssetDetails getAssetById(int assetId) throws InvalidAssetException {
		AssetDetails asset = assetRepo.findByAssetId(assetId);

		if(asset==null) {

			throw new InvalidAssetException();

		}

		return asset;
	}

	@Override
	public List<AssetDetails> getAssetByAccount(int accountId) {
		List<AssetDetails> list = assetRepo.findAll();
		List<AssetDetails> list2 = new ArrayList<>();
		for (AssetDetails ass  : list) {
			if (ass.getAccount_id() == accountId) {
				list2.add(ass);
			}
		}
		return list2;
	}
	

}