package utfpr.cc66c.server.services.recruiter;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class GetCompany {
    public static ArrayNode getCompany(String candidate_id) {
        var sql = String.format
                ("SELECT recruiter_id FROM candidates_recruiters WHERE candidate_id = '%s'", candidate_id);
        var rs = DatabaseDriver.query(sql);
        assert rs != null;
        var company_set = JsonNodeFactory.instance.arrayNode();
        try {
            while (rs.next()) {
                var recruiter_id = rs.getString("recruiter_id");
                company_set.add(getCompanyData(recruiter_id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return company_set;
    }

    private static ObjectNode getCompanyData(String recruiter_id) {
        var sql = String.format
                ("SELECT * FROM recruiters WHERE recruiter_id = '%s'", recruiter_id);
        var rs = DatabaseDriver.query(sql);
        assert rs != null;
        try {
            var recruiter = JsonNodeFactory.instance.objectNode();
            recruiter.put("name", rs.getString("name"));
            recruiter.put("email", rs.getString("email"));
            recruiter.put("industry", rs.getString("industry"));
            recruiter.put("description", rs.getString("description"));
            return recruiter;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
