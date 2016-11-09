
package com.example.lsds.appculturalevent.data.model.categorylist;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CategoryWeb {

    @SerializedName("message")
    @Expose
    private List<Message> message = new ArrayList<Message>();
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("ts")
    @Expose
    private long ts;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * 
     * @return
     *     The message
     */
    public List<Message> getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(List<Message> message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The code
     */
    public int getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The ts
     */
    public long getTs() {
        return ts;
    }

    /**
     * 
     * @param ts
     *     The ts
     */
    public void setTs(long ts) {
        this.ts = ts;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

}
