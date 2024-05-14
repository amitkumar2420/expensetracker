package com.assetdetails.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assetdetails.controller.AssetController;
import com.assetdetails.exception.AssetAlreadyExistsException;
import com.assetdetails.exception.InvalidAssetException;
import com.assetdetails.model.AssetDetails;
import com.assetdetails.service.AssetService;

class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostAsset() throws AssetAlreadyExistsException {
        AssetDetails assetDetails = new AssetDetails();
        when(assetService.postAllAssets(any(AssetDetails.class))).thenReturn(assetDetails);

        ResponseEntity<AssetDetails> response = assetController.postAsset(assetDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assetDetails, response.getBody());
    }

    @Test
    void testGetAllAssets() {
        List<AssetDetails> assetDetailsList = new ArrayList<>();
        when(assetService.getAllAssets()).thenReturn(assetDetailsList);

        List<AssetDetails> result = assetController.getAllAssets();

        assertEquals(assetDetailsList, result);
    }

    @Test
    void testGetAssetByName() throws InvalidAssetException {
        AssetDetails assetDetails = new AssetDetails();
        when(assetService.getAssetByName("AssetName")).thenReturn(assetDetails);

        ResponseEntity<AssetDetails> response = assetController.getAssetByName("AssetName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assetDetails, response.getBody());
    }

    @Test
    void testDeleteAssetByName() {
        when(assetService.deleteAssetByName("AssetName")).thenReturn("deleted");

        ResponseEntity<String> response = assetController.deleteAssetByName("AssetName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Asset deleted successfully\"}", response.getBody());
    }

    @Test
    void testTotalAssetsBalanceByAccountId() {
        when(assetService.totalAssetsBalance(1)).thenReturn(1000L);

        Long result = assetController.totalAssetsBalanceByAccountId(1);

        assertEquals(1000L, result);
    }

    @Test
    void testGetAssetByAccountId() {
        List<AssetDetails> assetDetailsList = new ArrayList<>();
        when(assetService.getAssetByAccount(1)).thenReturn(assetDetailsList);

        List<AssetDetails> result = assetController.getAssetByAccountId(1);

        assertEquals(assetDetailsList, result);
    }

    @Test
    void testUpdateAssetByName() throws InvalidAssetException {
        AssetDetails assetDetails = new AssetDetails();
        when(assetService.updateAssetByName("AssetName", 500)).thenReturn(assetDetails);

        AssetDetails result = assetController.updateAssetByName("AssetName", 500);

        assertEquals(assetDetails, result);
    }

    @Test
    void testGetAssetById() throws InvalidAssetException {
        AssetDetails assetDetails = new AssetDetails();
        when(assetService.getAssetById(1)).thenReturn(assetDetails);

        ResponseEntity<AssetDetails> response = assetController.getAssetById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assetDetails, response.getBody());
    }
}
