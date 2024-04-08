package utfpr.cc66c.core.serializers;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonFields {
    public static Map<String, String> getAllFields(ObjectNode json) {
        var jsonFields = new HashMap<String, String>();
        var fields = json.fields();
        while (fields.hasNext()) {
            var field = fields.next();
            var key = field.getKey();
            var value = field.getValue();
            if (Objects.equals(key, "data")) {
                var nestedFields = getAllFields((ObjectNode) value);
                for (var nestedField : nestedFields.entrySet()) {
                    var nestedKey = nestedField.getKey();
                    var nestedValue = nestedField.getValue();
                    jsonFields.put(nestedKey, nestedValue);
                }
            } else {
                jsonFields.put(key, value.asText());
            }
        }
        return jsonFields;
    }
}
