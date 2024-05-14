package com.assetdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.assetdetails.exception.AssetAlreadyExistsException;
import com.assetdetails.exception.InvalidAssetException;
import com.assetdetails.model.AssetDetails;
import com.assetdetails.service.AssetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asset")
public class AssetController {

	@Autowired
	AssetService assetService;
	

	@PostMapping("/postAsset")
	public ResponseEntity<AssetDetails> postAsset(@Valid @RequestBody AssetDetails assetdetails)
			throws AssetAlreadyExistsException {
		return new ResponseEntity<AssetDetails>(assetService.postAllAssets(assetdetails), HttpStatus.OK);
	}

//	@GetMapping("/all")
//	public List<AssetDetails> getAllAssets() {
//		return assetService.getAllAssets();
//	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AssetDetails>>getAllAssets(){
		List<AssetDetails> assets=assetService.getAllAssets();
		return new  ResponseEntity<List<AssetDetails>>(assets,HttpStatus.OK);
		
	}

	@GetMapping("/getByName/{assetname}")
	public ResponseEntity<AssetDetails> getAssetByName(@PathVariable String assetname) throws InvalidAssetException {
		AssetDetails asset = assetService.getAssetByName(assetname);
		return new ResponseEntity<AssetDetails>(asset, HttpStatus.OK);
	}

	@DeleteMapping("/deleteByName/{assetname}")
	public ResponseEntity<String> deleteAssetByName(@PathVariable String assetname) {
	    String message = assetService.deleteAssetByName(assetname);
	    
	    if (message.equals("deleted")) {
	        return new ResponseEntity<>("{\"message\": \"Asset deleted successfully\"}", HttpStatus.OK);
	    } else if (message.equals("Asset not found or could not be deleted")) {
	        return new ResponseEntity<>("{\"message\": \"Asset not found or could not be deleted\"}", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("{\"message\": \"An error occurred\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/totalAssets/{account_id}")
	public Long totalAssetsBalanceByAccountId(@PathVariable int account_id) {
		return assetService.totalAssetsBalance(account_id);
	}
	
	@GetMapping("/account/{account_id}")
	public List<AssetDetails>getAssetByAccountId(@PathVariable int account_id){
		return assetService.getAssetByAccount(account_id);
	}

	@PutMapping("/updateByName/{assetName}/{assetBalance}")

	public AssetDetails updateAssetByName(@PathVariable String assetName,@PathVariable long assetBalance)throws InvalidAssetException {

		return assetService.updateAssetByName(assetName,assetBalance);

	}
	
	@GetMapping("/getById/{assetId}")

	public ResponseEntity<AssetDetails> getAssetById(@PathVariable int assetId) throws InvalidAssetException {

	AssetDetails assetDetails =  assetService.getAssetById(assetId);

		return new ResponseEntity<AssetDetails>(assetDetails,HttpStatus.OK);

	}

 

}
