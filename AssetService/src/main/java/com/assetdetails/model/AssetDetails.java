package com.assetdetails.model;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



@Document(collection="assetdetailscol")
public class AssetDetails {
	 @Id
	    @Positive(message = "Asset ID must be a positive integer")
	    private int assetId;

	    @NotBlank(message = "Asset name cannot be blank")
	    private String assetName;

	    @NotNull(message = "Asset balance cannot be null")
	    @Min(value = 0, message = "Asset balance cannot be negative")
	    private Long assetBalance;

	    @Positive(message = "Account ID must be a positive integer")
	    private int account_id;

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public Long getAssetBalance() {
		return assetBalance;
	}

	public void setAssetBalance(Long assetBalance) {
		this.assetBalance = assetBalance;
	}

	
	public AssetDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssetDetails(int assetId, String assetName, Long assetBalance, int account_id) {
		super();
		this.assetId = assetId;
		this.assetName = assetName;
		this.assetBalance = assetBalance;
		this.account_id = account_id;
	}

	@Override
	public String toString() {
		return "AssetDetails [assetId=" + assetId + ", assetName=" + assetName + ", assetBalance=" + assetBalance
				+ ", account_id=" + account_id + "]";
	}

	
	
}
