package org.larrieulacoste.noe.al.trademe.features.projects.domain;

import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidProjectException;
import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.features.projects.application.command.AddProjectProfession;
import org.larrieulacoste.noe.al.trademe.features.projects.application.command.CreateProject;
import org.larrieulacoste.noe.al.trademe.features.projects.application.command.UpdateProject;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.validators.DateValidators;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProjectValidationService {
    private static final String STRING_DELIMITER = "\n - ";
    private static final String TASK_NAME = "taskName";
    private static final String SKILL_NAME = "skillName";
    private static final String SKILL_LEVEL = "skillRequiredLevel";
    private static final String PROFESSION = "profession";
    private static final String CONTRACTOR_ID = "contractorId";
    private static final String PROJECT_ID = "projectId";
    private static final String PERIOD = "period";
    private static final String DAILY_RATE = "dailyRate";
    private static final String LOCATION_NAME = "locationName";
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";
    private final Logger logger;
    private final StringValidators stringValidators;
    private final DateValidators dateValidators;

    public ProjectValidationService(Logger logger, StringValidators stringValidators, DateValidators dateValidators) {
        this.logger = logger;
        this.stringValidators = stringValidators;
        this.dateValidators = dateValidators;
    }

    public void validateCreateProject(CreateProject project) {
        logger.log("Triggered validation with project : " + project);
        List<String> errors = getCreateProjectInvalidFields(project);
        if (!errors.isEmpty()) {
            throw new InvalidProjectException(
                    "Error with project :" + ProjectValidationService.STRING_DELIMITER + String.join(
                            ProjectValidationService.STRING_DELIMITER,
                            errors
                    )
            );
        }
    }

    public void validateAddOrRemoveProjectProfession(AddProjectProfession profession) {
        logger.log("Triggered validation with project profession : " + profession);
        List<String> errors = getAddOrRemoveProjectProfessionInvalidFields(profession);
        if (!errors.isEmpty()) {
            throw new InvalidProjectException(
                    "Error with project profession :" + ProjectValidationService.STRING_DELIMITER + String.join(
                            ProjectValidationService.STRING_DELIMITER,
                            errors
                    )
            );
        }
    }

    public void validateAddOrRemoveProjectRequiredSkill(SkillRequest requiredSkill) {
        logger.log("Triggered validation with project required skill : " + requiredSkill);
        List<String> errors = getAddOrRemoveProjectRequiredSkillInvalidFields(requiredSkill);
        if (!errors.isEmpty()) {
            throw new InvalidProjectException(
                    "Error with project required skill :" + ProjectValidationService.STRING_DELIMITER + String.join(
                            ProjectValidationService.STRING_DELIMITER,
                            errors
                    )
            );
        }
    }

    private List<String> getAddOrRemoveProjectRequiredSkillInvalidFields(SkillRequest requiredSkill) {
        List<String> errors = new ArrayList<>();
        validateSkill(requiredSkill, errors);
        return errors;

    }

    public void validateUpdateProject(UpdateProject project) {
        logger.log("Triggered validation with project : " + project);
        List<String> errors = getUpdateProjectInvalidFields(project);
        if (!errors.isEmpty()) {
            throw new InvalidProjectException(
                    "Error with project :" + ProjectValidationService.STRING_DELIMITER + String.join(
                            ProjectValidationService.STRING_DELIMITER,
                            errors
                    )
            );
        }
    }

    private List<String> getAddOrRemoveProjectProfessionInvalidFields(AddProjectProfession profession) {
        List<String> errors = new ArrayList<>();
        validateProfession(profession.profession(), errors);
        return errors;
    }

    private List<String> getUpdateProjectInvalidFields(UpdateProject project) {
        List<String> errors = new ArrayList<>();
        required(project.projectId(), ProjectValidationService.PROJECT_ID, errors);
        if (project.taskName() != null) {
            required(project.taskName(), ProjectValidationService.TASK_NAME, errors);
        }
        if (project.startDate() != null || project.endDate() != null) {
            validatePeriod(project.startDate(), project.endDate(), errors);
        }
        if (project.dailyRate() != null) {
            positive(project.dailyRate(), ProjectValidationService.DAILY_RATE, errors);
        }
        if (project.locationName() != null || project.latitude() != null || project.longitude() != null) {
            required(project.locationName(), ProjectValidationService.LOCATION_NAME, errors);
            required(project.longitude(), ProjectValidationService.LONGITUDE, errors);
            required(project.latitude(), ProjectValidationService.LATITUDE, errors);
        }
        return errors;
    }

    private List<String> getCreateProjectInvalidFields(CreateProject project) {
        List<String> errors = new ArrayList<>();
        required(project.taskName(), ProjectValidationService.TASK_NAME, errors);
        validateSkills(project.skills(), errors);
        validateProfessions(project.professions(), errors);
        required(project.contractorId(), ProjectValidationService.CONTRACTOR_ID, errors);
        validatePeriod(project.startDate(), project.endDate(), errors);
        positive(project.dailyRate(), ProjectValidationService.DAILY_RATE, errors);
        required(project.locationName(), ProjectValidationService.LOCATION_NAME, errors);
        required(project.longitude(), ProjectValidationService.LONGITUDE, errors);
        required(project.latitude(), ProjectValidationService.LATITUDE, errors);
        return errors;
    }

    private void required(String field, String fieldName, List<String> errors) {
        if (!stringValidators.isNotEmptyOrOnlyWhitespaces(field)) {
            errors.add(fieldName);
        }
    }

    private void required(Double field, String fieldName, List<String> errors) {
        if (field == null) {
            errors.add(fieldName);
        }
    }

    private void validatePeriod(ZonedDateTime startDate, ZonedDateTime endDate, List<String> errors) {
        if (!dateValidators.isValidPeriod(startDate, endDate)) {
            errors.add(ProjectValidationService.PERIOD);
        }
    }

    private void positive(Integer field, String fieldName, List<String> errors) {
        if (field == null || field <= 0) {
            errors.add(fieldName);
        }
    }

    private void positive(Double field, String fieldName, List<String> errors) {
        if (field == null || field <= 0) {
            errors.add(fieldName);
        }
    }

    private void validateSkills(List<SkillRequest> skills, List<String> errors) {
        for (SkillRequest skill : skills) {
            validateSkill(skill, errors);
        }
    }

    private void validateSkill(SkillRequest skill, List<String> errors) {
        required(skill.skillName(), ProjectValidationService.SKILL_NAME, errors);
        positive(skill.requiredLevel(), ProjectValidationService.SKILL_LEVEL, errors);
    }

    private void validateProfessions(List<String> professions, List<String> errors) {
        for (String profession : professions) {
            validateProfession(profession, errors);
        }
    }

    private void validateProfession(String profession, List<String> errors) {
        required(profession, ProjectValidationService.PROFESSION, errors);
    }
}
