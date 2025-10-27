package Interface;

import org.json.JSONObject;

public interface IJson {
    JSONObject toJson();
    void fromJson(JSONObject objetoJSON);
}
