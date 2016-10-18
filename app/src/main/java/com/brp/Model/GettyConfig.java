
package com.brp.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GettyConfig {

    @SerializedName("result_count")
    @Expose
    private Integer resultCount;
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();

    /**
     * 
     * @return
     *     The resultCount
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * 
     * @param resultCount
     *     The result_count
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

}
