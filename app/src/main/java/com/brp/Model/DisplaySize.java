
package com.brp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplaySize {

    @SerializedName("is_watermarked")
    @Expose
    private Boolean isWatermarked;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uri")
    @Expose
    private String uri;

    /**
     * 
     * @return
     *     The isWatermarked
     */
    public Boolean getIsWatermarked() {
        return isWatermarked;
    }

    /**
     * 
     * @param isWatermarked
     *     The is_watermarked
     */
    public void setIsWatermarked(Boolean isWatermarked) {
        this.isWatermarked = isWatermarked;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

}
