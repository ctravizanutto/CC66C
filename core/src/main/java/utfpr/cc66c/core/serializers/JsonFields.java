package utfpr.cc66c.core.serializers;

import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonFields {
    public static Map<String, String> getStringFields(ObjectNode json) {
        var jsonFields = new HashMap<String, String>();
        var fields = json.fields();
        while (fields.hasNext()) {
            var field = fields.next();
            var key = field.getKey();
            var value = field.getValue();
            if (value instanceof NullNode) throw new AssertionError("[ERROR] NULL VALUE OF " + key);
            if (Objects.equals(key, "data")) {
                var nestedFields = getStringFields((ObjectNode) value);
                jsonFields.putAll(nestedFields);
            } else {
                jsonFields.put(key, value.asText());
            }
        }
        return jsonFields;
    }
}
