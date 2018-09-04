@Endpoint
public class ExamMarshallingEndpoint
{
    private static final String namespaceUri = "http://www.lifeimage.com/schemas/v1/0/lila";
    
    private static Logger logger = LoggerFactory.getLogger(ExamMarshallingEndpoint.class);
    private ExamManager examManager;

    /**
     * Retrieve all exams that match specified import id
     * @param requestDoc
     * @return
     */
    @PayloadRoot(localPart = "getExamsByImportIdRequest", namespace = namespaceUri)
    public GetExamsResponse getExamsByImportId(GetExamsByImportIdRequestDocument requestDoc) 
    {
    	GetExamsByImportIdRequest request = requestDoc.getGetExamsByImportIdRequest();
        GetExamsResponseDocument responseDoc = GetExamsResponseDocument.Factory.newInstance();
        GetExamsResponse response = responseDoc.addNewGetExamsResponse();
	        
        try
        {
            Long importId = request == null ? null : request.getImportId();
            logger.info("Received getExamsByImportId for import ID {}", importId);
            com.lifeimage.core.model.Exam example = com.lifeimage.core.model.Exam.getBlankExample();
            example.setImportManifestId(importId);
            String propVal = importId == null ? null : importId.toString();
            //while searching by import manifest, mrn also will be in the request
            //setting the mrn for study
            String mrn = request == null ? null : request.getMrn();
            Study study = Study.getBlankExample();
            example.addStudy(study);
            example.firstStudy().setPatientMedicalRecordNumber(mrn);
            return getExamsByExample(response, example, propVal, "importId");
        }
        catch (Exception e)
        {
            logException(response,"Error getting exams",e);
        }
        return response;
    }
