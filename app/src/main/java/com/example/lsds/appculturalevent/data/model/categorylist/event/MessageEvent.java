
package com.example.lsds.appculturalevent.data.model.categorylist.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MessageEvent {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subevents")
    @Expose
    private List<Subevent> subevents = new ArrayList<Subevent>();
    @SerializedName("eticket_possible")
    @Expose
    private boolean eticketPossible;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<Category>();
    @SerializedName("seo")
    @Expose
    private Seo seo;
    @SerializedName("min_date")
    @Expose
    private String minDate;
    @SerializedName("avg_weight")
    @Expose
    private double avgWeight;

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The subevents
     */
    public List<Subevent> getSubevents() {
        return subevents;
    }

    /**
     * 
     * @param subevents
     *     The subevents
     */
    public void setSubevents(List<Subevent> subevents) {
        this.subevents = subevents;
    }

    /**
     * 
     * @return
     *     The eticketPossible
     */
    public boolean isEticketPossible() {
        return eticketPossible;
    }

    /**
     * 
     * @param eticketPossible
     *     The eticket_possible
     */
    public void setEticketPossible(boolean eticketPossible) {
        this.eticketPossible = eticketPossible;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The seo
     */
    public Seo getSeo() {
        return seo;
    }

    /**
     * 
     * @param seo
     *     The seo
     */
    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    /**
     * 
     * @return
     *     The minDate
     */
    public String getMinDate() {
        return minDate;
    }

    /**
     * 
     * @param minDate
     *     The min_date
     */
    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    /**
     * 
     * @return
     *     The avgWeight
     */
    public double getAvgWeight() {
        return avgWeight;
    }

    /**
     * 
     * @param avgWeight
     *     The avg_weight
     */
    public void setAvgWeight(double avgWeight) {
        this.avgWeight = avgWeight;
    }

}
