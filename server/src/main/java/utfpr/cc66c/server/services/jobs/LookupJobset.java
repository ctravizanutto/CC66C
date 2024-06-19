package utfpr.cc66c.server.services.jobs;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupJobset {
    public static ArrayNode lookupJobset(String recruiter_id) {
        var jobset = JsonNodeFactory.instance.arrayNode();
        var sql = String.format("SELECT job_id, skill, experience FROM jobs WHERE recruiter_id = '%s'", recruiter_id);

        var resultSet = DatabaseDriver.query(sql);

        assert resultSet != null;
        try {
            while (resultSet.next()) {
                var job = JsonNodeFactory.instance.objectNode();
                job.put("id", resultSet.getString("job_id"));
                job.put("skill", resultSet.getString("skill"));
                job.put("experience", resultSet.getString("experience"));
                jobset.add(job);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jobset;
    }

}
