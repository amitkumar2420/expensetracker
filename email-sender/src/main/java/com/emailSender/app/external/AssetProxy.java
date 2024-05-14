package com.emailSender.app.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AssetService", url = "http://localhost:7070/asset")

@Service
public interface AssetProxy {
	@GetMapping("/totalAssets/{account_id}")
	public Long totalAssetsBalanceByAccountId(@PathVariable int account_id);

}
