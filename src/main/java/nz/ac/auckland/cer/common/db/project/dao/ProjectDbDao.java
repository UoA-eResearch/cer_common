package nz.ac.auckland.cer.common.db.project.dao;

import java.util.List;
import java.util.Map;

import nz.ac.auckland.cer.common.db.project.pojo.Adviser;
import nz.ac.auckland.cer.common.db.project.pojo.Affiliation;
import nz.ac.auckland.cer.common.db.project.pojo.FollowUp;
import nz.ac.auckland.cer.common.db.project.pojo.InstitutionalRole;
import nz.ac.auckland.cer.common.db.project.pojo.Project;
import nz.ac.auckland.cer.common.db.project.pojo.ProjectProperty;
import nz.ac.auckland.cer.common.db.project.pojo.ProjectWrapper;
import nz.ac.auckland.cer.common.db.project.pojo.RPLink;
import nz.ac.auckland.cer.common.db.project.pojo.ResearchOutput;
import nz.ac.auckland.cer.common.db.project.pojo.ResearchOutputType;
import nz.ac.auckland.cer.common.db.project.pojo.Researcher;
import nz.ac.auckland.cer.common.db.project.pojo.ScienceStudy;
import nz.ac.auckland.cer.common.db.project.util.Person;

public interface ProjectDbDao {

    public Integer createResearcher(
            Researcher r) throws Exception;
    
    public void createPropertyForResearcher(
    		Integer siteId, Integer researcherId, String propname, String propvalue) throws Exception;

    public Project createProject(
            ProjectWrapper pw) throws Exception;

    public void createProjectProperty(
            ProjectProperty pp) throws Exception;

    public List<Affiliation> getAffiliations() throws Exception;

    public List<InstitutionalRole> getInstitutionalRoles() throws Exception;

    public Adviser getAdviserForTuakiriSharedToken(
            String sharedToken) throws Exception;

    public Adviser getAdviserForEppn(
            String eppn) throws Exception;

    public Researcher getResearcherForTuakiriSharedToken(
            String sharedToken) throws Exception;

    public Researcher getResearcherForEppn(
            String eppn) throws Exception;

    public List<String> getAccountNamesForPerson(
            Person person) throws Exception;

    public String getInstitutionalRoleName(
            Integer roleId) throws Exception;

    public String getResearcherStatusName(
            Integer statusId) throws Exception;

    public ResearchOutputType[] getResearchOutputTypes() throws Exception;

    public Researcher getResearcherForId(
            Integer id) throws Exception;

    public List<Project> getProjectsOfResearcher(
            Integer researcherId) throws Exception;

    public Researcher[] getAllStaffOrPostDocs() throws Exception;

    public List<ScienceStudy> getScienceStudies() throws Exception;

    public String getScienceStudyNameForId(
            String id) throws Exception;

    public String getScienceDomainNameForScienceStudyId(
            String id) throws Exception;
    
    public ProjectWrapper getProjectForIdOrCode(
            String projectCode) throws Exception;

    public Map<Integer, String> getRolesOnProjectsForResearcher(
            Integer researcherId) throws Exception;

    public void addResearcherToProject(
            RPLink rpl) throws Exception;

    public void addOrUpdateFollowUp(
            FollowUp fu) throws Exception;

    public void addOrUpdateResearchOutput(
            ResearchOutput ro) throws Exception;

    public void updateResearcher(
            Researcher r) throws Exception;
    
    public void updateAdviser(
            Adviser a) throws Exception;

    public void updateProject(
            Integer projectId,
            String object,
            String field,
            String timestamp,
            String newValue) throws Exception;

    public List<String> getResearcherAccountNamesForSharedToken(
            String sharedToken) throws Exception;

    public List<String> getResearcherOrAdviserAccountNamesForSharedToken(
            String sharedToken) throws Exception;

    public List<String> getProjectCodesForSharedToken(
            String sharedToken) throws Exception;

    public List<String> getProjectCodesForEppn(
            String eppn) throws Exception;

    public List<String> getProjectCodes() throws Exception;

    public Boolean isUserAdviser(
            String sharedToken) throws Exception;

}