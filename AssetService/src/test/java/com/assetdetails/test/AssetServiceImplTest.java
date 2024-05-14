package com.assetdetails.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assetdetails.exception.AssetAlreadyExistsException;
import com.assetdetails.exception.InvalidAssetException;
import com.assetdetails.model.AssetDetails;
import com.assetdetails.repo.AssetRepo;
import com.assetdetails.service.AssetServiceImpl;

class AssetServiceImplTest {

    @Mock
    private AssetRepo assetRepo;

    @InjectMocks
    private AssetServiceImpl assetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostAllAssets() throws AssetAlreadyExistsException {
        // Mock data
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setAssetName("TestAsset");
        when(assetRepo.findByAssetName("TestAsset")).thenReturn(null);
        when(assetRepo.save(assetDetails)).thenReturn(assetDetails);

        AssetDetails result = assetService.postAllAssets(assetDetails);
        assertEquals("TestAsset", result.getAssetName());
    }

    @Test
    void testGetAllAssets() {
        List<AssetDetails> assetList = new ArrayList<>();
        assetList.add(new AssetDetails());
        when(assetRepo.findAll()).thenReturn(assetList);

        List<AssetDetails> result = assetService.getAllAssets();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAssetByName() throws InvalidAssetException {
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setAssetName("TestAsset");
        when(assetRepo.findByAssetName("TestAsset")).thenReturn(assetDetails);

        AssetDetails result = assetService.getAssetByName("TestAsset");
        assertEquals("TestAsset", result.getAssetName());
    }

    @Test
    void testDeleteAssetByName() {
        when(assetRepo.deleteByAssetName("TestAsset")).thenReturn("1");

        String result = assetService.deleteAssetByName("TestAsset");
        assertEquals("deleted", result);
    }

    

    @Test
    void testUpdateAssetByName() throws InvalidAssetException {
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setAssetName("TestAsset");
        when(assetRepo.findByAssetName("TestAsset")).thenReturn(assetDetails);
        when(assetRepo.save(assetDetails)).thenReturn(assetDetails);

        AssetDetails result = assetService.updateAssetByName("TestAsset", 500);
        assertEquals(500, result.getAssetBalance());
    }

    @Test
    void testGetAssetById() throws InvalidAssetException {
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setAssetId(1);
        when(assetRepo.findByAssetId(1)).thenReturn(assetDetails);

        AssetDetails result = assetService.getAssetById(1);
        assertEquals(1, result.getAssetId());
    }

    @Test
    void testGetAssetByAccount() {
        // Mock data
        int accountId = 123;
        List<AssetDetails> assetList = new ArrayList<>();
        assetList.add(new AssetDetails());
        assetList.add(new AssetDetails());
        when(assetRepo.findAll()).thenReturn(assetList);

        List<AssetDetails> result = assetService.getAssetByAccount(accountId);
        assertEquals(2, result.size());
    }
}
