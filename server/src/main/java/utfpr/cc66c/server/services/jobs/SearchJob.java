package utfpr.cc66c.server.services.jobs;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class SearchJob {
    public static ObjectNode searchJob(String[] skillset, String experience, String filter) {
        var data = JsonNodeFactory.instance.objectNode();
        ArrayNode ret;
        if (experience == null || experience.isEmpty()) {
            ret = searchJobSkillset(skillset);
        } else if (skillset == null || skillset.length == 0) {
            ret = searchJobExperience(experience);
        } else {
            ret = searchJobSkillExperience(skillset, experience, filter);
        }
        data.set("jobset", ret);
        data.put("jobset_size", Integer.toString(ret.size()));

        return data;
    }

    private static ArrayNode searchJobSkillset(String[] skillset) {
        var jobset = JsonNodeFactory.instance.arrayNode();
        StringBuilder sql = new StringBuilder("SELECT skill, experience, job_id FROM jobs WHERE searchable='YES' AND ");
        for (var skill : skillset) {
            sql.append("skill=").append(String.format("'%s' OR ", skill));
        }
        sql.append("skill=").append("''");

        var resultSet = DatabaseDriver.query(sql.toString());
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

    private static ArrayNode searchJobExperience(String experience) {
        var jobset = JsonNodeFactory.instance.arrayNode();
        var sql = String.format("SELECT skill, experience, job_id FROM jobs WHERE searchable='YES' AND experience<='%s'", experience);
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

    private static ArrayNode searchJobSkillExperience(String[] skillset, String experience, String filter) {
        var jobset = JsonNodeFactory.instance.arrayNode();
        StringBuilder sql = new StringBuilder("SELECT skill, experience, job_id FROM jobs WHERE searchable='YES' (AND (");
        for (var skill : skillset) {
            sql.append("skill=").append(String.format("'%s' OR ", skill));
        }
        sql.append("skill=").append("'') ");
        sql.append(String.format("%s experience <='%s')", filter, experience));

        var resultSet = DatabaseDriver.query(sql.toString());
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
