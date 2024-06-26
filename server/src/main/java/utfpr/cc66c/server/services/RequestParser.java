package utfpr.cc66c.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.controllers.candidate.ChooseCandidateController;
import utfpr.cc66c.server.controllers.candidate.SearchCandidateController;
import utfpr.cc66c.server.controllers.candidate.auth.LoginCandidateController;
import utfpr.cc66c.server.controllers.candidate.auth.SignupCandidateController;
import utfpr.cc66c.server.controllers.candidate.profile.DeleteCandidateController;
import utfpr.cc66c.server.controllers.candidate.profile.LookupCandidateController;
import utfpr.cc66c.server.controllers.candidate.profile.UpdateCandidateController;
import utfpr.cc66c.server.controllers.job.*;
import utfpr.cc66c.server.controllers.recruiter.GetCompanyController;
import utfpr.cc66c.server.controllers.recruiter.auth.LoginRecruiterController;
import utfpr.cc66c.server.controllers.recruiter.auth.SignupRecruiterController;
import utfpr.cc66c.server.controllers.recruiter.profile.DeleteRecruiterController;
import utfpr.cc66c.server.controllers.recruiter.profile.LookupRecruiterController;
import utfpr.cc66c.server.controllers.recruiter.profile.UpdateRecruiterController;
import utfpr.cc66c.server.controllers.skill.*;

public class RequestParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseJson(String request) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var operation = json.get("operation");
        if (operation != null) {
            var operationString = operation.asText();
            switch (operationString) {
                // Account Auth
                case "LOGIN_CANDIDATE" -> {
                    return LoginCandidateController.loginCandidate(json);
                }
                case "LOGIN_RECRUITER" -> {
                    return LoginRecruiterController.loginRecruiter(json);
                }
                case "LOGOUT_CANDIDATE" -> {
                    return LoginCandidateController.logoutCandidate(json);
                }
                case "LOGOUT_RECRUITER" -> {
                    return LoginRecruiterController.logoutRecruiter(json);
                }
                case "SIGNUP_CANDIDATE" -> {
                    return SignupCandidateController.signupCandidate(json);
                }
                case "SIGNUP_RECRUITER" -> {
                    return SignupRecruiterController.signupRecruiter(json);
                }
                // Account Lookup
                case "LOOKUP_ACCOUNT_CANDIDATE" -> {
                    return LookupCandidateController.lookupCandidate(json);
                }
                case "LOOKUP_ACCOUNT_RECRUITER" -> {
                    return LookupRecruiterController.lookupRecruiter(json);
                }
                // Account Delete
                case "DELETE_ACCOUNT_CANDIDATE" -> {
                    return DeleteCandidateController.deleteCandidate(json);
                }
                case "DELETE_ACCOUNT_RECRUITER" -> {
                    return DeleteRecruiterController.deleteRecruiter(json);
                }
                // Account Update
                case "UPDATE_ACCOUNT_CANDIDATE" -> {
                    return UpdateCandidateController.updateCandidate(json);
                }
                case "UPDATE_ACCOUNT_RECRUITER" -> {
                    return UpdateRecruiterController.updateRecruiter(json);
                }
                // Skill
                case "INCLUDE_SKILL" -> {
                    return IncludeSkillController.includeSkill(json);
                }
                case "LOOKUP_SKILL" -> {
                    return LookupSkillController.lookupSkill(json);
                }
                case "LOOKUP_SKILLSET" -> {
                    return LookupSkillsetController.lookupSkillset(json);
                }
                case "DELETE_SKILL" -> {
                    return DeleteSkillController.deleteSkill(json);
                }
                case "UPDATE_SKILL" -> {
                    return UpdateSkillController.updateSkill(json);
                }
                // Jobs
                case "SEARCH_JOB" -> {
                    return SearchJobController.searchJob(json);
                }
                case "INCLUDE_JOB" -> {
                    return IncludeJobController.includeJob(json);
                }
                case "LOOKUP_JOB" -> {
                    return LookupJobController.lookupJob(json);
                }
                case "LOOKUP_JOBSET" -> {
                    return LookupJobsetController.lookupJobset(json);
                }
                case "DELETE_JOB" -> {
                    return DeleteJobController.deleteJob(json);
                }
                case "UPDATE_JOB" -> {
                    return UpdateJobController.updateJob(json);
                }
                case "SET_JOB_AVAILABLE" -> {
                    return JobAvailableController.setJobAvailable(json);
                }
                case "SET_JOB_SEARCHABLE" -> {
                    return JobSearchableController.setJobSearchable(json);
                }
                // Recruiter candidate relationship
                case "SEARCH_CANDIDATE" -> {
                    return SearchCandidateController.searchCandidate(json);
                }
                case "CHOOSE_CANDIDATE" -> {
                    return ChooseCandidateController.chooseCandidate(json);
                }
                case "GET_COMPANY" -> {
                    return GetCompanyController.getCompany(json);
                }
                default -> {
                    return errorInvalidOperation(operationString);
                }
            }
        }
        return errorInvalidOperation("null");
    }

    private static String errorInvalidOperation(String operation) {
        var json = JsonNodeFactory.instance.objectNode();

        json.put("operation", operation);
        json.put("status", "INVALID_OPERATION");
        json.set("data", JsonNodeFactory.instance.objectNode());

        return json.toString();
    }
}