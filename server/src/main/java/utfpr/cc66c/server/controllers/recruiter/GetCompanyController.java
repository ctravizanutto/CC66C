package utfpr.cc66c.server.controllers.recruiter;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.recruiter.GetCompany;
import utfpr.cc66c.server.validators.AuthValidator;

public class GetCompanyController {
    public static String getCompany(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        var token = fields.get("token");
        var candidate_id = JWTController.getIdClaim(token);

        var company_set = GetCompany.getCompany(candidate_id);

        return success(company_set);
    }

    private static String success(ArrayNode company_set) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.set("company", company_set);
        data.put("company_size", company_set.size());

        json.put("status", "success");
        json.set("data", data);

        return json.toString();
    }
}
