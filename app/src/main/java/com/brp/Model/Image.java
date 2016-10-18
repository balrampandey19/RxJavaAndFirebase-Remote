
package com.brp.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("asset_family")
    @Expose
    private String assetFamily;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("collection_code")
    @Expose
    private String collectionCode;
    @SerializedName("collection_id")
    @Expose
    private Integer collectionId;
    @SerializedName("collection_name")
    @Expose
    private String collectionName;
    @SerializedName("display_sizes")
    @Expose
    private List<DisplaySize> displaySizes = new ArrayList<DisplaySize>();
    @SerializedName("license_model")
    @Expose
    private String licenseModel;
    @SerializedName("max_dimensions")
    @Expose
    private MaxDimensions maxDimensions;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The assetFamily
     */
    public String getAssetFamily() {
        return assetFamily;
    }

    /**
     * 
     * @param assetFamily
     *     The asset_family
     */
    public void setAssetFamily(String assetFamily) {
        this.assetFamily = assetFamily;
    }

    /**
     * 
     * @return
     *     The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * 
     * @param caption
     *     The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * 
     * @return
     *     The collectionCode
     */
    public String getCollectionCode() {
        return collectionCode;
    }

    /**
     * 
     * @param collectionCode
     *     The collection_code
     */
    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }

    /**
     * 
     * @return
     *     The collectionId
     */
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     * 
     * @param collectionId
     *     The collection_id
     */
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * 
     * @return
     *     The collectionName
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * 
     * @param collectionName
     *     The collection_name
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * 
     * @return
     *     The displaySizes
     */
    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

    /**
     * 
     * @param displaySizes
     *     The display_sizes
     */
    public void setDisplaySizes(List<DisplaySize> displaySizes) {
        this.displaySizes = displaySizes;
    }

    /**
     * 
     * @return
     *     The licenseModel
     */
    public String getLicenseModel() {
        return licenseModel;
    }

    /**
     * 
     * @param licenseModel
     *     The license_model
     */
    public void setLicenseModel(String licenseModel) {
        this.licenseModel = licenseModel;
    }

    /**
     * 
     * @return
     *     The maxDimensions
     */
    public MaxDimensions getMaxDimensions() {
        return maxDimensions;
    }

    /**
     * 
     * @param maxDimensions
     *     The max_dimensions
     */
    public void setMaxDimensions(MaxDimensions maxDimensions) {
        this.maxDimensions = maxDimensions;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
